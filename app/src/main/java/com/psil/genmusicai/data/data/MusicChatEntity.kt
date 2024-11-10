package com.psil.genmusicai.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.psil.genmusicai.ui.data.ChatMessage

@Entity(tableName = "music_chats")
data class MusicChatEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val chatTitle: String,
    val messagesJson: String,
    val timestamp: Long
) {
    fun getMessages(): List<ChatMessage> {
        val type = object : TypeToken<List<ChatMessage>>() {}.type
        return Gson().fromJson(messagesJson, type)
    }

    companion object {
        fun fromMessages(chatTitle: String, messages: List<ChatMessage>): MusicChatEntity {
            val messagesJson = Gson().toJson(messages)
            return MusicChatEntity(
                chatTitle = chatTitle,
                messagesJson = messagesJson,
                timestamp = System.currentTimeMillis()
            )
        }

        fun fromMessagesWithID(id:Int?, chatTitle: String?, messages: List<ChatMessage>): MusicChatEntity {
            val messagesJson = Gson().toJson(messages)
            return MusicChatEntity(
                id = id ?: 0,
                chatTitle = chatTitle ?: "",
                messagesJson = messagesJson,
                timestamp = System.currentTimeMillis()
            )
        }
    }


}
