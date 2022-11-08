package com.betsson.foursquare.data.repository

import com.betsson.foursquare.model.Venue
import com.betsson.foursquare.model.VenueItem
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getPlace(
        id: String,
        fields: String = "categories,distance,fsq_id,hours,location,name,photos,popularity,price,rating,tel,tips,verified,website",
    ): Flow<Venue>

    fun getCoffeeBarsStream(
        query: String = "",
        fields: String = "distance,fsq_id,location,name,photos,price,rating",
        categories: String = "13032,13034,13034",
        limit: Int = 50,
    ): Flow<List<VenueItem>>

    fun getPhotos(
        id: String,
        size: String = "original",
    ): Flow<List<String>>
}
