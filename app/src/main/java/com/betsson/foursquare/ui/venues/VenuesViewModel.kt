package com.betsson.foursquare.ui.venues

import androidx.lifecycle.ViewModel
import com.betsson.foursquare.data.repository.VenueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val venueRepository: VenueRepository,
) : ViewModel() {

}
