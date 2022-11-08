package com.betsson.foursquare.model

data class Venue(
    val categories: List<String>? = listOf(),
    val distance: Int = 0,
    val fsq_id: String = "",
    val hours: Hours? = null,
    val address: String? = "",
    val name: String? = "",
    val photos: List<String?>? = listOf(),
    val popularity: Float = 0f,
    val price: Int = 0,
    val rating: Float = 0f,
    val tel: String? = "",
    val tips: List<Tip?>? = listOf(),
    val verified: Boolean = false,
    val website: String? = "",
)
