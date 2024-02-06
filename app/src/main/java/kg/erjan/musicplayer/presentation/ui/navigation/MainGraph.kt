package kg.erjan.musicplayer.presentation.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.erjan.musicplayer.services.music.MusicPlayerRemote
import kg.erjan.musicplayer.presentation.ui.activity.MainActivity
import kg.erjan.musicplayer.presentation.ui.views.home.MusicListScreen
import kg.erjan.musicplayer.presentation.ui.views.PlayerScreen
import kg.erjan.musicplayer.presentation.ui.helpers.Auxiliary
import kg.erjan.musicplayer.presentation.ui.helpers.FadeTransition
import kg.erjan.musicplayer.presentation.ui.helpers.Screen
import kg.erjan.musicplayer.presentation.ui.helpers.SlideTransition

@Composable
fun MainGraphNavigation(playerRemote: MusicPlayerRemote, mainActivity: MainActivity) {
    val navController = rememberNavController()

    val auxiliary = Auxiliary(
        navController,
        mainActivity,
        playerRemote
    )

    NavHost(
        navController = navController,
        startDestination = Screen.MUSIC_LIST.route
    ) {
        composable(
            route = Screen.MUSIC_LIST.route,
        ) {
            MusicListScreen(auxiliary)
        }
        composable(
            route = Screen.TRACK_SCREEN.route,
            enterTransition = { SlideTransition.slideDown.enterTransition() },
            exitTransition = { SlideTransition.slideDown.exitTransition() },
            popEnterTransition = { FadeTransition.enterTransition() },
            popExitTransition = { SlideTransition.slideDown.exitTransition() }
        ) {
            PlayerScreen(auxiliary)
        }
    }
}