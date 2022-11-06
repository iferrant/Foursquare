package com.betsson.foursquare.data.remote

import com.betsson.foursquare.data.remote.model.NetworkSearch

interface FsNetworkDataSource {
    suspend fun search(query: String, categories: String): NetworkSearch
}
