package com.psil.genmusicai.ui.compose.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psil.genmusicai.R
import com.psil.genmusicai.data.data.MusicChatEntity
import com.psil.genmusicai.ui.compose.dialogs.AlertDialogMusicChat
import com.psil.genmusicai.ui.compose.utils.GenMusicAINavigationBar
import com.psil.genmusicai.ui.extensions.formatTime
import com.psil.genmusicai.ui.navigation.data.GenMusicAIScreens
import com.psil.genmusicai.ui.theme.GenMusicAITheme
import com.psil.genmusicai.ui.viewmodel.GenMusicAIViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    genMusicAIViewModel: GenMusicAIViewModel,
    onClickInMusicTab: () -> Unit,
    onCLickInSettingsTab: () -> Unit,
    onClickFloatingButton: () -> Unit,
    onClickInMusicItemList: () -> Unit
) {

    val selectedTab by rememberSaveable { mutableStateOf(GenMusicAIScreens.MAIN.name) }
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val chats by genMusicAIViewModel.allChats.collectAsState()
    val searchChats by genMusicAIViewModel.searchChats.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var selectedChat by remember { mutableStateOf<MusicChatEntity?>(null) }

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, start = 8.dp),
                inputField = {
                    SearchBarDefaults.InputField(
                        query = query,
                        onQueryChange = { newQuery ->
                            query = newQuery
                            genMusicAIViewModel.searchChatMusicInDB(newQuery)
                        },
                        onSearch = { genMusicAIViewModel.searchChatMusicInDB(it) },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text(stringResource(R.string.main_screen_search_bar_hint)) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier.wrapContentSize(),
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },

                ) {
                LazyColumn {
                    items(items = searchChats) { searchMusicChat ->
                        ChatItem(
                            title = searchMusicChat.chatTitle,
                            timestamp = searchMusicChat.timestamp,
                            modifier = Modifier
                                .clickable {
                                    onClickInMusicItemList()
                                    genMusicAIViewModel.getStoredChat(searchMusicChat)
                                }

                        )
                    }
                }
            }
        },
        bottomBar = {
            GenMusicAINavigationBar(selectedTab, onClickInMusicTab, onCLickInSettingsTab)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                genMusicAIViewModel.newMusic()
                onClickFloatingButton()
            }) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }

        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                items(items = chats) { chat ->
                    ChatItem(
                        title = chat.chatTitle,
                        timestamp = chat.timestamp,
                        modifier = Modifier
                            .combinedClickable(
                                onClick = {
                                    onClickInMusicItemList()
                                    genMusicAIViewModel.getStoredChat(chat)
                                },
                                onLongClick = {
                                    selectedChat = chat
                                    showDialog = true
                                }
                            )
                    )
                }
            }
        }
    }

    if (showDialog) {
        AlertDialogMusicChat(
            onConfirm = {
                genMusicAIViewModel.deleteChat(selectedChat)
                showDialog = false
            },
            onDismiss = { showDialog = false },
            showDialog = showDialog
        )
    }
}

@Composable
fun ChatItem(title: String, timestamp: Long, modifier: Modifier) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = formatTime(timestamp),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GenMusicAITheme {
        ChatItem(title = "Test", timestamp = System.currentTimeMillis(), modifier = Modifier)
    }
}