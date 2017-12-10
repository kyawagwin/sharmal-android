package com.infotechincubator.sharmal.capture

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.cloudinary.android.MediaManager
import com.infotechincubator.sharmal.R
import com.infotechincubator.sharmal.extension.requestPermissionsExt
import com.infotechincubator.sharmal.model.PermissionsRequestModel
import com.infotechincubator.sharmal.util.SharmalUtils
import kotlinx.android.synthetic.main.activity_take_photo.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by kyawagwin on 7/12/17.
 *
 * 1. request permission
 * 2. dispatch camera intent
 * 3. save photo on public directory to be able to access from gallery
 */
class TakePhotoActivity : AppCompatActivity() {

    lateinit var photoFile: File

    companion object {
        val REQUEST_CAMERA_PERMISSION_CODE: Int = 10
        val REQUEST_TAKE_PHOTO: Int = 1

        val cameraPermissions: Array<String> = arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val requestCameraPermissions: PermissionsRequestModel =
                PermissionsRequestModel(REQUEST_CAMERA_PERMISSION_CODE, cameraPermissions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_take_photo)

        takePhotoBtn.setOnClickListener { view: View? -> requestPermissionsExt(requestCameraPermissions, dispatchTakePhotoIntent()) }
    }

    // 3
    private fun dispatchTakePhotoIntent() {
        try {
            photoFile = SharmalUtils.createImageFile()
            val photoURI = FileProvider.getUriForFile(this, "com.infotechincubator.sharmal.fileprovider", photoFile)
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO)
        } catch (ex: IOException) {
            Log.i("dispatchTakePhotoIntent", ex.toString())
        }
    }

    private fun galleryAddPhoto() {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(photoFile?.absolutePath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        this.sendBroadcast(mediaScanIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_TAKE_PHOTO) {

            if (resultCode == Activity.RESULT_OK) {
                val photoUri = Uri.fromFile(photoFile)

                galleryAddPhoto()
                MediaManager.get().upload(photoUri).option("public_id", photoFile?.nameWithoutExtension).dispatch()
                photoIV.setImageURI(photoUri)
            } else {
                photoFile.delete()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var allPermissionsGranted = true

        when (requestCode) {
            requestCameraPermissions.requestCode -> {

                for (i in requestCameraPermissions.permissions.indices) {
                    if (grantResults.isEmpty() || grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Log.i("Result", "Permission has been denied by user")
                        allPermissionsGranted = false
                    }
                }

                if (allPermissionsGranted) dispatchTakePhotoIntent()
            }
        }
    }
}