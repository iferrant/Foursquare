package com.betsson.foursquare.model

data class Hours(
    val display: String,
    val isLocalHoliday: Boolean,
    val openNow: Boolean,
    val regular: List<Regular>
)