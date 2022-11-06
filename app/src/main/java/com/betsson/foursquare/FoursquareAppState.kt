package com.betsson.foursquare

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Venues: Screen("venues")
    object Details: Screen("detail")
}

@Composable
fun rememberFoursquareAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current,
) = remember(navController, context) {
    FoursquareAppState(navController)
}

class FoursquareAppState(
    val navController: NavHostController,
) {
    fun navigateBack() {
        navController.popBackStack()
    }
}