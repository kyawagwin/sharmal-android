package com.infotechincubator.sharmal.listingwizard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.infotechincubator.sharmal.R
import kotlinx.android.synthetic.main.fragment_listing_wizard_photo.*

/**
 * Created by Win on 12/12/2017.
 */
class ListingWizardPhotoFragment: Fragment(), View.OnClickListener {

    lateinit var dialog: MaterialDialog

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
                    addImageView()
                }
                .onNegative { dialog, which ->
                    dialog.dismiss()
                }
                .build()
    }

    override fun onClick(p0: View?) {
        dialog.show()
    }

    private fun addImageView() {
        var imageView = ImageView(context)
        imageView.setImageResource(R.drawable.ic_launcher_background)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        fragment_listing_wizard_photo_containerLayout.addView(imageView)
    }
}