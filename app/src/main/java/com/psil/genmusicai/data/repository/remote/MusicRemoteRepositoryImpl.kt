package com.psil.genmusicai.data.repository.remote

import com.psil.genmusicai.data.data.MusicResponse
import com.psil.genmusicai.data.datasource.remote.MusicRemoteDataSource
import javax.inject.Inject

class MusicRemoteRepositoryImpl @Inject constructor(private val musicRemoteDataSource: MusicRemoteDataSource) :
    MusicRemoteRepository {

    override suspend fun generateMusic(prompt: String): Result<List<MusicResponse>> {
        return musicRemoteDataSource.generateMusic(prompt)
    }

    override suspend fun getMusicInformation(ids: String): Result<List<MusicResponse>> {
        return musicRemoteDataSource.getMusicInformation(ids)
    }

}