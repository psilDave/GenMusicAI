package com.psil.genmusicai.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.psil.genmusicai.data.data.MusicChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicChatDao {

    @Insert
    suspend fun insertMusicChat(chat: MusicChatEntity)

    @Query("SELECT * FROM music_chats ORDER BY timestamp DESC")
    fun getAllMusicChats(): Flow<List<MusicChatEntity>>

    @Query("SELECT * FROM music_chats WHERE id = :musicChatId")
    suspend fun getMusicChatById(musicChatId: Int): MusicChatEntity?

    @Delete
    suspend fun deleteChat(chat: MusicChatEntity)

    @Update
    suspend fun updateChat(chat: MusicChatEntity)
}
