package com.betsson.foursquare.data.repository

import com.betsson.foursquare.model.VenueItem
import kotlinx.coroutines.flow.Flow

interface VenueRepository {
    fun getCoffeeBarsStream(): Flow<List<VenueItem>>
}
