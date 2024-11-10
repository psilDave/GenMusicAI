package com.psil.genmusicai.di

import com.psil.genmusicai.data.dao.MusicChatDao
import com.psil.genmusicai.data.datasource.remote.MusicRemoteDataSource
import com.psil.genmusicai.data.repository.local.MusicChatRepository
import com.psil.genmusicai.data.repository.local.MusicChatRepositoryImpl
import com.psil.genmusicai.data.repository.remote.MusicRemoteRepository
import com.psil.genmusicai.data.repository.remote.MusicRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UIModule {

    @Provides
    fun providesMusicRemoteRepository(musicRemoteDataSource: MusicRemoteDataSource): MusicRemoteRepository {
        return MusicRemoteRepositoryImpl(musicRemoteDataSource)
    }

    @Provides
    fun providesMusicChatRepository(musicChatDao: MusicChatDao): MusicChatRepository {
        return MusicChatRepositoryImpl(musicChatDao)
    }


}