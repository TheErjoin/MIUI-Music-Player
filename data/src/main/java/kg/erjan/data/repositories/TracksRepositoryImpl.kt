package kg.erjan.data.repositories

import kg.erjan.data.base.BaseRepository
import kg.erjan.data.remote.service.tracks.TracksService
import kg.erjan.domain.repositories.TracksRepository
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val service: TracksService
) : TracksRepository, BaseRepository() {

    override fun fetchTracks() = doRequestWithoutMapping {
        service.fetchTracks().map {
            it.mapToDomain()
        }
    }
}