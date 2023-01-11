package kg.erjan.musicplayer.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kg.erjan.data.local.preferences.PreferencesHelper
import kg.erjan.data.local.preferences.music.MusicPreferencesData
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferencesHelper(
        @ApplicationContext context: Context
    ) = PreferencesHelper(context)

    @Provides
    @Singleton
    fun provideMusicPreferencesData(
        preferencesHelper: PreferencesHelper
    ) = MusicPreferencesData(preferencesHelper)


}