package com.psil.genmusicai.di

import android.content.Context
import androidx.room.Room
import com.psil.genmusicai.data.dao.MusicChatDao
import com.psil.genmusicai.data.datasource.local.MusicChatDatabase
import com.psil.genmusicai.data.datasource.remote.MusicRemoteDataSource
import com.psil.genmusicai.data.datasource.remote.MusicRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun providesMusicRemoteDataSource(retrofitBuilder: Retrofit.Builder): MusicRemoteDataSource {
        return MusicRemoteDataSourceImpl(retrofitBuilder)
    }

    @Provides
    fun providesChatMessageDataSource(@ApplicationContext context: Context): MusicChatDatabase {
        return Room.databaseBuilder(context, MusicChatDatabase::class.java, "music_chat_database")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesMusicChatDao(database: MusicChatDatabase): MusicChatDao {
        return database.musicChatDao()
    }

}