package kg.erjan.musicplayer.presentation.ui.screens.home_music.tracks

import androidx.compose.runtime.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.erjan.domain.entities.tracks.Tracks
import kg.erjan.domain.usecases.tracks.FetchTracksUseCase
import kg.erjan.musicplayer.presentation.base.BaseViewModel
import kg.erjan.musicplayer.presentation.ui.state.UIState
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    private val fetchTracksUseCase: FetchTracksUseCase
) : BaseViewModel() {

    private val _trackState = MutableState<List<Tracks>>()
    val trackState: State<UIState<List<Tracks>>> = _trackState

    init {
        fetchTracks()
    }

    private fun fetchTracks() {
        fetchTracksUseCase().collectRequest(_trackState)
    }
}