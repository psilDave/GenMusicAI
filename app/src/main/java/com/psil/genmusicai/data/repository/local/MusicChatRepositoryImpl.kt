package com.psil.genmusicai.data.repository.local

import com.psil.genmusicai.data.dao.MusicChatDao
import com.psil.genmusicai.data.data.MusicChatEntity
import com.psil.genmusicai.ui.data.ChatMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MusicChatRepositoryImpl @Inject constructor(private val musicChatDao: MusicChatDao) :
    MusicChatRepository {
    override suspend fun saveChat(chatTitle: String, messages: List<ChatMessage>) {
        val musicChatEntity = MusicChatEntity.fromMessages(chatTitle, messages)
        musicChatDao.insertMusicChat(musicChatEntity)
    }

    override suspend fun allChats(): Flow<List<MusicChatEntity>> =
        musicChatDao.getAllMusicChats().flowOn(Dispatchers.IO)

    override suspend fun deleteChat(musicChat: MusicChatEntity) {
        musicChatDao.deleteChat(musicChat)
    }

    override suspend fun updateChat(musicChat: MusicChatEntity) {
        musicChatDao.updateChat(musicChat)
    }
}