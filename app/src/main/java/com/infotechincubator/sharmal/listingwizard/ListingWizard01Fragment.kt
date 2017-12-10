package com.infotechincubator.sharmal.listingwizard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.infotechincubator.sharmal.R

/**
 * Created by kyawagwin on 9/12/17.
 */
class ListingWizard01Fragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_listing_wizard_01, container, false)
    }
}