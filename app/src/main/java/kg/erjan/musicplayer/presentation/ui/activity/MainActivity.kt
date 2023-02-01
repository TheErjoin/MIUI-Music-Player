package kg.erjan.musicplayer.presentation.ui.activity

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kg.erjan.data.remote.service.music.MusicPlayerRemote
import kg.erjan.musicplayer.presentation.ui.navigation.MainGraphNavigation
import kg.erjan.musicplayer.presentation.ui.theme.MainBackgroundColor
import kg.erjan.musicplayer.presentation.ui.theme.MusicPlayerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var serviceToken: MusicPlayerRemote.ServiceToken? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        bindService()
        requestPermission()
        setContentCompose()
    }

    private fun bindService() {
        serviceToken = viewModel.playerRemote.bindService(this, object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                //TODO for receiver
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                //TODO for receiver
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.playerRemote.unbindFromService(serviceToken)
    }

    private fun requestPermission() {
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun setContentCompose() {
        setContent {
            MusicPlayerTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MainBackgroundColor),
                ) {
                    MainGraphNavigation(viewModel.playerRemote,this@MainActivity)
                }
            }
        }
    }
}