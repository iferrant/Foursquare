package com.betsson.foursquare.data.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkSeasonal(
    val closed: Boolean,
    val end_date: String,
    val hours: List<NetworkRegular>,
    @SerializedName("start_date")
    val startDate: String
)