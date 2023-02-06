package kg.erjan.musicplayer.presentation.ui.activity

import dagger.hilt.android.lifecycle.HiltViewModel
import kg.erjan.data.remote.service.music.MusicPlayerRemote
import kg.erjan.musicplayer.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val playerRemote: MusicPlayerRemote
) : BaseViewModel()