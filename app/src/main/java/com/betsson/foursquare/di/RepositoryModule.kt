package com.betsson.foursquare.di

import com.betsson.foursquare.data.repository.PlaceRepository
import com.betsson.foursquare.data.repository.PlaceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun providePlaceRepository(placeRepository: PlaceRepositoryImpl): PlaceRepository
}
