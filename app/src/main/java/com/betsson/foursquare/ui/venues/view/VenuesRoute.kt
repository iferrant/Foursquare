package com.betsson.foursquare.ui.venues.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.betsson.foursquare.R
import com.betsson.foursquare.model.VenueItem
import com.betsson.foursquare.ui.custom.OpenFilter
import com.betsson.foursquare.ui.custom.PriceFilter
import com.betsson.foursquare.ui.venuedetails.view.VenueItem
import com.betsson.foursquare.ui.venues.VenuesViewModel


@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLifecycleComposeApi::class,
)
@Composable
fun VenuesRoute(
    modifier: Modifier = Modifier,
    viewModel: VenuesViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
) {

    val state by viewModel.venuesListUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.venues_list_title))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
        ) {

            Divider(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 8.dp),
            )

            Filters(
                onPriceClick = { price -> viewModel.applyPriceFilter(price) },
                onOpenClick = { isOpen -> viewModel.applyOpenFilter(if (isOpen) null else true) },
            )

            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            if (state.venuesList.isEmpty() && state.isLoading.not()) {
                EmptyState()
            } else {
                Venues(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    items = state.venuesList,
                    onClick = onClick,
                )
            }
        }
    }
}

@Composable
fun Filters(
    modifier: Modifier = Modifier,
    onPriceClick: (Int) -> Unit,
    onOpenClick: (Boolean) -> Unit,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            PriceFilter { onPriceClick(it) }
        }

        item {
            OpenFilter { onOpenClick(it) }
        }
    }
}

@Composable
private fun EmptyState(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.venue_details_empty_state),
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        )
    }
}

@Composable
private fun Venues(
    modifier: Modifier = Modifier,
    items: List<VenueItem>,
    onClick: (String) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(items) {
            Spacer(modifier = Modifier.size(4.dp))
            VenueItem(item = it, onClick = onClick)
        }
    }

}

@Preview
@Composable
private fun PreviewEmptyState() {
    EmptyState()
}
