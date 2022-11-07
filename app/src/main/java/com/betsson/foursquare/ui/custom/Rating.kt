package com.betsson.foursquare.ui.custom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Rating(
    modifier: Modifier = Modifier,
    rating: Int,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(80.dp),
            progress = rating.toFloat()/10,
        )

        Text(
            text = "$rating",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
private fun PreviewRating() {
    Rating(rating = 8)
}
