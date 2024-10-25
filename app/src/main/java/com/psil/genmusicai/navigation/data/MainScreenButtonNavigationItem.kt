package com.psil.genmusicai.navigation.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.psil.genmusicai.R

data class MainScreenButtonNavigationItem(
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val mainScreenButtonNavigationList = listOf(
    MainScreenButtonNavigationItem(
        title = R.string.main_screen_navigation_item_music,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    MainScreenButtonNavigationItem(
        title = R.string.main_screen_navigation_item_settings,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
)
