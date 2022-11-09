package com.betsson.foursquare.data.repository

import com.betsson.foursquare.model.Venue
import com.betsson.foursquare.model.VenueItem

interface PlaceRepository {

    suspend fun getPlace(
        id: String,
        fields: String = "categories,distance,fsq_id,hours,location,name,photos,popularity,price,rating,tel,tips,verified,website",
    ): Venue

    suspend fun getPlaces(
        query: String = "",
        fields: String = "distance,fsq_id,location,name,photos,price,rating",
        minPrice: Int = 1,
        openNow: Boolean? = null,
        limit: Int = 50,
    ): List<VenueItem>

    suspend fun getPhotos(
        id: String,
        size: String = "original",
    ): List<String>
}
