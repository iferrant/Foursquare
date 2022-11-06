package com.betsson.foursquare.di

import com.betsson.foursquare.data.remote.FsNetworkDataSource
import com.betsson.foursquare.data.remote.RetrofitFsNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsFsNetwork(fsNetwork: RetrofitFsNetwork): FsNetworkDataSource
}
