package kg.erjan.data.remote.service.music.playback

interface PlaybackService {

    fun isInitialized(): Boolean

    fun isPlaying(): Boolean

    val audioSessionId: Int

    fun stop()

    fun pause(): Boolean

    fun setCallbacks(callbacks: PlaybackCallbacks)

    fun setDataSource(path: String): Boolean

    fun startMusic(): Boolean

    fun setNextDataSource(path: String?)

    interface PlaybackCallbacks {
        fun onTrackWentToNext()

        fun onTrackEnded()
    }
}