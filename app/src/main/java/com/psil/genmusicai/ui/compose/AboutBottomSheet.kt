package com.psil.genmusicai.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.theme.GenMusicAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutBottomSheet() {
    var visible by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = { visible = false },
        sheetState = rememberModalBottomSheetState()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.about_bottom_sheet_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(text = stringResource(R.string.about_bottom_sheet_title))
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(R.string.about_bottom_sheet_description))

        }
    }


}

@Composable
@Preview(showBackground = true)
fun PreviewAboutBottomSheet() {
    GenMusicAITheme {
        AboutBottomSheet()
    }

}