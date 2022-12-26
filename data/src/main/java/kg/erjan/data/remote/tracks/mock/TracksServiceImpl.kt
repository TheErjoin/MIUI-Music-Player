package kg.erjan.data.remote.tracks.mock

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import kg.erjan.data.remote.dto.TracksDto
import kg.erjan.data.remote.tracks.TracksService
import javax.inject.Inject

class TracksServiceImpl @Inject constructor(
    private val context: Context
) : TracksService {

    @SuppressLint("Recycle")
    override suspend fun fetchTracks(): List<TracksDto>  {
        val tracksList = mutableListOf<TracksDto>()

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE,
        )

        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val cursor: Cursor? = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )

        if (cursor != null){
            while (cursor.moveToNext()) {
                val path = cursor.getString(0)
                val artist = cursor.getString(1)
                val album = cursor.getString(2)
                val name = cursor.getString(3)
                val tracks = TracksDto(path, name, album, artist)

                tracksList.add(tracks)
            }
            cursor.close()
        }
        return tracksList
    }
}