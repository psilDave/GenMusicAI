package com.psil.genmusicai.ui.data

import com.psil.genmusicai.data.data.MusicChats

data class MainScreenUiState(
    val musicChatStored: List<MusicChats> = emptyList(),
    val musicChatSearch: List<MusicChats> = emptyList()
)
