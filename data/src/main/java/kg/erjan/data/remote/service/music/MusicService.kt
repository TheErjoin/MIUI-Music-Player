package kg.erjan.data.remote.service.music

import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kg.erjan.data.remote.service.music.mock.MusicServiceImpl
import kg.erjan.data.remote.service.music.playback.PlaybackService
import kg.erjan.data.utils.MusicUtil
import kg.erjan.domain.entities.tracks.Tracks
import java.util.*

class MusicService : Service(), PlaybackService.PlaybackCallbacks,
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val playbackService: PlaybackService by lazy {
        MusicServiceImpl(this)
    }
    private val musicBind: IBinder = MusicBinder(this)
    private var position = -1
    private var originalPlayerQueue = mutableListOf<Tracks>()
    private var playingQueue: ArrayList<Tracks> = ArrayList<Tracks>()

    private fun getSongAt(position: Int): Tracks {
        return playingQueue[position]
    }

    fun openQueue(playingQueue: List<Tracks>?, startPosition: Int, startingPlaying: Boolean) {
        if (playingQueue != null && playingQueue.isNotEmpty() && startPosition >= 0 && startPosition < playingQueue.size
        ) {
            originalPlayerQueue = ArrayList(playingQueue)
            this.playingQueue = ArrayList(originalPlayerQueue)
            if (startingPlaying) {
                playSongAtImpl(startPosition)
            }
        }
    }

    private fun getCurrentSong(): Tracks {
        return getSongAt(position)
    }

    override fun onBind(p0: Intent?): IBinder {
        return musicBind
    }

    private fun playSongAtImpl(position: Int) {
        Log.e(
            "currentOpen",
            "playSongAtImpl: ${openTrackAndPrepareNextAt(position)},${openCurrent()}"
        )
        if (openTrackAndPrepareNextAt(position)) {
            play()
        } else {
            Log.e("dontWorked", "playSongAtImpl: ")
        }
    }

    private fun play() {
        playbackService.startMusic()
    }

    private fun openTrackAndPrepareNextAt(position: Int): Boolean {
        Log.e("position", "openTrackAndPrepareNextAt: $position")
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

    private fun openCurrent(): Boolean {
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

    class MusicBinder(val service: MusicService) : Binder()

    override fun onTrackWentToNext() {
    }

    override fun onTrackEnded() {
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
    }
}