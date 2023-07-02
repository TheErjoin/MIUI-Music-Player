package kg.erjan.domain.utils

sealed class Either<out A, out B> {
    class On<out A>(val value: A): Either<A,Nothing>()
    class Off<out B>(val value: B): Either<Nothing,B>()
}