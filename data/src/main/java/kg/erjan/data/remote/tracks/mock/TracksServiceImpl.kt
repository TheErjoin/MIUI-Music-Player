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
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
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
            while (cursor.moveToNext()){
                val path = cursor.getString(0)
                val album = cursor.getString(1)
                val artist = cursor.getString(2)
                val name = path.substring(path.lastIndexOf("/") + 1)
                val tracks = TracksDto(path,name,album,artist)

                Log.e("tracks", " Album :$album,Name :$name")

                tracksList.add(tracks)
            }
            cursor.close()
        }
        return tracksList
    }
}