package com.psil.genmusicai.data.datasource.remote

import com.psil.genmusicai.data.data.MusicResponse

interface MusicRemoteDataSource {

    suspend fun generateMusic(prompt: String): Result<List<MusicResponse>>

    suspend fun getMusicInformation(ids:String): Result<List<MusicResponse>>
}