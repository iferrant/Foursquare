package com.betsson.foursquare.data.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkPhoto(
    @SerializedName("created_at")
    val createdAt: String,
    val height: Int,
    val id: String,
    val prefix: String,
    val suffix: String,
    val width: Int,
    val tip: NetworkTip?,
    val classifications: List<String>,
)

fun NetworkPhoto.asModel(size: String) = prefix.plus(size).plus(suffix)
