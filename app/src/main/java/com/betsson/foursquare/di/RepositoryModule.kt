package com.betsson.foursquare.di

import com.betsson.foursquare.data.repository.VenueRepository
import com.betsson.foursquare.data.repository.VenueRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideVenueRepository(venueRepository: VenueRepositoryImpl): VenueRepository
}
