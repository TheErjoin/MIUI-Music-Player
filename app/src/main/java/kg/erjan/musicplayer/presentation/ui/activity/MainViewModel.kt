package kg.erjan.musicplayer.presentation.ui.activity

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.erjan.data.remote.service.music.MusicPlayerRemote
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val playerRemote: MusicPlayerRemote
) : ViewModel()