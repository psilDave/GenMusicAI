package com.psil.genmusicai.ui.compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.compose.utils.GenMusicAINavigationBar
import com.psil.genmusicai.ui.navigation.data.GenMusicAIScreens
import com.psil.genmusicai.ui.theme.GenMusicAITheme
import com.psil.genmusicai.ui.viewmodel.MainScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onClickInMusicTab: () -> Unit,
    onCLickInSettingsTab: () -> Unit,
    onClickFloatingButton: () -> Unit,
    onClickInMusicItemList: () -> Unit
) {

    val selectedTab by rememberSaveable { mutableStateOf(GenMusicAIScreens.MAIN.name) }
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val viewModel: MainScreenViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, start = 8.dp),
                inputField = {
                    SearchBarDefaults.InputField(
                        query = query,
                        onQueryChange = { newQuery -> query = newQuery },
                        onSearch = { viewModel.searchChatMusicInDB(it) },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text(stringResource(R.string.main_screen_search_bar_hint)) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },

                ) {
                LazyColumn {
                    items(items = viewModel.uiState.value.musicChatSearch) { musicChat ->
                        Column(modifier = Modifier.clickable { onClickInMusicItemList() }) {
                            Text(text = musicChat.title)
                            Text(text = musicChat.summary)
                        }
                    }
                }
            }
        },
        bottomBar = {
            GenMusicAINavigationBar(selectedTab, onClickInMusicTab, onCLickInSettingsTab)

        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onClickFloatingButton() }) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }

        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                items(items = viewModel.uiState.value.musicChatStored) {

                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GenMusicAITheme {
        MainScreen({}, {}, {}, {})
    }
}