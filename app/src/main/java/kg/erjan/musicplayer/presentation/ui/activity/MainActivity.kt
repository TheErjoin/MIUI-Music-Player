package kg.erjan.musicplayer.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import kg.erjan.musicplayer.presentation.ui.navigation.MainGraphNavigation
import kg.erjan.musicplayer.presentation.ui.theme.MainBackgroundColor
import kg.erjan.musicplayer.presentation.ui.theme.MusicPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicPlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainBackgroundColor
                ) {
                   MainGraphNavigation()
                }
            }
        }
    }
}