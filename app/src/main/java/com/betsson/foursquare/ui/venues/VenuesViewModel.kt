package com.betsson.foursquare.ui.venues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betsson.foursquare.data.repository.PlaceRepository
import com.betsson.foursquare.model.VenueItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val repository: PlaceRepository,
) : ViewModel() {

    val venuesListUiState: StateFlow<List<VenueItem>> =
        repository
            .getCoffeeBarsStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = listOf()
            )

}
