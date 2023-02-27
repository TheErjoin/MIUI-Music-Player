package kg.erjan.musicplayer.presentation.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kg.erjan.data.remote.service.music.MusicPlayerRemote
import kg.erjan.musicplayer.presentation.ui.activity.MainActivity
import kg.erjan.musicplayer.presentation.ui.views.home.MusicListScreen
import kg.erjan.musicplayer.presentation.ui.views.player.PlayerScreen
import kg.erjan.musicplayer.presentation.ui.helpers.Auxiliary
import kg.erjan.musicplayer.presentation.ui.helpers.FadeTransition
import kg.erjan.musicplayer.presentation.ui.helpers.Screen
import kg.erjan.musicplayer.presentation.ui.helpers.SlideTransition

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainGraphNavigation(playerRemote: MusicPlayerRemote, mainActivity: MainActivity) {
    val navController = rememberAnimatedNavController()

    val auxiliary = Auxiliary(
        navController,
        mainActivity,
        playerRemote
    )

    AnimatedNavHost(
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
            enterTransition = { SlideTransition.slideUp.enterTransition() },
            exitTransition = { SlideTransition.slideDown.exitTransition() },
            popEnterTransition = { FadeTransition.enterTransition() },
            popExitTransition = { SlideTransition.slideDown.exitTransition() }
        ) {
            PlayerScreen(auxiliary)
        }
    }
}