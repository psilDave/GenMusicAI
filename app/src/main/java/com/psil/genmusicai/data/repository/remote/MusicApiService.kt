package com.psil.genmusicai.data.repository.remote

import com.psil.genmusicai.data.data.MusicRequest
import com.psil.genmusicai.data.data.MusicResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MusicApiService {

    @POST("generate")
    suspend fun generateMusic(@Body requestBody: MusicRequest): List<MusicResponse>

    @GET("get")
    suspend fun getMusicInformation(@Query("ids") ids: String): List<MusicResponse>
}