package com.infotechincubator.sharmal.listingwizard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.infotechincubator.sharmal.R
import kotlinx.android.synthetic.main.activity_listing_wizard.*
import kotlinx.android.synthetic.main.fragment_listing_wizard_address.*

/**
 * Created by kyawagwin on 9/12/17.
 */
class ListingWizardActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var adapter: WizardPagerAdapter
    private var currentPage = 0
    private var wizardFragments: Array<Fragment> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_listing_wizard)

        wizardFragments = arrayOf(
                ListingWizardAddressFragment(),
                ListingWizardMapFragment(),
                ListingWizardPropertyFragment(),
                ListingWizardPhotoFragment())

        adapter = WizardPagerAdapter(supportFragmentManager)
        adapter.addFragments(wizardFragments)

        activity_listing_wizard_viewPager.setPagingEnabled(false)
        activity_listing_wizard_viewPager.adapter = adapter

        activity_listing_wizard_nextButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            activity_listing_wizard_nextButton.id -> {

                /*
                when (activity_listing_wizard_viewPager.currentItem) {

                }

                Log.i("result", fragment_listing_wizard_address_blockNoTV.text.toString())
                */
                currentPage += 1
                activity_listing_wizard_viewPager.setCurrentItem(currentPage, true)
            }
        }
    }
}