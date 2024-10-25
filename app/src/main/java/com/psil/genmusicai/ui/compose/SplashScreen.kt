package com.psil.genmusicai.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psil.genmusicai.R
import com.psil.genmusicai.ui.theme.GenMusicAITheme

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center, ) {
        Image(
            painter = painterResource(id = R.drawable.logo_com_titulo),
            contentDescription = stringResource(R.string.descricao_logo_do_app),
            Modifier.size(250.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    GenMusicAITheme {
        SplashScreen()
    }
}