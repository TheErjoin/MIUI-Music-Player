package kg.erjan.data.remote.tracks.mock

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
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

        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST
        )
        val cursor: Cursor? = context.contentResolver.query(
            uri,
            projection,
            MediaStore.Audio.Media.DATA + " like ? ",
            arrayOf("%yourFolderName%"),
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