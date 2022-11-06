package com.betsson.foursquare.data.repository

import com.betsson.foursquare.data.remote.FsNetworkDataSource
import com.betsson.foursquare.data.remote.model.NetworkResult
import com.betsson.foursquare.data.remote.model.asListModel
import com.betsson.foursquare.model.VenueItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VenueRepositoryImpl @Inject constructor(
    private val network: FsNetworkDataSource,
) : VenueRepository {

    override fun getCoffeeBarsStream(): Flow<List<VenueItem>> = flow {
        emit(
            network
                .search("", "13032,13034,13034")
                .networkResults
                .map(NetworkResult::asListModel)
        )
    }

}
