package com.betsson.foursquare.data.remote

import com.betsson.foursquare.data.remote.model.NetworkPhoto
import com.betsson.foursquare.data.remote.model.NetworkResult
import com.betsson.foursquare.data.remote.model.NetworkSearch

interface FsNetworkDataSource {
    suspend fun search(
        query: String,
        fields: String,
        categories: String,
        limit: Int,
    ): NetworkSearch

    suspend fun getPlace(id: String, fields: String): NetworkResult

    suspend fun getPhotos(id: String): List<NetworkPhoto>

}
