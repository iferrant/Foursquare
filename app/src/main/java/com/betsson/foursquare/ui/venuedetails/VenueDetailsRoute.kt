package com.betsson.foursquare.ui.venuedetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.betsson.foursquare.R
import com.betsson.foursquare.model.Hours
import com.betsson.foursquare.model.Regular
import com.betsson.foursquare.model.Tip
import com.betsson.foursquare.model.Venue
import com.betsson.foursquare.ui.custom.PriceRange
import java.util.*

sealed class VenueDetailsClick {
    object OnBack: VenueDetailsClick()
    class OnFav(val id: String): VenueDetailsClick()
}

@Composable
fun VenueDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: VenueDetailsViewModel = viewModel(),
    venueId: String,
    onClick: (VenueDetailsClick) -> Unit,
) {
    VenueDetails(
        modifier = modifier,
        venue = venue,
        onClick = onClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VenueDetails(
    modifier: Modifier = Modifier,
    venue: Venue,
    onClick: (VenueDetailsClick) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            DetailsTopBar(
                venue = venue,
                onClick = onClick,
            )
        },
    ) {

        var state by remember { mutableStateOf(0) }
        val titles = listOf(
            stringResource(id = R.string.tab_highlights),
            stringResource(id = R.string.tab_tips),
        )

        Column(
            modifier = Modifier.padding(it)
        ) {
            TabRow(selectedTabIndex = state) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        text = {
                            Text(
                                text = title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            when (state) {
                0 -> VenueInfo(venue = venue)
                1 -> VenueTips(tips = venue.tips)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    modifier: Modifier = Modifier,
    venue: Venue,
    onClick: (VenueDetailsClick) -> Unit,
) {
    LargeTopAppBar(
        modifier = modifier,
        title = {
            Column {
                Text(text = venue.name)
                Row {
                    if (venue.categories.first().isNotEmpty()) {
                        Text(
                            text = venue.categories.first(),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    PriceRange(price = venue.price)
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { onClick(VenueDetailsClick.OnBack) }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "Go back")
            }
        },
        actions = {
            // TODO: Check is favorite
            IconButton(onClick = { onClick(VenueDetailsClick.OnFav(venue.fsq_id)) }) {
                Icon(Icons.Rounded.Favorite, contentDescription = "Save as favourite")
            }
        }
    )
}


@Preview
@Composable
fun PreviewVenueDetails() {
    VenueDetails(venue = venue) { }
}

private val venue = Venue(
    categories = listOf("Coffee", "Restaurant"),
    distance = 1300,
    fsq_id = "id",
    hours = Hours (
        display = "display",
        isLocalHoliday = false,
        openNow = true,
        regular = listOf(
            Regular(
                close = "01:00 AM",
                day = 2,
                open = "07:00 AM"
            )
        )
    ),
    address = "The Strand Gzira",
    name = "Bus Stop Lounge",
    photos = listOf(
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
        "https://restaurantguidemalta.com/wp-content/uploads/2020/04/Bus-Stop-Lounge-The-Strand-Near-to-Manoel-Island-Gzira-Malta-5.jpg",
    ),
    popularity = 4,
    price = 3,
    rating = 8,
    tel = "99786654",
    tips = listOf(
        Tip(
            agreeCount = 4,
            createdAt = "06/11/2022",
            disagreeCount = 1,
            id = "id1",
            lang = "EN",
            text = "Great food, great views and great prices",
            url = "https://url.com"
        ),
        Tip(
            agreeCount = 9,
            createdAt = "13/04/2022",
            disagreeCount = 0,
            id = "id2",
            lang = "EN",
            text = "They have a good selection of food and drinks. Perfect place to enjoy the sunny weather of Malta",
            url = "https://url.com"
        ),
    ),
    verified = true,
    website = "https://bustoplounge.com"
)

