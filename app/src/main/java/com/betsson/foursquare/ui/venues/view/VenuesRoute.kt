package com.betsson.foursquare.ui.venues.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
                    Text(text = stringResource(id = R.string.coffee_title))
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

            Venues(
                modifier = Modifier.padding(horizontal = 4.dp),
                items = state.venuesList,
                onClick = onClick,
            )
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
