package com.infotechincubator.sharmal.capture

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.infotechincubator.sharmal.R
import kotlinx.android.synthetic.main.activity_take_photo.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by kyawagwin on 7/12/17.
 */
class TakePhotoActivity: AppCompatActivity() {

    var photoFile: File? = null

    companion object {
        val REQUEST_CAMERA_PERMISSION: Int = 10
        val REQUEST_TAKE_PHOTO: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_take_photo)

        //checkPermissions()

        takePhotoBtn.setOnClickListener { view: View? -> checkPermissions() }
    }

    // 1
    private fun checkPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("setupPermissions", "Permission to use camera denied")
            makeRequest()
        } else {
            dispatchTakePhotoIntent()
        }
    }

    // 2
    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION)
    }

    // 3
    private fun dispatchTakePhotoIntent() {
        try {
            photoFile = createImageFile()
            val photoURI = FileProvider.getUriForFile(this, "com.infotechincubator.sharmal.fileprovider", photoFile)
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO)
        } catch (ex: IOException) {

        }
    }

    private fun galleryAddPhoto() {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(photoFile?.absolutePath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        this.sendBroadcast(mediaScanIntent)
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {

            galleryAddPhoto()

            //val extra = data?.extras
            //val imageBitmap = extra?.get("data") as Bitmap
            //photoIV.setImageBitmap(imageBitmap)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("Result", "Permission has been denied by user")
                } else {
                    Log.i("Result", "Permission has been granted by user")
                    dispatchTakePhotoIntent()
                }
            }
        }
    }
}