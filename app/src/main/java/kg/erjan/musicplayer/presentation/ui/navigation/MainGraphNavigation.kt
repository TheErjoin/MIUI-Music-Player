package kg.erjan.musicplayer.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.erjan.musicplayer.presentation.ui.screens.music_list.MusicListScreen

@Composable
fun MainGraphNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MUSIC_LIST.route){
        composable(route = Screen.MUSIC_LIST.route){
            MusicListScreen()
        }
    }
}