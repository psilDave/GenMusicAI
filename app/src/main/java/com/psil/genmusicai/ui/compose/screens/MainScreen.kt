package com.psil.genmusicai.ui.compose.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.navigation.data.GenMusicAIScreens
import com.psil.genmusicai.ui.compose.utils.GenMusicAINavigationBar
import com.psil.genmusicai.ui.theme.GenMusicAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onClickInMusicTab: () -> Unit,
    onCLickInSettingsTab: () -> Unit,
    onClickFloatingButton: () -> Unit
) {

    val selectedTab by rememberSaveable { mutableStateOf(GenMusicAIScreens.MAIN.name) }

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, start = 8.dp),
                inputField = {
                    SearchBarDefaults.InputField(query = "", // TODO("Valor mostrado na pesquisa")
                        onSearch = { TODO("Realizar a pesquisa no banco de dados") },
                        expanded = false,
                        onExpandedChange = {},
                        placeholder = { Text(stringResource(R.string.main_screen_search_bar_hint)) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        onQueryChange = { TODO("Verificar se existe alguma conversa com esse nome") })
                },
                expanded = false,
                onExpandedChange = { TODO() },
            ) {
                TODO("Mostrar pesquisas feitas anteriormente como sugestão de pesquisa")
            }
        }, bottomBar = {
            GenMusicAINavigationBar(selectedTab, onClickInMusicTab, onCLickInSettingsTab)

        }, floatingActionButton = {
            FloatingActionButton(onClick = { onClickFloatingButton() }) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }

        }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GenMusicAITheme {
        MainScreen({}, {}, {})
    }
}