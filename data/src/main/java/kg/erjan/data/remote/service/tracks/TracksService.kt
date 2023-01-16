package kg.erjan.data.remote.service.tracks

import kg.erjan.data.remote.dto.TracksDto
import retrofit2.Response

interface TracksService {

    suspend fun  fetchTracks(): Response<List<TracksDto>>

}