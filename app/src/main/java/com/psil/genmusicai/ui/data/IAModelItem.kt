package com.psil.genmusicai.ui.data

import androidx.annotation.StringRes
import com.psil.genmusicai.R

data class IAModelItem(
    @StringRes val title: Int
)

val iAModelList = listOf(

    IAModelItem(R.string.ia_model_screen_suno_item_title),
    IAModelItem(R.string.ia_model_screen_mubert_item_title)
)

