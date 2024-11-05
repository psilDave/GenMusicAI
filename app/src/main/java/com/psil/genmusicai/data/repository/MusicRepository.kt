package com.psil.genmusicai.data.repository

import com.psil.genmusicai.data.data.MusicResponse

interface MusicRepository {

    suspend fun generateMusic(prompt: String): Result<List<MusicResponse>>
}