package kg.erjan.musicplayer.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.erjan.musicplayer.presentation.ui.screens.home.MusicListScreen
import kg.erjan.musicplayer.presentation.ui.screens.track.TrackScreen

@Composable
fun MainGraphNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.TRACK_SCREEN.route){
        composable(route = Screen.MUSIC_LIST.route){
            MusicListScreen()
        }
        composable(route = Screen.TRACK_SCREEN.route){
            TrackScreen()
        }
    }
}