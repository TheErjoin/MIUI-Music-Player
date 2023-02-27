package kg.erjan.musicplayer.utils

import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore

class MusicUtil {

    fun getSongFileUri(songId: Int): Uri {
        return ContentUris.withAppendedId(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            songId.toLong()
        )
    }
}