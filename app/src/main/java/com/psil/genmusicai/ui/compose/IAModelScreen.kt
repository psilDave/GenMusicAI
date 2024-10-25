package com.psil.genmusicai.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.data.iAModelList
import com.psil.genmusicai.ui.theme.GenMusicAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IAModelScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.ia_model_screen_topbar_title)) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.music_chat_back_button_description
                            )
                        )
                    }
                }
            )
        },

    ) { innerPadding ->

        Column (modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            // Conteúdo da tela
            var expanded by remember { mutableStateOf(false) }
            val iaModelItems = iAModelList
            var selectedItem by remember { mutableIntStateOf(iaModelItems[0].title) }

            Text(text = stringResource(R.string.ia_model_screen_description))

            Box {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(selectedItem),
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown arrow")
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    iaModelItems.forEach { iaModelItem ->
                        DropdownMenuItem(
                            text = { Text(text = stringResource(iaModelItem.title))},
                            onClick = {
                                selectedItem = iaModelItem.title
                                expanded = false
                            },

                        )
                    }
                }
            }


        }

    }

}

@Composable
@Preview(showBackground = true)
fun PreviewIAModelScreen() {
    GenMusicAITheme {
        IAModelScreen()
    }
}