package com.betsson.foursquare.data.repository

import com.betsson.foursquare.data.remote.FsNetworkDataSource
import com.betsson.foursquare.data.remote.model.NetworkResult
import com.betsson.foursquare.data.remote.model.asListModel
import com.betsson.foursquare.data.remote.model.asModel
import com.betsson.foursquare.model.Venue
import com.betsson.foursquare.model.VenueItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val network: FsNetworkDataSource,
) : PlaceRepository {

    override fun getPlace(id: String, fields: String): Flow<Venue> = flow {
        emit(network.getPlace(id, fields).asModel())
    }

    override fun getCoffeeBarsStream(
        query: String,
        fields: String,
        categories: String,
        limit: Int,
    ): Flow<List<VenueItem>> = flow {
        emit(
            network
                .search(query, fields, categories, limit)
                .results
                .map(NetworkResult::asListModel)
        )
    }

    override fun getPhotos(
        id: String,
        size: String,
    ): Flow<List<String>> = flow{
        emit(network.getPhotos(id).map { it.asModel(size) })
    }

}
