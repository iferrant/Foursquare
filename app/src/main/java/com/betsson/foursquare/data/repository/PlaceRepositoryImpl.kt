package com.betsson.foursquare.data.repository

import com.betsson.foursquare.data.remote.FsNetworkDataSource
import com.betsson.foursquare.data.remote.model.NetworkResult
import com.betsson.foursquare.data.remote.model.asListModel
import com.betsson.foursquare.data.remote.model.asModel
import com.betsson.foursquare.di.IoDispatcher
import com.betsson.foursquare.model.Venue
import com.betsson.foursquare.model.VenueItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val network: FsNetworkDataSource,
) : PlaceRepository {

    override suspend fun getPlace(id: String, fields: String): Venue = withContext(ioDispatcher) {
        network.getPlace(id, fields).asModel()
    }

    override suspend fun getCoffeeBarsStream(
        query: String,
        fields: String,
        categories: String,
        minPrice: Int,
        openNow: Boolean?,
        limit: Int,
    ): List<VenueItem> =
        withContext(ioDispatcher) {
            network
                .search(query, fields, categories, minPrice, openNow, limit)
                .results
                .map(NetworkResult::asListModel)
        }

    override suspend fun getPhotos(
        id: String,
        size: String,
    ): List<String> = withContext(ioDispatcher) {
        network.getPhotos(id).map { it.asModel(size) }
    }

}
