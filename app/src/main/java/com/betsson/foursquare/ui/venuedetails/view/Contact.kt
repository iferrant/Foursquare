package com.betsson.foursquare.ui.venuedetails.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.betsson.foursquare.R
import com.betsson.foursquare.model.Hours
import com.betsson.foursquare.model.Venue

@Composable
fun Contact(
    modifier: Modifier = Modifier,
    venue: Venue,
) {
    Column(
        modifier = modifier,
    ) {
        TitledContact(title = stringResource(id = R.string.section_address)) {
            if (venue.address != null) {
                Address(address = venue.address)
            } else {
                // TODO: Empty state
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        venue.hours?.let { hours ->
            TitledContact(title = stringResource(id = R.string.section_hours)) {
                Hours(hours)
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        TitledContact(title = stringResource(id = R.string.section_category)) {
            if (venue.categories != null) {
                Category(categories = venue.categories)
            } else {
                // TODO: Empty state
            }
        }
    }
}

@Composable
private fun TitledContact(
    title: String,
    content: @Composable () -> Unit
) {
    Text(
        text = title.uppercase(),
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4F)
    )

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        content()
    }
}

@Composable
private fun Address(
    address: String,
) {
    Text(
        text = address,
    )
}

@Composable
private fun Hours(
    hours: Hours,
) {
    hours.openNow?.let { isOpen ->
        Text(
            text = stringResource(id = if (isOpen) R.string.section_open else R.string.section_closed),
            color = if (isOpen) Color.Green else Color.Red,
            fontWeight = FontWeight.Bold,
        )
    }
    hours.regular?.let { regular ->
        regular.forEach {
            Text(text = "${mapDayNumber(number = it.day)}: ${it.open} - ${it.close}")
        }
    }
}

@Composable
private fun Category(
    categories: List<String>,
) {
    var commaSeparatedCategories = ""
    categories
        .forEachIndexed { index, category ->
            commaSeparatedCategories +=
                if (index == categories.size -1) category else category.plus(", ")
        }
    Text(text = commaSeparatedCategories)
}

@Composable
private fun mapDayNumber(number: Int): String =
    when(number) {
        1 -> stringResource(id = R.string.monday)
        2 -> stringResource(id = R.string.tuesday)
        3 -> stringResource(id = R.string.wednesday)
        4 -> stringResource(id = R.string.thursday)
        5 -> stringResource(id = R.string.friday)
        6 -> stringResource(id = R.string.saturday)
        7 -> stringResource(id = R.string.sunday)
        else -> ""
    }
