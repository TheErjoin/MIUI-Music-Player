package kg.erjan.data.remote.service.music.playback

interface PlaybackService {

    fun isInitialized(): Boolean

    val isPlaying: Boolean

    fun stop()

    fun pause(): Boolean

    fun setDataSource(path: String): Boolean

    fun startMusic(): Boolean

    fun setNextDataSource(path: String?)
}