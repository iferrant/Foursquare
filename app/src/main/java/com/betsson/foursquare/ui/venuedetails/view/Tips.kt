package com.betsson.foursquare.ui.venuedetails.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betsson.foursquare.model.Tip

@Composable
fun VenueTips(
    modifier: Modifier = Modifier,
    tips: List<Tip>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(tips) {
            Tip(tip = it)
            Divider()
        }
    }
}

@Composable
fun Tip(
    modifier: Modifier = Modifier,
    tip: Tip,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (tip.createdAt != null) {
            Text(
                text = tip.createdAt,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
        }
        if (tip.text != null) {
            Text(
                text = tip.text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowUp,
                contentDescription = "Agree counter",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
            Text(
                text = if(tip.agreeCount != null) tip.agreeCount.toString() else "0",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.size(32.dp))

            Icon(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = "Agree counter",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
            Text(
                text = if (tip.disagreeCount != null) tip.disagreeCount.toString() else "0",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewVenueTips() {
    VenueTips(
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
    )
}
