package kg.erjan.data.remote.service.music

import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import kg.erjan.data.remote.service.music.mock.MusicServiceImpl
import kg.erjan.data.remote.service.music.playback.PlaybackHandler
import kg.erjan.data.remote.service.music.playback.PlaybackService
import kg.erjan.data.utils.MusicUtil
import kg.erjan.domain.entities.tracks.Tracks
import java.util.*

class MusicService : Service(), PlaybackService.PlaybackCallbacks,
    SharedPreferences.OnSharedPreferenceChangeListener {

    private var playbackService: PlaybackService = MusicServiceImpl(this)
    private val musicBind: IBinder = MusicBinder(this)
    private var position = -1
    private var playerHandler: PlaybackHandler? = null
    private var musicPlayerHandlerThread: HandlerThread = HandlerThread("PlaybackHandler")
    private var originalPlayerQueue = mutableListOf<Tracks>()
    private var playingQueue: ArrayList<Tracks> = ArrayList<Tracks>()

    override fun onCreate() {
        super.onCreate()
        musicPlayerHandlerThread.start()
        playerHandler = PlaybackHandler(this, musicPlayerHandlerThread.looper)
        playbackService.setCallbacks(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    private fun getSongAt(position: Int): Tracks {
        return playingQueue[position]
    }

    fun openQueue(playingQueue: List<Tracks>?, startPosition: Int, startingPlaying: Boolean) {
        if (playingQueue != null && playingQueue.isNotEmpty() && startPosition >= 0 && startPosition < playingQueue.size
        ) {
            originalPlayerQueue = ArrayList(playingQueue)
            this.playingQueue = ArrayList(originalPlayerQueue)
            if (startingPlaying) {
                playSongAt(startPosition)
            }
        }
    }

    private fun getCurrentSong(): Tracks {
        return getSongAt(position)
    }

    override fun onBind(p0: Intent?): IBinder {
        return musicBind
    }

    private fun play() {
        if (!playbackService.isPlaying) {
            if (!playbackService.isInitialized) {
                playSongAt(position)
            } else {
                playbackService.startMusic()
            }
        }
    }

    fun playSongAtImpl(position: Int) {
        if (openTrackAndPrepareNextAt(position)) {
            play()
        } else {
            Log.e("dontWorked", "playSongAtImpl: ")
        }
    }

    private fun playSongAt(position: Int) {
        // handle this on the handlers thread to avoid blocking the ui thread
        playerHandler!!.removeMessages(PLAY_SONG)
        playerHandler!!.obtainMessage(PLAY_SONG, position, 0).sendToTarget()
    }

    private fun openTrackAndPrepareNextAt(position: Int): Boolean {
        this.position = position
        val prepared = openCurrent()
        if (prepared) {
            prepareNextImpl()
        }
        return prepared
    }

    private fun prepareNextImpl(): Boolean {
        playbackService.setNextDataSource(getTrackUri(Objects.requireNonNull(getSongAt(position))))
        return true
    }

    private fun openCurrent(): Boolean = synchronized(this) {
        return playbackService.setDataSource(
            getTrackUri(
                Objects.requireNonNull(
                    getCurrentSong()
                )
            )
        )
    }

    private fun getTrackUri(tracks: Tracks): String {
        return MusicUtil().getSongFileUri(tracks.id).toString()
    }

    override fun onTrackWentToNext() {
    }

    override fun onTrackEnded() {
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
    }

    class MusicBinder(val service: MusicService) : Binder()

    companion object {
        const val PLAY_SONG = 3
    }
}