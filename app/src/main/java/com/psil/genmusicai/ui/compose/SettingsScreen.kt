package com.psil.genmusicai.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.data.settingsItemsList
import com.psil.genmusicai.ui.theme.GenMusicAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.settings_screen_topbar_title)) })
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.settings_screen_general_title)
                )
            }
            items(settingsItemsList) { configItem ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        configItem.onClickAction.invoke()
                    }) {
                    Icon(painter = painterResource(id = configItem.icon), contentDescription = null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = stringResource(id = configItem.title))
                }

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    GenMusicAITheme {
        SettingsScreen()
    }
}
