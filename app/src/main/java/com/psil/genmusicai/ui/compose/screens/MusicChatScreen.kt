package com.psil.genmusicai.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.compose.utils.GenMusicAITopBar
import com.psil.genmusicai.ui.theme.GenMusicAITheme

@Composable
fun MusicChatScreen(onBackButtonClick: () -> Unit) {

    var currentMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            GenMusicAITopBar(
                title = R.string.music_chat_top_bar_title,
                description = R.string.music_chat_back_button_description
            ) {
                onBackButtonClick()
            }
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
                    .fillMaxWidth()
                    .padding(8.dp),
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
        MusicChatScreen {}
    }
}