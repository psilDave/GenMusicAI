package com.psil.genmusicai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.psil.genmusicai.navigation.data.GenMusicAINavHost
import com.psil.genmusicai.navigation.data.GenMusicAIScreens
import com.psil.genmusicai.ui.theme.GenMusicAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GenMusicAITheme {
                GenMusicAIApp()
            }
        }
    }
}

@Composable
fun GenMusicAIApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    GenMusicAINavHost(
        navController = navController,
        startDestination = GenMusicAIScreens.SPLASH.name
    )


}

@Preview(showBackground = true)
@Composable
fun GenMusicAIAppPreview() {
    GenMusicAITheme {
        GenMusicAIApp()
    }
}