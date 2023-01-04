package kg.erjan.musicplayer.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kg.erjan.data.remote.service.tracks.TracksService
import kg.erjan.data.remote.service.tracks.mock.TracksServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideTracksService(
        @ApplicationContext context: Context
    ): TracksService {
        return TracksServiceImpl(context)
    }
}