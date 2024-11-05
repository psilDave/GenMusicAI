package com.psil.genmusicai.data.datasource

import com.psil.genmusicai.data.data.MusicResponse

interface MusicDataSource {

    suspend fun generateMusic(prompt: String): Result<List<MusicResponse>>
}