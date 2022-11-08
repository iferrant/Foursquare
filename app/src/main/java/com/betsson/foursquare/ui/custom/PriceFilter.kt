package com.betsson.foursquare.ui.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PriceFilter(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    var state by remember { mutableStateOf(0) }
    Row(
        modifier = modifier.selectableGroup()
    ) {
        Button(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 0.dp, bottomEnd = 0.dp, bottomStart = 20.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
            ),
            colors = selectedButtonColor(state == 0),
            onClick = {
                onClick(1)
                state = 0
            },
        ) {
            Text(text = "$")
        }

        Button(
            shape = RectangleShape,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
            ),
            colors = selectedButtonColor(state == 1),
            onClick = {
                onClick(2)
                state = 1
            },
        ) {
            Text(text = "$$")
        }

        Button(
            shape = RectangleShape,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
            ),
            colors = selectedButtonColor(state == 2),
            onClick = {
                onClick(3)
                state = 2
            },
        ) {
            Text(text = "$$$")
        }

        Button(
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 20.dp, bottomEnd = 20.dp, bottomStart = 0.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
            ),
            colors = selectedButtonColor(state == 3),
            onClick = {
                onClick(4)
                state = 3
            },
        ) {
            Text(text = "$$$$")
        }

    }
}

@Composable
private fun selectedButtonColor(isSelected: Boolean): ButtonColors =
    if (isSelected) {
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        )
    }

@Preview
@Composable
private fun PreviewPriceFilter() {
    PriceFilter { }
}

