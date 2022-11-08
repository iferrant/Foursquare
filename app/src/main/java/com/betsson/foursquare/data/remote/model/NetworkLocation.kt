package com.betsson.foursquare.data.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkLocation(
    val address: String,
    @SerializedName("address_extended")
    val addressExtended: String,
    @SerializedName("admin_region")
    val adminRegion: String,
    @SerializedName("census_block")
    val censusBlock: String,
    val country: String,
    @SerializedName("cross_street")
    val crossStreet: String,
    val dma: String,
    @SerializedName("formatted_address")
    val formattedAddress: String,
    val locality: String,
    val neighborhood: List<String>,
    @SerializedName("po_box")
    val poBox: String,
    @SerializedName("post_town")
    val postTown: String,
    val postcode: String,
    val region: String
)