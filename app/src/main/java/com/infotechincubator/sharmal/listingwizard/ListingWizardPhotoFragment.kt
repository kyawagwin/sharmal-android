package com.infotechincubator.sharmal.listingwizard

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.cloudinary.android.MediaManager
import com.infotechincubator.sharmal.R
import com.infotechincubator.sharmal.capture.TakePhotoActivity
import com.infotechincubator.sharmal.extension.requestPermissionsExt
import com.infotechincubator.sharmal.model.PermissionsRequestModel
import com.infotechincubator.sharmal.util.SharmalUtils
import kotlinx.android.synthetic.main.activity_take_photo.*
import kotlinx.android.synthetic.main.fragment_listing_wizard_photo.*
import java.io.File
import java.io.IOException

/**
 * Created by Win on 12/12/2017.
 */
class ListingWizardPhotoFragment: Fragment(), View.OnClickListener {

    lateinit var dialog: MaterialDialog

    lateinit var tempPhoto: File
    var mainPhoto: File? = null
    var galleryPhotos: MutableList<File> = mutableListOf()

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

    override fun onCreateView(inflater: android.view.LayoutInflater, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View? {
        return inflater.inflate(R.layout.fragment_listing_wizard_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_listing_wizard_photo_addBtn.setOnClickListener(this)


        dialog = MaterialDialog.Builder(context!!)
                .title("Add Photo")
                .content("Add photo dialog")
                .positiveText("Add")
                .negativeText("Cancel")  
                .onPositive { dialog, which ->

                }
                .onNegative { dialog, which ->

                }
                .build()
    }

    override fun onClick(view: View?) {
        requestPermissionsExt(requestCameraPermissions, dispatchTakePhotoIntent())
    }

    private fun dispatchTakePhotoIntent() {
        try {
            tempPhoto = SharmalUtils.createImageFile()
            val photoURI = FileProvider.getUriForFile(context!!, "com.infotechincubator.sharmal.fileprovider", tempPhoto)
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(takePhotoIntent, TakePhotoActivity.REQUEST_TAKE_PHOTO)
        } catch (ex: IOException) {
            Log.i("dispatchTakePhotoIntent", ex.toString())
            Toast.makeText(context, ex.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun addImageView(photoUri: Uri) {
        var imageView = ImageView(context)
        imageView.setImageURI(photoUri)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SharmalUtils.dpToPx(200))
        fragment_listing_wizard_photo_containerLayout.addView(imageView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TakePhotoActivity.REQUEST_TAKE_PHOTO) {

            if (resultCode == Activity.RESULT_OK) {
                val photoUri = Uri.fromFile(tempPhoto)

                galleryPhotos.add(tempPhoto)
                addImageView(Uri.fromFile(tempPhoto))
                /*
                // Upload to cloudinary
                MediaManager.get().upload(photoUri).option("public_id", photoFile?.nameWithoutExtension).dispatch()
                */
            } else {
                tempPhoto.delete()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var allPermissionsGranted = true

        when (requestCode) {
            TakePhotoActivity.requestCameraPermissions.requestCode -> {

                for (i in TakePhotoActivity.requestCameraPermissions.permissions.indices) {
                    if (grantResults.isEmpty() || grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Log.i("Result", "Permission has been denied by user")
                        allPermissionsGranted = false
                    }
                }

                if (allPermissionsGranted) dispatchTakePhotoIntent()
            }
        }
    }

    override fun onDestroy() {
        // clear all the photos
        if (galleryPhotos.size > 0) {
            galleryPhotos.forEach { file ->
                file.delete()
            }
        }

        super.onDestroy()
    }
}