package kg.erjan.data.remote.service.music.playback

import android.os.Handler
import android.os.Looper
import android.os.Message
import kg.erjan.data.remote.service.music.MusicService
import java.lang.ref.WeakReference

class PlaybackHandler(service: MusicService, looper: Looper) : Handler(looper) {

    private val service: WeakReference<MusicService> = WeakReference(service)

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val service: MusicService = service.get() ?: return

        when (msg.what) {
            MusicService.PLAY_SONG -> {
                service.playSongAtImpl(msg.arg1)
            }
        }
    }

}