package com.betsson.foursquare

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.betsson.foursquare.ui.theme.FoursquareTheme
import com.betsson.foursquare.ui.venuedetails.VenueDetailsClick
import com.betsson.foursquare.ui.venuedetails.VenueDetailsRoute
import com.betsson.foursquare.ui.venues.VenuesRoute

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
                        viewModel = hiltViewModel(),
                        onClick = { id -> state.navController.navigate("venue/${id}") }
                    )
                }

                composable(
                    route = Screen.Details.route,
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) { backStackEntry ->
                    VenueDetailsRoute(
                        viewModel = hiltViewModel(),
                        venueId = backStackEntry.arguments?.getString("id") ?: ""
                    ) {
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
