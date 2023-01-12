package kg.erjan.data.utils

import android.provider.MediaStore

class SortOrder {

    interface SongSortOrder {
        companion object {
            const val SONG_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER
            const val SONG_Z_A = "$SONG_A_Z DESC"
            const val SONG_ARTIST = MediaStore.Audio.Artists.DEFAULT_SORT_ORDER
            const val SONG_ALBUM = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER
            const val SONG_YEAR = MediaStore.Audio.Media.YEAR + " Desc"
            const val SONG_DURATION = MediaStore.Audio.Media.DURATION + " Desc"
            const val SONG_DATE = MediaStore.Audio.Media.DATE_ADDED + " Desc"
            const val SONG_DATE_MODIFIED = MediaStore.Audio.Media.DATE_MODIFIED + " DESC"
            const val COMPOSER = MediaStore.Audio.Media.COMPOSER
        }
    }
}