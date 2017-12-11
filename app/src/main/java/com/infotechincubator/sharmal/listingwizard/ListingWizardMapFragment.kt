package com.infotechincubator.sharmal.listingwizard

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.infotechincubator.sharmal.R
import kotlinx.android.synthetic.main.fragment_listing_wizard_map.*
import com.google.android.gms.maps.MapsInitializer



/**
 * Created by kyawagwin on 10/12/17.
 */
class ListingWizardMapFragment: Fragment(), OnMapReadyCallback {

    private var map: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listing_wizard_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_listing_wizard_mapView.onCreate(savedInstanceState)
        fragment_listing_wizard_mapView.onResume()

        try {
            MapsInitializer.initialize(activity?.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        fragment_listing_wizard_mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

}