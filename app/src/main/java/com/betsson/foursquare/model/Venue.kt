package com.betsson.foursquare.model

data class Venue(
    val categories: List<String>,
    val distance: Int,
    val fsq_id: String,
    val hours: Hours,
    val location: Location,
    val name: String,
    val photos: List<Photo>,
    val popularity: Int,
    val price: Int,
    val rating: Int,
    val tel: String,
    val tips: List<Tip>,
    val verified: Boolean,
    val website: String
)
