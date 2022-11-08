package com.betsson.foursquare.ui.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.betsson.foursquare.R

@Composable
fun OpenFilter(
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit,
) {
    var state by remember { mutableStateOf(false) }
    Button(
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(20.dp),
        colors = selectedButtonColor(isSelected = state),
        onClick = {
            onClick(state)
            state = !state
        },
    ) {
        Icon(imageVector = Icons.Rounded.Schedule, contentDescription = "Open now")

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = stringResource(id = R.string.section_open).uppercase())
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
private fun PreviewOpenFilter() {
    OpenFilter(onClick = { })
}
