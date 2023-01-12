package kg.erjan.data.remote.dto

import kg.erjan.data.utils.DataMapper
import kg.erjan.domain.entities.tracks.Tracks

class TracksDto(
    val id: Int,
    val title: String,
    val trackNumber: Int,
    val year: Int,
    val duration: Long,
    val data: String,
    val dateModified: Long,
    val albumId: Int,
    val albumName: String,
    val artistId: Int,
    val artistName: String,
    val composer: String?
) : DataMapper<Tracks> {
    override fun mapToDomain() = Tracks(
        id,
        title,
        trackNumber,
        year,
        duration,
        data,
        dateModified,
        albumId,
        albumName,
        artistId,
        artistName,
        composer
    )
    companion object {
        @JvmStatic
        val emptySong = TracksDto(
            -1,
            "",
            -1,
            -1,
            -1,
            "",
            -1,
            -1,
            "",
            -1,
            "",
            ""
        )
    }
}