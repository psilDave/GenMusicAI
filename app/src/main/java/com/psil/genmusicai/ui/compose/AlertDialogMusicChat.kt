package com.psil.genmusicai.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.theme.GenMusicAITheme


@Composable
fun AlertDialogMusicChat(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            title = { Text(text = stringResource(R.string.alert_dialog_music_chat_title)) },
            text = { Text(text = stringResource(R.string.alert_dialog_music_chat_description)) },
            icon = { Icon(imageVector = Icons.Outlined.Delete, contentDescription = null) },
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = {
                        onConfirm()
                        showDialog = false
                    }) {
                    Text(text = stringResource(R.string.alert_dialog_music_chat_confirm_button))
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = {
                        onDismiss()
                        showDialog = false
                    }) {
                    Text(text = stringResource(R.string.alert_dialog_music_chat_dimiss_button))
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAlertDialogMusicChat() {
    GenMusicAITheme {
        AlertDialogMusicChat(onConfirm = {}, onDismiss = {})
    }
}