package kg.erjan.data.remote.service.tracks.impl

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import kg.erjan.data.local.preferences.music.MusicPreferencesData
import kg.erjan.data.remote.dto.TracksDto
import kg.erjan.data.remote.service.tracks.TracksService
import kg.erjan.data.utils.Constants.BASE_SELECTION
import kg.erjan.data.utils.Constants.baseProjection
import javax.inject.Inject

class TracksServiceImpl @Inject constructor(
    private val context: Context,
    private val musicPreferencesData: MusicPreferencesData
) : TracksService {

    override suspend fun fetchTracks(): List<TracksDto> {
        val cursor = makeSongCursor(context, null, null)
        return getSongs(cursor)
    }

    private fun getSongs(
        cursor: Cursor?
    ): List<TracksDto> {
        val songs = mutableListOf<TracksDto>()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                songs.add(getSongFromCursorImpl(cursor))
            } while (cursor.moveToNext())
        }

        cursor?.close()
        return songs
    }

    @JvmOverloads
    fun makeSongCursor(
        context: Context,
        selection: String?,
        selectionValues: Array<String>?,
        sortOrder: String = musicPreferencesData.isSongSortOrder
    ): Cursor? {
        var selectionFinal = selection
        selectionFinal = if (selection != null && selection.trim { it <= ' ' } != "") {
            "$BASE_SELECTION AND $selectionFinal"
        } else {
            BASE_SELECTION
        }

        return try {
            context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                baseProjection,
                selectionFinal + " AND " + MediaStore.Audio.Media.DURATION + ">= " + (musicPreferencesData.filterLength * 1000),
                selectionValues,
                sortOrder
            )
        } catch (e: SecurityException) {
            null
        }
    }

    private fun getSongFromCursorImpl(
        cursor: Cursor
    ): TracksDto {
        val id = cursor.getInt(0)
        val title = cursor.getString(1)
        val trackNumber = cursor.getInt(2)
        val year = cursor.getInt(3)
        val duration = cursor.getLong(4)
        val data = cursor.getString(5)
        val dateModified = cursor.getLong(6)
        val albumId = cursor.getInt(7)
        val albumName = cursor.getString(8)
        val artistId = cursor.getInt(9)
        val artistName = cursor.getString(10)
        val composer = cursor.getString(11)

        return TracksDto(
            id,
            title,
            trackNumber,
            year,
            duration,
            data,
            dateModified,
            albumId,
            if (albumName == "Download") "unknown" else albumName,
            artistId,
            artistName,
            composer ?: ""
        )
    }
}