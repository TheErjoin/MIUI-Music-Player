package kg.erjan.musicplayer.presentation.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kg.erjan.musicplayer.presentation.ui.screens.home.MusicListScreen
import kg.erjan.musicplayer.presentation.ui.screens.player.PlayerScreen
import kg.erjan.musicplayer.presentation.ui.utils.FadeTransition
import kg.erjan.musicplayer.presentation.ui.utils.SlideTransition

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainGraphNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.MUSIC_LIST.route
    ) {
        composable(
            route = Screen.MUSIC_LIST.route,
        ) {
            MusicListScreen(navController)
        }
        composable(
            route = Screen.TRACK_SCREEN.route,
            enterTransition = { SlideTransition.slideUp.enterTransition() },
            exitTransition = { SlideTransition.slideDown.exitTransition() },
            popEnterTransition = { FadeTransition.enterTransition() },
            popExitTransition = { SlideTransition.slideDown.exitTransition() }
        ) {
            PlayerScreen(navController)
        }
    }
}