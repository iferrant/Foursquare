package com.betsson.foursquare.ui.venuedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betsson.foursquare.VenueArgs
import com.betsson.foursquare.data.repository.PlaceRepository
import com.betsson.foursquare.model.Venue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class VenueDetailsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val venue: Venue = Venue(),
)

@HiltViewModel
class VenueDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlaceRepository,
): ViewModel() {

    private val venueArgs: VenueArgs = VenueArgs(savedStateHandle)

    private val _venueDetailsUiStateFlow: MutableStateFlow<VenueDetailsUiState> =
        MutableStateFlow(VenueDetailsUiState(isLoading = true))
    val venueDetailsUiStateFlow: StateFlow<VenueDetailsUiState> =
        _venueDetailsUiStateFlow.asStateFlow()

    init {
        getVenueDetails(venueArgs.venueId)
    }

    private fun getVenueDetails(id: String) {
        viewModelScope.launch {
            val venue = repository
                .getPlace(venueArgs.venueId)
            val photos = repository.getPhotos(venueArgs.venueId)
            _venueDetailsUiStateFlow.update { it.copy(venue = venue.copy(photos = photos)) }
        }
    }

}
