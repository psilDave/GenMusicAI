package com.psil.genmusicai.data.datasource.remote

import android.util.Log
import com.psil.genmusicai.data.data.MusicRequest
import com.psil.genmusicai.data.data.MusicResponse
import com.psil.genmusicai.data.repository.remote.MusicApiService
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.Result

class MusicRemoteDataSourceImpl @Inject constructor(retrofit: Retrofit.Builder) :
    MusicRemoteDataSource {

    private val musicApiService = retrofit.build().create(MusicApiService::class.java)

    override suspend fun generateMusic(prompt: String): Result<List<MusicResponse>> {
        return try {
            val requestBody = MusicRequest(prompt)
            val response = musicApiService.generateMusic(requestBody)
            Log.d("GenMusicAI", "response:${response.first()}")
            Result.success(response)
        } catch (e: Exception) {
            Log.e("GenMusicAI", "Request falhou:${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getMusicInformation(ids: String): Result<List<MusicResponse>> {
        return try {
            val response = musicApiService.getMusicInformation(ids)
            Log.d("GenMusicAI", "Music info response: $response")
            Result.success(response)
        } catch (e: Exception) {
            Log.e("GenMusicAi", "Music info request falhou: ${e.message}")
            Result.failure(e)
        }
    }
}