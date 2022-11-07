package com.betsson.foursquare.ui.custom

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PriceRange(
    modifier: Modifier = Modifier,
    currency: String = "€",
    price: Int,
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
private fun PreviewPriceRange() {
    PriceRange(price = 3)
}
