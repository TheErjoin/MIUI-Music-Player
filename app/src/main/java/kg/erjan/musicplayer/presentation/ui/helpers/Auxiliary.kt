package kg.erjan.musicplayer.presentation.ui.helpers

import androidx.navigation.NavHostController
import kg.erjan.musicplayer.services.music.MusicPlayerRemote
import kg.erjan.musicplayer.presentation.ui.activity.MainActivity

class Auxiliary(
    val navController: NavHostController,
    val activity: MainActivity,
    val musicPlayerRemote: MusicPlayerRemote
)