package com.psil.genmusicai.ui.compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psil.genmusicai.R
import com.psil.genmusicai.navigation.data.GenMusicAIScreens
import com.psil.genmusicai.ui.compose.GenMusicAINavigationBar
import com.psil.genmusicai.ui.compose.bottomsheet.ShowAboutBottomSheet
import com.psil.genmusicai.ui.theme.GenMusicAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onClickInMusicTab: () -> Unit,
    onCLickInSettingsTab: () -> Unit,
    onClickInIAModel: () -> Unit
) {

    var selectedTab by rememberSaveable { mutableStateOf(GenMusicAIScreens.SETTINGS.name) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.settings_screen_topbar_title)) })
        },
        bottomBar = {
            GenMusicAINavigationBar(
                selectedTab = selectedTab,
                onClickInMusicTab = { onClickInMusicTab() },
                onCLickInSettingsTab = { onCLickInSettingsTab() })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(R.string.settings_screen_general_title),

                )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    onClickInIAModel()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings_screen_neurology_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = stringResource(id = R.string.settings_screen_ia_model_item_title))
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    showBottomSheet = true
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings_screen_about_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = stringResource(id = R.string.settings_screen_about_item_title))
            }
        }
    }

    // Mostrar o BottomSheet quando showBottomSheet for true
    ShowAboutBottomSheet(sheetState = sheetState, onDismiss = { showBottomSheet = false })

    LaunchedEffect(key1 = showBottomSheet) {
        if (showBottomSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    GenMusicAITheme {
        SettingsScreen({}, {}, {})
    }
}
