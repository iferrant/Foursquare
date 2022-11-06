package com.betsson.foursquare.data.remote.model

data class NetworkPhoto(
    val created_at: String,
    val height: Int,
    val id: String,
    val prefix: String,
    val suffix: String,
    val width: Int,
    val tip: NetworkTip
)