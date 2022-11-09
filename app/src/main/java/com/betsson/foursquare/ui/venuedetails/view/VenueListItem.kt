package com.betsson.foursquare.ui.venuedetails.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.betsson.foursquare.model.VenueItem
import com.betsson.foursquare.ui.custom.PriceRange

@Composable
fun VenueItem(
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
