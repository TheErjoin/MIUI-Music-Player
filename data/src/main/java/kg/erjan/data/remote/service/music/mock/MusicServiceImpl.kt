package kg.erjan.data.remote.service.music.mock

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.PowerManager
import android.util.Log
import kg.erjan.data.remote.service.music.playback.PlaybackService
import javax.inject.Inject

class MusicServiceImpl(
    val context: Context
) : PlaybackService, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private var mediaPlayer: MediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }
    override var isInitialized = false
    override val isPlaying: Boolean = isInitialized && mediaPlayer.isPlaying
    override val audioSessionId: Int = mediaPlayer.audioSessionId
    private var mNextMediaPlayer: MediaPlayer? = null

    override fun setDataSource(path: String): Boolean {
        isInitialized = false
        isInitialized = setDataSourceImpl(mediaPlayer, path)
        if (isInitialized) {
            setNextDataSource(null)
        }
        return isInitialized
    }

    private fun setDataSourceImpl(mediaPlayer: MediaPlayer, path: String): Boolean {
        try {
            mediaPlayer.release()
            mediaPlayer.setOnPreparedListener(null)
            if (path.startsWith("content://")) {
                mediaPlayer.setDataSource(context, Uri.parse(path))
            } else {
                mediaPlayer.setDataSource(path)
            }
        } catch (e: Exception) {
            return false
        }
        mediaPlayer.setOnCompletionListener(this)
        mediaPlayer.setOnErrorListener(this)
        return true
    }

    override fun startMusic(): Boolean {
        return try {
            mediaPlayer.start()
            true
        } catch (e: java.lang.IllegalStateException) {
            false
        }
    }

    override fun setNextDataSource(path: String?) {
        try {
            mediaPlayer.setNextMediaPlayer(null)
        } catch (e: IllegalArgumentException) {
            Log.i("MusicPlayer", "Next media player is current one, continuing");
        } catch (e: IllegalStateException) {
            Log.e("MusicPlayer", "Media player not initialized!");
            return
        }
        if (mNextMediaPlayer != null) {
            mNextMediaPlayer!!.release()
            mNextMediaPlayer = null
        }
        mNextMediaPlayer = MediaPlayer()
        mNextMediaPlayer!!.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
        mNextMediaPlayer!!.audioSessionId = audioSessionId
        if (setDataSourceImpl(mNextMediaPlayer!!, path.toString())) {
            try {
                mNextMediaPlayer!!.setNextMediaPlayer(mNextMediaPlayer)
            } catch (e: IllegalArgumentException) {
                Log.e(
                    "MusicPlayer",
                    "setNextDataSource: setNextMediaPlayer()",
                    e
                )
                if (mNextMediaPlayer != null) {
                    mNextMediaPlayer!!.release()
                    mNextMediaPlayer = null
                }
            }
        } else {
            if (mNextMediaPlayer != null) {
                mNextMediaPlayer!!.release()
                mNextMediaPlayer = null
            }
        }
    }

    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        Log.e("onError", "onError: ", )
        isInitialized = false
        mediaPlayer.release()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
        return false
    }

    override fun onCompletion(p0: MediaPlayer?) {
        Log.e("MusicPlayer", "onCompletion: ")
    }
}