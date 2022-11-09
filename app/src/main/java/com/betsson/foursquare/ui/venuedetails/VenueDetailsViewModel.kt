package com.betsson.foursquare.ui.venuedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betsson.foursquare.VenueArgs
import com.betsson.foursquare.data.repository.PlaceRepository
import com.betsson.foursquare.model.Venue
import com.betsson.foursquare.venueIdArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class VenueDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlaceRepository,
): ViewModel() {

    private val venueArgs: VenueArgs = VenueArgs(savedStateHandle)

    val venueUiState: StateFlow<Venue> =
        repository
            .getPlace(venueArgs.venueId)
            .flatMapMerge { venue ->
                repository
                    .getPhotos(venueArgs.venueId)
                    .map { venue.copy(photos = it) }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = Venue()
            )

}
