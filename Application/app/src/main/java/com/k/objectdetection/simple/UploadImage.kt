/*
HELP TAKEN

Select an Image from Gallery
https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/

TensorFlow lite Android Example
https://github.com/tensorflow/examples/tree/master/lite/examples/object_detection/android
*/

package com.k.objectdetection.simple

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.k.objectdetection.R
import com.k.objectdetection.databinding.FragmentUploadImageBinding
import com.k.objectdetection.mirror.fragments.PermissionsFragment
import com.k.objectdetection.tensorflow.ObjectDetectorHelper
import org.tensorflow.lite.task.vision.detector.Detection
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UploadImage : Fragment(), ObjectDetectorHelper.DetectorListener {

    private val TAG = "SimpleObjectDetection"

    private var _fragmentUploadImageBinding: FragmentUploadImageBinding? = null

    private val fragmentUploadImageBinding
        get() = _fragmentUploadImageBinding!!

    private lateinit var objectDetectorHelper: ObjectDetectorHelper
    private var imageAnalyzer: ImageAnalysis? = null

    /** Blocking Image operations are performed using this executor */
    private lateinit var imageExecutor: ExecutorService

    override fun onResume() {
        super.onResume()
        // Make sure that all permissions are still present, since the
        // user could have removed them while the app was in paused state.
        if (!PermissionsFragment.hasPermissions(requireContext())) {
            Navigation.findNavController(requireActivity(), R.id.fragment_container)
                .navigate(com.k.objectdetection.mirror.fragments.CameraFragmentDirections.actionCameraToPermissions())
        }
    }

    override fun onDestroyView() {
        _fragmentUploadImageBinding = null
        super.onDestroyView()

        // Shut down our background executor
        imageExecutor.shutdown()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentUploadImageBinding = FragmentUploadImageBinding.inflate(inflater, container, false)

        return fragmentUploadImageBinding.root
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dispatchTakePictureIntent()


        objectDetectorHelper = ObjectDetectorHelper(
            context = requireContext(),
            objectDetectorListener = this
        )

        // Initialize our background executor
        imageExecutor = Executors.newSingleThreadExecutor()

    }

    /**
     * Open a Gallery app to take photo.
     */
    private val GALLERY_REQUEST_CODE = 123
    private fun dispatchTakePictureIntent() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            val bitmapBuffer: Bitmap? = selectedImageUri?.let { getBitmapFromUri(it) }

            _fragmentUploadImageBinding?.uploadImage?.setImageBitmap(bitmapBuffer)

            if (bitmapBuffer != null) {
                objectDetectorHelper.detect(bitmapBuffer, 0)
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        imageAnalyzer?.targetRotation = fragmentUploadImageBinding.viewFinder.display.rotation
    }

    // Update UI after objects have been detected. Extracts original image height/width
    // to scale and place bounding boxes properly through OverlayView
    override fun onResults(
        results: MutableList<Detection>?,
        inferenceTime: Long,
        imageHeight: Int,
        imageWidth: Int
    ) {
        activity?.runOnUiThread {

            // Pass necessary information to OverlayView for drawing on the canvas
            fragmentUploadImageBinding.overlay.setResults(
                results ?: LinkedList<Detection>(),
                imageHeight,
                imageWidth
            )

            // Force a redraw
            fragmentUploadImageBinding.overlay.invalidate()
        }
    }

    override fun onError(error: String) {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }
}
