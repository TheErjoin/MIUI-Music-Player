package kg.erjan.domain.usecases.tracks

import kg.erjan.domain.repositories.TracksRepository
import javax.inject.Inject

class FetchTracksUseCase @Inject constructor(
    private val repository: TracksRepository
) {
    operator fun invoke() = repository.fetchTracks()
}