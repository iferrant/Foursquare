package com.betsson.foursquare.data.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkTip(
    @SerializedName("agree_count")
    val agreeCount: Int?,
    @SerializedName("created_at")
    val created_at: String?,
    @SerializedName("disagree_count")
    val disagreeCount: Int?,
    val id: String?,
    val lang: String?,
    val text: String?,
    val url: String?,
)