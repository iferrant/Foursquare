package com.betsson.foursquare.ui.venues

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.betsson.foursquare.model.VenueItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VenuesRoute(
    modifier: Modifier = Modifier,
    viewModel: VenuesViewModel = viewModel()
) {

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
            items = items.plus(items),
        )
    }
}

@Composable
private fun Venues(
    modifier: Modifier = Modifier,
    items: List<VenueItem>,
) {
    LazyColumn(
        modifier = modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items) {
            VenueItem(item = it)
            Spacer(modifier = Modifier.size(4.dp))
            Divider()
        }
    }

}

@Composable
private fun VenueItem(
    item: VenueItem,
    modifier: Modifier = Modifier,
) {
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        AsyncImage(
            modifier = Modifier.size(100.dp),
            model = item.photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.size(8.dp))

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = item.name,
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

@Composable
private fun PriceRange(
    price: Int,
    currency: String = "€",
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        for (i in 1..4) {
            Text(
                text = currency,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = if(i<=price) 1f else 0.3f)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewVenueItem() {
    val item = VenueItem(
        distance = 1300,
        fsq_id = "id",
        name = "Bus Stop Lounge",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 2,
        rating = 9,
    )
    VenueItem(item = item)
}

@Preview
@Composable
private fun PreviewPriceRange() {
    PriceRange(price = 3)
}

private fun ratingColor(rating: Int): Color {
    val colorHex = if (rating == 0) { "#C7CDCF" }
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
        fsq_id = "id1",
        name = "Bus Stop Lounge",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 1,
        rating = 7,
    ),
    VenueItem(
        distance = 600,
        fsq_id = "id2",
        name = "Emma's Kitchen",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 3,
        rating = 8,
    ),
    VenueItem(
        distance = 800,
        fsq_id = "id3",
        name = "Dolce Sicilia",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 7,
        rating = 7,
    ),
    VenueItem(
        distance = 1700,
        fsq_id = "id4",
        name = "Caffe Berry",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 1,
        rating = 9,
    ),
    VenueItem(
        distance = 1900,
        fsq_id = "id5",
        name = "Coral Cafe",
        photoUrl = "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        price = 2,
        rating = 5,
    ),
)
