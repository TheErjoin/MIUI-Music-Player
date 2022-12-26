package kg.erjan.musicplayer.presentation.ui.screens.home.tracks

import dagger.hilt.android.lifecycle.HiltViewModel
import kg.erjan.domain.entities.tracks.Tracks
import kg.erjan.domain.usecases.tracks.FetchTracksUseCase
import kg.erjan.musicplayer.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    private val fetchTracksUseCase: FetchTracksUseCase
) : BaseViewModel() {

    private val _trackState = MutableUIStateFlow<List<Tracks>>()
    val trackState = _trackState.asStateFlow()

    init {
        fetchTracks()
    }

    private fun fetchTracks() {
        fetchTracksUseCase().collectRequest(_trackState)
    }
}