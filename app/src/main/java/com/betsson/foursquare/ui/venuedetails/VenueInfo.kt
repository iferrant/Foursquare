package com.betsson.foursquare.ui.venuedetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betsson.foursquare.model.Venue
import com.betsson.foursquare.ui.custom.Rating

@Composable
fun VenueInfo(
    modifier: Modifier = Modifier,
    venue: Venue,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(key = 1) {
            DetailsSection(
                title = "Ratings",
            ) {
                Rating(rating = venue.rating)
            }
        }

        item(key = 2) {
            DetailsSection {
                Contact(venue = venue)
            }
        }

        item(key = 3) {
            DetailsSection(
                title = "Photos",
            ) {
                PhotosCarousel(photos = venue.photos)
            }
        }

    }
}

@Composable
fun DetailsSection(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            title?.let {
                Text(
                    text = title.uppercase(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4F)
                )
                Spacer(modifier = Modifier.size(8.dp))
            }

            content()
        }
    }
}

@Preview
@Composable
fun PreviewVenueInfo() {
}
