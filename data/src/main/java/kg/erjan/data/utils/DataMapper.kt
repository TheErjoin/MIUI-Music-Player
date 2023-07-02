package kg.erjan.data.utils

interface DataMapper<T> {
    fun mapToDomain(): T
}