package kg.erjan.data.remote.service.music.mock

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.audiofx.AudioEffect
import android.net.Uri
import android.os.PowerManager
import android.util.Log
import kg.erjan.data.remote.service.music.playback.PlaybackService

class MusicServiceImpl(
    val context: Context
) : PlaybackService {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    private var mIsInitialized = false

    override fun isInitialized(): Boolean = mIsInitialized

    override val isPlaying: Boolean
        get() = mediaPlayer.isPlaying && mIsInitialized

    override val audioSessionId: Int = mediaPlayer.audioSessionId

    private var callbacks: PlaybackService.PlaybackCallbacks? = null
    private var mNextMediaPlayer: MediaPlayer? = null

    override fun setCallbacks(callbacks: PlaybackService.PlaybackCallbacks) {
        this.callbacks = callbacks
    }

    override fun setDataSource(path: String): Boolean {
        mIsInitialized = false
        mIsInitialized = setDataSourceImpl(mediaPlayer, path)
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
        val intent = Intent(AudioEffect.ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION)
        intent.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, audioSessionId)
        intent.putExtra(AudioEffect.EXTRA_PACKAGE_NAME, context.packageName)
        intent.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC)
        context.sendBroadcast(intent)
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

    override fun stop() {
        mediaPlayer.reset()
    }

    override fun pause(): Boolean {
        return try {
            mediaPlayer.pause()
            true
        } catch (e: java.lang.IllegalStateException) {
            false
        }
    }

    override fun setNextDataSource(path: String?) {
        try {
            mediaPlayer.setNextMediaPlayer(null)
        } catch (e: IllegalArgumentException) {
            Log.i("MusicPlayer", "Next media player is current one, continuing")
        } catch (e: IllegalStateException) {
            return
        }
        if (mNextMediaPlayer != null) {
            mNextMediaPlayer!!.release()
            mNextMediaPlayer = null
        }
        mNextMediaPlayer = MediaPlayer()
        mNextMediaPlayer!!.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
        mNextMediaPlayer!!.audioSessionId = audioSessionId
        if (setDataSourceImpl(mediaPlayer, path.toString())) {
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
}