package kg.erjan.musicplayer.services.music

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import kg.erjan.musicplayer.services.music.playback.PlaybackService

class MusicPlayer(
    val context: Context
) : PlaybackService {

    private var mIsInitialized = false

    override val currentPlaybackState: PlaybackState?
        get() = mediaPlayer?.let {
            PlaybackState(
                played = it.currentPosition,
                total = it.duration
            )
        }

    private val unsafeMediaPlayer: MediaPlayer = MediaPlayer().apply {
        setOnPreparedListener {
            mIsInitialized = true
        }
        setOnCompletionListener {
            mIsInitialized = false
        }
        setOnErrorListener { _, _, _ ->
            true
        }

    }
    private val mediaPlayer: MediaPlayer?
        get() = if (mIsInitialized) unsafeMediaPlayer else null

    override fun isInitialized(): Boolean = mIsInitialized

    override val isPlaying: Boolean
        get() = mediaPlayer?.isPlaying ?: false

    override fun setDataSource(path: String): Boolean {
        mIsInitialized = false
        mIsInitialized = setDataSourceImpl(unsafeMediaPlayer, path)
        if (mIsInitialized) {
            setNextDataSource(null)
        }
        return mIsInitialized
    }

    private fun setDataSourceImpl(player: MediaPlayer, path: String): Boolean {
        try {
            player.reset()
            player.setOnPreparedListener(null)
            if (path.startsWith("content://")) {
                player.setDataSource(context, Uri.parse(path))
            } else {
                player.setDataSource(path)
            }
            player.prepare()
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override fun startMusic(): Boolean {
        return try {
            mediaPlayer?.start()
            true
        } catch (e: java.lang.IllegalStateException) {
            false
        }
    }

    override fun stop() {
        mediaPlayer?.reset()
    }

    override fun pause(): Boolean {
        return try {
            mediaPlayer?.pause()
            true
        } catch (e: java.lang.IllegalStateException) {
            false
        }
    }

    override fun setNextDataSource(path: String?) {
    }
}

data class PlaybackState(
    val played: Int,
    val total: Int,
) {
    val ratio: Float
        get() = (played.toFloat() / total).takeIf { it.isFinite() } ?: 0f

    companion object {
        val zero = PlaybackState(0, 0)
    }
}