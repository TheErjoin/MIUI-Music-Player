package kg.erjan.musicplayer.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kg.erjan.musicplayer.presentation.ui.navigation.MainGraphNavigation
import kg.erjan.musicplayer.presentation.ui.theme.MainBackgroundColor
import kg.erjan.musicplayer.presentation.ui.theme.MusicPlayerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionForExternalStorageLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        setContentCompose()
    }

    private fun requestPermission() {
        requestPermissionForExternalStorageLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun setContentCompose() {
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