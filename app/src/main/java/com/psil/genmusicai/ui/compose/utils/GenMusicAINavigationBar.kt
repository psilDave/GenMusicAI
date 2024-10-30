package com.psil.genmusicai.ui.compose.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.navigation.data.GenMusicAIScreens

@Composable
fun GenMusicAINavigationBar(
    selectedTab: String,
    onClickInMusicTab: () -> Unit,
    onCLickInSettingsTab: () -> Unit,

) {
    var selectedTab1 = selectedTab

    NavigationBar {
        NavigationBarItem(
            selected = selectedTab1 == GenMusicAIScreens.MAIN.name,
            onClick = {
                selectedTab1 = GenMusicAIScreens.MAIN.name
                onClickInMusicTab()
            },
            icon = {
                Icon(
                    imageVector = if (selectedTab1 == GenMusicAIScreens.MAIN.name) Icons.Filled.Home
                    else Icons.Outlined.Home,
                    contentDescription = null
                )

            },
            label = { Text(stringResource(R.string.main_screen_navigation_item_music)) })

        NavigationBarItem(
            selected = selectedTab1 == GenMusicAIScreens.SETTINGS.name,
            onClick = {
                selectedTab1 = GenMusicAIScreens.SETTINGS.name
                onCLickInSettingsTab()
            },
            icon = {
                Icon(
                    imageVector = if (selectedTab1 == GenMusicAIScreens.SETTINGS.name) Icons.Filled.Settings
                    else Icons.Outlined.Settings,
                    contentDescription = null
                )

            },
            label = { Text(stringResource(R.string.main_screen_navigation_item_settings)) })
    }
}