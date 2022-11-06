package com.betsson.foursquare.data.remote.model

data class NetworkHours(
    val display: String,
    val is_local_holiday: Boolean,
    val open_now: Boolean,
    val networkRegular: List<NetworkRegular>
)