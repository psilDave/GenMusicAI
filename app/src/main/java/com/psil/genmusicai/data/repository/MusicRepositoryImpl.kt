package com.psil.genmusicai.data.repository

import com.psil.genmusicai.data.data.MusicResponse
import com.psil.genmusicai.data.datasource.MusicDataSource
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val musicDataSource: MusicDataSource) :
    MusicRepository {

    override suspend fun generateMusic(prompt: String): Result<List<MusicResponse>> {
        return musicDataSource.generateMusic(prompt)
    }
}