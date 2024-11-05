package com.psil.genmusicai.di

import com.psil.genmusicai.data.datasource.MusicDataSource
import com.psil.genmusicai.data.repository.MusicRepository
import com.psil.genmusicai.data.repository.MusicRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UIModule {

    @Provides
    fun providesMusicRepository(musicDataSource: MusicDataSource): MusicRepository {
        return MusicRepositoryImpl(musicDataSource)
    }


}