package com.betsson.foursquare.ui.venues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betsson.foursquare.data.repository.PlaceRepository
import com.betsson.foursquare.model.VenueItem
import com.betsson.foursquare.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class VenuesUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val venuesList: List<VenueItem> = listOf(),
) : UiState

private data class CurrentSearch(
    val price: Int = 1,
    val open: Boolean? = null,
)

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val repository: PlaceRepository,
) : ViewModel() {

    init {
        getVenuesList()
    }

    private val _venueUiStateFlow: MutableStateFlow<VenuesUiState> = MutableStateFlow(VenuesUiState(isLoading = true))
    val venuesListUiState: StateFlow<VenuesUiState> = _venueUiStateFlow.asStateFlow()

    private var currentSearch = CurrentSearch()

    private fun getVenuesList() {
        viewModelScope.launch {
            val response = repository.getCoffeeBarsStream()
            _venueUiStateFlow.update {
                it.copy(
                    venuesList = response,
                    isLoading = false,
                )
            }
        }
    }

    fun applyPriceFilter(price: Int) {
        _venueUiStateFlow.update { it.copy(isLoading = true) }
        currentSearch = currentSearch.copy(price = price)
        applyFilter(currentSearch)
    }

    fun applyOpenFilter(open: Boolean?) {
        _venueUiStateFlow.update { it.copy(isLoading = true) }
        currentSearch = currentSearch.copy(open = open)
        applyFilter(currentSearch)
    }

    private fun applyFilter(currentSearch: CurrentSearch) {
        viewModelScope.launch {
            val response = repository.getCoffeeBarsStream(minPrice = currentSearch.price, openNow = currentSearch.open)
            _venueUiStateFlow.update {
                it.copy(
                    venuesList = response,
                    isLoading = false,
                )
            }
        }
    }
}
