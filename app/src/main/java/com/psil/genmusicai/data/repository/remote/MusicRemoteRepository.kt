package com.psil.genmusicai.data.repository.remote

import com.psil.genmusicai.data.data.MusicResponse

interface MusicRemoteRepository {

    suspend fun generateMusic(prompt: String): Result<List<MusicResponse>>

    suspend fun getMusicInformation(ids: String): Result<List<MusicResponse>>


}