package com.betsson.foursquare.ui.venuedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PhotosCarousel(
    photos: List<String>,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(photos) {
            AsyncImage(
                modifier = Modifier.size(160.dp),
                model = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}
