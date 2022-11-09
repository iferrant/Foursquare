package com.betsson.foursquare

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.betsson.foursquare.ui.theme.FoursquareTheme
import com.betsson.foursquare.ui.venuedetails.view.VenueDetailsClick
import com.betsson.foursquare.ui.venuedetails.view.VenueDetailsRoute
import com.betsson.foursquare.ui.venues.view.VenuesRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoursquareApp(
    state: FoursquareAppState = rememberFoursquareAppState()
) {
    FoursquareTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {
            NavHost(
                navController = state.navController,
                startDestination = Screen.Venues.route,
                modifier = Modifier.padding(it)
            ) {

                composable(route = Screen.Venues.route) {
                    VenuesRoute(
                        onClick = { id -> state.navController.navigate("venue/${id}") }
                    )
                }

                composable(
                    route = Screen.Details.route,
                    arguments = listOf(navArgument(venueIdArg) { type = NavType.StringType })
                ) {
                    VenueDetailsRoute {
                        when (it) {
                            VenueDetailsClick.OnBack -> state.navController.popBackStack()
                            else -> { }
                        }
                    }
                }
            }
        }
    }
}

internal const val venueIdArg = "venueId"

internal class VenueArgs(val venueId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle.get<String>(venueIdArg)))
}
