package kg.erjan.domain.utils

import kotlinx.coroutines.flow.Flow

internal typealias RemoteWrapper<T> = Flow<Either<T,String>>
