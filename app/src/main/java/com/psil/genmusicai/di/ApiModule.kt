package com.psil.genmusicai.di

import com.psil.genmusicai.data.authenticator.AuthInterceptor
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
            .baseUrl("https://api.aimlapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                okHttpClientBuilder.build()
            )
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
    }
}
