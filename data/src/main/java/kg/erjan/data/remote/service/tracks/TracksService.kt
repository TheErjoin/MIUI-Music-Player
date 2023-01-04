package kg.erjan.data.remote.service.tracks

import kg.erjan.data.remote.dto.TracksDto

interface TracksService {

    suspend fun  fetchTracks(): List<TracksDto>

}