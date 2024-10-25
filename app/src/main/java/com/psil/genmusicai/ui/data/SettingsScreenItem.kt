package com.psil.genmusicai.ui.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.psil.genmusicai.R

data class SettingsScreenItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val onClickAction: () -> Unit
)

val settingsItemsList = listOf(
    SettingsScreenItem(
        title = R.string.settings_screen_ia_model_item_title,
        icon = R.drawable.settings_screen_neurology_icon,
        onClickAction = {}
    ),
    SettingsScreenItem(
        title = R.string.settings_screen_about_item_title,
        icon = R.drawable.settings_screen_about_icon,
        onClickAction = {}
    )
)
