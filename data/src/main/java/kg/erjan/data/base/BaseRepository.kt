package kg.erjan.data.base

import android.util.Log
import kg.erjan.data.utils.DataMapper
import kg.erjan.domain.utils.Either
import kg.erjan.musicplayer.data.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T> doRequestWithoutMapping(
        request: suspend () -> Response<T>
    ) = flow {
        request().let {
            when {
                it.isSuccessful && it.body() != null -> {
                    emit(Either.On(it.body()!!))
                }
                !it.isSuccessful -> {
                    emit(Either.Off("Error !!"))
                }
            }
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        val message = exception.localizedMessage ?: "Error Occurred"
        if (BuildConfig.DEBUG) {
            Log.e("${this@BaseRepository.javaClass.simpleName}", message)
        }
        emit(Either.Off(message))
    }

    protected fun <T : DataMapper<S>, S> doRequestForList(
        request: suspend () -> Response<List<T>>
    ) = flow {
        request().let {
            when {
                it.isSuccessful && it.body() != null -> {
                    emit(Either.On(it.body()!!.map { data -> data.mapToDomain() }))
                }
                !it.isSuccessful -> {
                    emit(Either.Off("Error!!"))
                }
            }
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        val message = exception.localizedMessage ?: "Error Occurred"
        if (BuildConfig.DEBUG) {
            Log.e("${this@BaseRepository.javaClass.simpleName}", message)
        }
        emit(Either.Off(message))
    }

    protected fun <T : DataMapper<S>, S> doRequest(
        request: suspend () -> Response<T>
    ) = flow {
        request().let {
            when {
                it.isSuccessful && it.body() != null -> {
                    emit(Either.On(it.body()!!.mapToDomain()))
                }
                !it.isSuccessful -> {
                    emit(Either.Off("Error !!"))
                }
            }
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        val message = exception.localizedMessage ?: "Error Occurred"
        if (BuildConfig.DEBUG) {
            Log.e("${this@BaseRepository.javaClass.simpleName}", message)
        }
        emit(Either.Off(message))
    }
}