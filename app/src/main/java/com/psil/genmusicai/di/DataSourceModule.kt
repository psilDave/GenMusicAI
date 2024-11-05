package com.psil.genmusicai.di

import com.psil.genmusicai.data.datasource.MusicDataSource
import com.psil.genmusicai.data.datasource.MusicDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun providesMusicDataSource(retrofitBuilder: Retrofit.Builder): MusicDataSource {
        return MusicDataSourceImpl(retrofitBuilder)
    }

}