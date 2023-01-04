package kg.erjan.musicplayer.presentation.extensions

import androidx.navigation.NavController

fun NavController.navigateSafely(directions: String) {
    currentDestination?.let { navigate(directions) }
}