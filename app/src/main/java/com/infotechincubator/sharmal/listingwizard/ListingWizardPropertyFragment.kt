package com.infotechincubator.sharmal.listingwizard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.infotechincubator.sharmal.R
import kotlinx.android.synthetic.main.fragment_listing_wizard_property.*

/**
 * Created by kyawagwin on 10/12/17.
 */
class ListingWizardPropertyFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_listing_wizard_property, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val propertyTypeAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(context, R.array.PropertyTypes, android.R.layout.simple_spinner_item)
        propertyTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fragment_listing_wizard_property_propertyTypeSpinner.adapter = propertyTypeAdapter
    }
}