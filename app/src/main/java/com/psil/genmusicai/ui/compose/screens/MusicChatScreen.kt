package com.psil.genmusicai.ui.compose.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.compose.utils.GenMusicAITopBar
import com.psil.genmusicai.ui.data.ChatMessage
import com.psil.genmusicai.ui.theme.GenMusicAITheme
import com.psil.genmusicai.ui.viewmodel.GenMusicAIViewModel

@Composable
fun MusicChatScreen(genMusicAIViewModel: GenMusicAIViewModel, onBackButtonClick: () -> Unit) {

    var currentMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    val listState = rememberLazyListState()
    val messages by genMusicAIViewModel.messages.collectAsState()

    LaunchedEffect(messages.size, currentMessage) {
        if (messages.isNotEmpty()) {
            listState.scrollToItem(messages.size - 1)
        }
    }

    BackHandler {
        genMusicAIViewModel.saveOrUpdateChatInDB()
        onBackButtonClick()
    }

    Scaffold(
        topBar = {
            GenMusicAITopBar(
                title = genMusicAIViewModel.firstMusicTitle.value
                    ?: stringResource(R.string.music_chat_top_bar_title),
                description = R.string.music_chat_back_button_description
            ) {
                genMusicAIViewModel.saveOrUpdateChatInDB()
                onBackButtonClick()
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .imePadding()
        ) {

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                items(items = messages) { message ->

                    Log.d("GenMusicAI", "message content: ${message.content}")
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (message.isUserMessage) {
                            UserChatItem(
                                message,
                                Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 12.dp, top = 8.dp)
                            )
                        } else if (message.content == "...") {
                            LoadingChatItem(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 12.dp, top = 8.dp)
                            )
                        } else {
                            IAModelChatItem(
                                message,
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 12.dp, top = 8.dp)
                            )
                        }
                    }
                }
            }
            Row(
                Modifier
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
                IconButton(onClick = {
                    genMusicAIViewModel.sendMessage(currentMessage) {
                        Toast.makeText(
                            context,
                            "Não foi possivel gerar a música pedida",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    currentMessage = ""
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun IAModelChatItem(message: ChatMessage, modifier: Modifier) {

    val parts = message.content.split("[")
    val imageUrl = parts.find { it.startsWith("imagem]") }?.substringAfter("]")
    val title = parts.firstOrNull()
    val audioUrl = parts.find { it.startsWith("link]") }?.substringAfter("]")
    val context = LocalContext.current


    Card(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(audioUrl))
            intent.setDataAndType(Uri.parse(audioUrl), "audio/*")
            context.startActivity(intent)
        },
        modifier = modifier
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(id = R.drawable.ia_model_placeholder_image),
                contentDescription = "Imagem gerada",
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title.toString(),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}

@Composable
fun UserChatItem(message: ChatMessage, modifier: Modifier) {
    Card(modifier = modifier) {
        Text(
            text = message.content, Modifier
                .padding(8.dp)
                .width(300.dp)
                .wrapContentHeight()
        )
    }
}

@Composable
fun LoadingChatItem(modifier: Modifier) {
    Card(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp)
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MusicChatScreenPreview() {
    GenMusicAITheme {
        IAModelChatItem(message = ChatMessage("teste", false), modifier = Modifier)

    }
}