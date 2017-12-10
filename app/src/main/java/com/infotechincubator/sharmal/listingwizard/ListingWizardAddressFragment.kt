package com.infotechincubator.sharmal.listingwizard

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.infotechincubator.sharmal.R
import kotlinx.android.synthetic.main.fragment_listing_wizard_address.*

/**
 * Created by kyawagwin on 9/12/17.
 */
class ListingWizardAddressFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_listing_wizard_address, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(context, R.array.locations, android.R.layout.simple_spinner_item)
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fragment_listing_wizard_address_locationSpinner.adapter = locationAdapter

        val floorLevelAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(context, R.array.floorLevels, android.R.layout.simple_spinner_item)
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fragment_listing_wizard_address_floorLevelSpinner.adapter = floorLevelAdapter
    }
}