package kg.erjan.musicplayer.presentation.extensions

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kg.erjan.musicplayer.presentation.ui.helpers.Screen

fun NavController.navigateSafely(directions: String) {
    currentDestination?.let { navigate(directions) }
}

fun NavHostController.navigate(route: Screen) = navigate(route.route)