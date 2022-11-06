package com.betsson.foursquare.data.remote.model

data class NetworkTip(
    val agree_count: Int,
    val created_at: String,
    val disagree_count: Int,
    val id: String,
    val lang: String,
    val text: String,
    val url: String
)