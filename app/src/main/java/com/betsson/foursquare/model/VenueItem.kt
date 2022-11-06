package com.betsson.foursquare.model

data class VenueItem(
    val distance: Int,
    val fsq_id: String,
    val name: String,
    val photoUrl: String,
    val price: Int,
    val rating: Int,
)
