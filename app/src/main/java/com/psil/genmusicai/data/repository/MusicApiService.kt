package com.psil.genmusicai.data.repository

import com.psil.genmusicai.data.data.MusicRequest
import com.psil.genmusicai.data.data.MusicResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MusicApiService {

    @POST("generate")
    suspend fun generateMusic(@Body requestBody: MusicRequest): List<MusicResponse>
}