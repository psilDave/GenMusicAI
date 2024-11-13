package com.psil.genmusicai.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesRetrofit(okHttpClientBuilder: OkHttpClient.Builder): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(TODO("retirei a url para não vazar a chave da API"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                okHttpClientBuilder.build()
            )
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
    }
}
