package kg.erjan.data.remote.dto

import kg.erjan.data.utils.DataMapper
import kg.erjan.domain.entities.tracks.Tracks

class TracksDto(
    val path: String,
    val nameTrack: String,
    val albumTrack: String,
    val artistTrack: String
) : DataMapper<Tracks> {
    override fun mapToDomain() = Tracks(path, nameTrack, albumTrack, artistTrack)
}