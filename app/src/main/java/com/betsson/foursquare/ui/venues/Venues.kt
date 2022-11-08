package com.betsson.foursquare.ui.venues

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.betsson.foursquare.model.VenueItem
import com.betsson.foursquare.ui.custom.PriceRange


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

    val venuesListState by viewModel.venuesListUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Coffee & Tea")
                }
            )
        }
    ) {

        Venues(
            modifier = modifier.padding(it),
            items = venuesListState,
            onClick = onClick,
        )
    }
}

@Composable
private fun Venues(
    modifier: Modifier = Modifier,
    items: List<VenueItem>,
    onClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items) {
            VenueItem(item = it, onClick = onClick)
            Spacer(modifier = Modifier.size(4.dp))
        }
    }

}

@Composable
private fun VenueItem(
    item: VenueItem,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = { onClick(item.fsqId) }
            ),
        shape = RoundedCornerShape(4.dp),
    ) {
        Row {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = item.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.size(4.dp))

            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = item.name ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        modifier = Modifier
                            .background(
                                color = ratingColor(item.rating),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp),
                        text = item.rating.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                PriceRange(price = item.price)

                Text(
                    text = "${item.distance/1000F}km",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewVenueItem() {
    val item = VenueItem(
        distance = 1300,
        fsqId = "id",
        name = "Bus Stop Lounge",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 2,
        rating = 9f,
    )
    VenueItem(item = item) { }
}

private fun ratingColor(rating: Float): Color {
    val colorHex = if (rating == 0f) { "#C7CDCF" }
    else if (rating > 0 && rating < 4) { "#E6092C" }
    else if (rating >= 4 && rating < 5) { "#FF6701" }
    else if (rating >= 5 && rating < 6) { "#FF9600" }
    else if (rating >= 6 && rating < 7) { "#FFC800" }
    else if (rating >= 7 && rating < 8) { "#C5DE35" }
    else if (rating >= 8 && rating < 9) { "#73CF42" }
    else { "#00B551" }

    return Color(android.graphics.Color.parseColor(colorHex))
}

private var items = listOf(
    VenueItem(
        distance = 1300,
        fsqId = "id1",
        name = "Bus Stop Lounge",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 1,
        rating = 7f,
    ),
    VenueItem(
        distance = 600,
        fsqId = "id2",
        name = "Emma's Kitchen",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 3,
        rating = 8f,
    ),
    VenueItem(
        distance = 800,
        fsqId = "id3",
        name = "Dolce Sicilia",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 7,
        rating = 7f,
    ),
    VenueItem(
        distance = 1700,
        fsqId = "id4",
        name = "Caffe Berry",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 1,
        rating = 9f,
    ),
    VenueItem(
        distance = 1900,
        fsqId = "id5",
        name = "Coral Cafe",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 2,
        rating = 5f,
    ),
)
