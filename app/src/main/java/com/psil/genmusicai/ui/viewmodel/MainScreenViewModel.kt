package com.psil.genmusicai.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psil.genmusicai.ui.data.MainScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(musicChatStored = listOf()) } // Change to repository
            } catch (e: Exception) {
                Log.e("GenMusicAI", "Erro ao resgatar as músicas armazenadas no banco de dados")
            }
        }
    }

    fun searchChatMusicInDB(searchQuery: String) = viewModelScope.launch {

        _uiState.update {
            it.copy(musicChatSearch = it.musicChatStored.filter { musicChats ->
                musicChats.title.contains(
                    searchQuery,
                    ignoreCase = true
                )
            })
        }
    }
}