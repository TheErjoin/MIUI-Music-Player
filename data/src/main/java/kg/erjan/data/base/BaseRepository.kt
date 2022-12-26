package kg.erjan.data.base

import kg.erjan.data.utils.DataMapper
import kg.erjan.domain.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    protected fun <T> doRequestWithoutMapping(request: suspend () -> T) = flow {
        try {
            emit(Either.On(value = request()))
        } catch (ioException: Exception) {
            ioException.printStackTrace()
            emit(
                Either.Off(
                    ioException.localizedMessage ?: "Error Occurred!"
                )
            )
        }
    }

    /**
     * do Request for not api
     */
    protected fun <T : DataMapper<S>, S> doRequest(
        request: suspend () -> T
    ) = flow {
        request().let {
            try {
                emit(Either.On(it.mapToDomain()))
            } catch (ioException: Exception) {
                emit(Either.Off("Error"))
            }
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        val message = exception.localizedMessage ?: "Error Occurred"
        emit(Either.Off(message))
    }
}