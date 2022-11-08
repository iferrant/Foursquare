package com.betsson.foursquare.model

data class VenueItem(
    val distance: Int,
    val fsqId: String,
    val name: String? = "",
    val photoUrl: String?,
    val price: Int,
    val rating: Float,
)
