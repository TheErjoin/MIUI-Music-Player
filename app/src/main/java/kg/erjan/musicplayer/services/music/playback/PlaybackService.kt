package kg.erjan.musicplayer.services.music.playback

import kg.erjan.musicplayer.services.music.PlaybackState

interface PlaybackService {

    fun isInitialized(): Boolean

    val isPlaying: Boolean

    val currentPlaybackState: PlaybackState?
    fun stop()

    fun pause(): Boolean

    fun setDataSource(path: String): Boolean

    fun startMusic(): Boolean

    fun setNextDataSource(path: String?)
}