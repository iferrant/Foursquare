package com.betsson.foursquare.data.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkHours(
    val display: String?,
    @SerializedName("is_local_holiday")
    val isLocalHoliday: Boolean?,
    @SerializedName("open_now")
    val openNow: Boolean?,
    val regular: List<NetworkRegular>?,
    val seasonal: List<NetworkSeasonal>,
)