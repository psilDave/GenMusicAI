package com.psil.genmusicai.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psil.genmusicai.data.data.MusicResponse
import com.psil.genmusicai.data.repository.MusicRepository
import com.psil.genmusicai.ui.data.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicChatScreenViewModel @Inject constructor(private val repository: MusicRepository) :
    ViewModel() {
    private var _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()


    fun sendMessage(prompt: String, onFailure: () -> Unit) = viewModelScope.launch {

        _messages.value += ChatMessage(prompt, true)
        _messages.value += ChatMessage(content = "...", isUserMessage = false)

        val result = repository.generateMusic(prompt)

        result.fold(
            onSuccess = { musicResponseList: List<MusicResponse> ->
                setLoadingAnimationState()
                val formattedMusicResponse = musicResponseList.first().getFormattedMessage()
                _messages.value += ChatMessage(formattedMusicResponse, false)
            },

            onFailure = {
                setLoadingAnimationState()
                onFailure()
                Log.e("GenMusicAI", "Erro ao gerar a música:${it.localizedMessage}")
            }
        )
    }

    private fun setLoadingAnimationState() {
        _messages.value -= ChatMessage(content = "...", isUserMessage = false)
    }

}