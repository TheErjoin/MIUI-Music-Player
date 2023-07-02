package kg.erjan.domain.repositories

import kg.erjan.domain.entities.tracks.Tracks
import kg.erjan.domain.utils.RemoteWrapper

interface TracksRepository {

    fun fetchTracks(): RemoteWrapper<List<Tracks>>

}