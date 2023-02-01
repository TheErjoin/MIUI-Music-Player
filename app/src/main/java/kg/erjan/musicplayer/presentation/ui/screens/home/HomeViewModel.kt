package kg.erjan.musicplayer.presentation.ui.screens.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kg.erjan.data.remote.service.music.MusicPlayerRemote
import kg.erjan.musicplayer.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val musicPlayerRemote: MusicPlayerRemote
) : BaseViewModel()