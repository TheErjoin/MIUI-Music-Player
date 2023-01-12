package kg.erjan.data.remote.service.music.playback

interface PlaybackService {

    val isInitialized: Boolean

    val isPlaying: Boolean

    val audioSessionId: Int

    fun setCallbacks(callbacks: PlaybackCallbacks)

    fun setDataSource(path: String): Boolean

    fun startMusic(): Boolean

    fun setNextDataSource(path: String?)

    interface PlaybackCallbacks {
        fun onTrackWentToNext()

        fun onTrackEnded()
    }
}