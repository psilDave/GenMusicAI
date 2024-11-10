package com.psil.genmusicai.data.repository.local

import com.psil.genmusicai.data.data.MusicChatEntity
import com.psil.genmusicai.ui.data.ChatMessage
import kotlinx.coroutines.flow.Flow

interface MusicChatRepository {

    suspend fun saveChat(chatTitle: String, messages: List<ChatMessage>)

    suspend fun allChats(): Flow<List<MusicChatEntity>>

    suspend fun deleteChat(musicChat: MusicChatEntity)

    suspend fun updateChat(musicChat: MusicChatEntity)
}