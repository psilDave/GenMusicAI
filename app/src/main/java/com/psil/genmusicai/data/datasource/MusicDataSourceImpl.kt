package com.psil.genmusicai.data.datasource

import android.util.Log
import com.psil.genmusicai.data.data.MusicRequest
import com.psil.genmusicai.data.data.MusicResponse
import com.psil.genmusicai.data.repository.MusicApiService
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.Result

class MusicDataSourceImpl @Inject constructor(retrofit: Retrofit.Builder) :
    MusicDataSource {

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
}