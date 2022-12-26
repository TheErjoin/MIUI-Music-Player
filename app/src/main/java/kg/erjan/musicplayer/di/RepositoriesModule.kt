package kg.erjan.musicplayer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.erjan.data.repositories.TracksRepositoryImpl
import kg.erjan.domain.repositories.TracksRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindTrackRepository(
        tracksRepositoryImpl: TracksRepositoryImpl
    ):TracksRepository
}