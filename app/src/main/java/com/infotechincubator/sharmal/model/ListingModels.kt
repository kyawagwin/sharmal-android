package com.infotechincubator.sharmal.model

/**
 * Created by Win on 12/13/2017.
 */
data class ListingModel(
        var location: String = "",
        var address: String = "",
        var blockNo: String = "",
        var floorLevel: Int = 0,
        var lat: Double = 0.0,
        var lng: Double = 0.0,
        var propertyType: String = "",
        var area: Int = 0,
        var condition: String = "",
        var bedroomCount: Int = 0,
        var bathroomCount: Int = 0,
        var description: String = ""
)