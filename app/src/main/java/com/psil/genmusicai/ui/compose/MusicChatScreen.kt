package com.psil.genmusicai.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.theme.GenMusicAITheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicChatScreen() {

    var currentMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nova música") },
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
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = listOf("Item 1", "Item 1", "Item 1")) {
                    Text(text = it)
                }
            }
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = currentMessage,
                    onValueChange = { currentMessage = it },
                    label = {
                        Text(
                            text = stringResource(R.string.music_chat_text_field_label)
                        )
                    }
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = null)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MusicChatScreenPreview() {
    GenMusicAITheme {
        MusicChatScreen()
    }
}