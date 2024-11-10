package com.psil.genmusicai.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psil.genmusicai.data.data.MusicChatEntity
import com.psil.genmusicai.data.data.MusicResponse
import com.psil.genmusicai.data.repository.local.MusicChatRepository
import com.psil.genmusicai.data.repository.remote.MusicRemoteRepository
import com.psil.genmusicai.ui.data.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GenMusicAIViewModel @Inject constructor(
    private val repository: MusicRemoteRepository,
    private val localRepository: MusicChatRepository
) :
    ViewModel() {
    private var _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private var _firstMusicTitle: MutableStateFlow<String?> = MutableStateFlow(null)
    val firstMusicTitle: StateFlow<String?> = _firstMusicTitle.asStateFlow()

    private var _isNewChat: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    private var _chatDataId = MutableStateFlow<Int?>(null)

    private var _allChats = MutableStateFlow<List<MusicChatEntity>>(emptyList())
    val allChats: StateFlow<List<MusicChatEntity>> = _allChats.asStateFlow()

    private var _searchChats = MutableStateFlow<List<MusicChatEntity>>(emptyList())
    val searchChats: StateFlow<List<MusicChatEntity>> = _searchChats.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    localRepository.allChats().collect { chats ->
                        _allChats.emit(chats)
                    }
                } catch (e: Exception) {
                    Log.e(
                        "GenMusicAI",
                        "Erro ao resgatar as músicas armazenadas no banco de dados:${e.message}"
                    )
                }
            }

        }
    }

    fun sendMessage(prompt: String, onFailure: () -> Unit) = viewModelScope.launch {

        _messages.value += ChatMessage(prompt, true)
        _messages.value += ChatMessage(content = "...", isUserMessage = false)

        val result = repository.generateMusic(prompt)

        result.fold(
            onSuccess = { musicResponseList: List<MusicResponse> ->
                val id = musicResponseList.first().id
                checkMusicStatus(id, onFailure)
            },

            onFailure = {
                _messages.value = _messages.value.dropLast(1)
                onFailure()
                Log.e("GenMusicAI", "Erro ao gerar a música:${it.localizedMessage}")
            }
        )
    }

    private fun checkMusicStatus(id: String, onFailure: () -> Unit) = viewModelScope.launch {

        repeat(30) {

            val result = repository.getMusicInformation(id)
            result.fold(
                onSuccess = { response ->
                    val musicResponse = response.first()
                    if (musicResponse.status == "streaming") {
                        val formattedMusic = musicResponse.getFormattedMessage()
                        _messages.value =
                            _messages.value.dropLast(1) + (ChatMessage(formattedMusic, false))
                        if (_messages.value.size == 2) _firstMusicTitle.update { musicResponse.title }
                        return@launch
                    }
                },
                onFailure = {
                    Log.e("GenMusicAI", "Erro ao verificar status: ${it.localizedMessage}")
                    _messages.value = _messages.value.dropLast(1)
                    onFailure()
                }
            )
            delay(5000L)
        }
        _messages.value = _messages.value.dropLast(1)
    }

    private fun saveChat() = viewModelScope.launch {
        if (_messages.value.isNotEmpty() and (_isNewChat.value == true))
            localRepository.saveChat(_firstMusicTitle.value ?: "", messages.value)
    }

    private fun updateChat() = viewModelScope.launch {
        localRepository.updateChat(
            MusicChatEntity.fromMessagesWithID(
                _chatDataId.value,
                _firstMusicTitle.value,
                _messages.value
            )
        )

    }

    fun saveOrUpdateChatInDB() = run { if (_isNewChat.value == true) saveChat() else updateChat() }


    fun getStoredChat(musicChatEntity: MusicChatEntity) = viewModelScope.launch {

        _messages.update { musicChatEntity.getMessages() }
        _firstMusicTitle.update { musicChatEntity.chatTitle }
        _chatDataId.update { musicChatEntity.id }
        _isNewChat.update { false }

        Log.d(
            "GenMusicAI",
            "updateMessages - messages:${_firstMusicTitle.value} and _messages:${_messages.value}"
        )
    }


    fun searchChatMusicInDB(searchQuery: String) = viewModelScope.launch {

        _searchChats.update {
            _allChats.value.filter { musicChatEntity ->
                musicChatEntity.chatTitle.contains(
                    searchQuery,
                    ignoreCase = true
                )

            }
        }
    }

    fun deleteChat(selectedChat: MusicChatEntity?) = viewModelScope.launch {
        selectedChat?.let { localRepository.deleteChat(it) }
    }

    fun newMusic() = viewModelScope.launch {
        _messages.update { emptyList() }
        _firstMusicTitle.update { null }
        _chatDataId.update { null }
        _isNewChat.update { true }
    }

}