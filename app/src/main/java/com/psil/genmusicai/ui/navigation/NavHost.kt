package com.psil.genmusicai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.psil.genmusicai.ui.navigation.data.GenMusicAIScreens
import com.psil.genmusicai.ui.compose.screens.AIModelScreen
import com.psil.genmusicai.ui.compose.screens.MainScreen
import com.psil.genmusicai.ui.compose.screens.MusicChatScreen
import com.psil.genmusicai.ui.compose.screens.SettingsScreen
import com.psil.genmusicai.ui.compose.screens.SplashScreen

@Composable
fun GenMusicAINavHost(navController: NavHostController, startDestination: String) {
    return NavHost(navController = navController, startDestination = startDestination) {

        composable(GenMusicAIScreens.SPLASH.name) {
            SplashScreen {
                navController.navigate(
                    GenMusicAIScreens.MAIN.name
                )
            }
        }

        composable(GenMusicAIScreens.MAIN.name) {
            MainScreen(
                onClickInMusicTab = {
                    navController.navigate(GenMusicAIScreens.MAIN.name)
                },
                onCLickInSettingsTab = {
                    navController.navigate(GenMusicAIScreens.SETTINGS.name)
                },
                onClickFloatingButton = {
                    navController.navigate(GenMusicAIScreens.MUSIC_CHAT.name)
                }
            )
        }

        composable(GenMusicAIScreens.SETTINGS.name) {
            SettingsScreen(
                onClickInMusicTab = {
                    navController.navigate(GenMusicAIScreens.MAIN.name)
                },
                onCLickInSettingsTab = {
                    navController.navigate(GenMusicAIScreens.SETTINGS.name)
                },
                onClickInIAModel = {
                    navController.navigate(GenMusicAIScreens.AI_MODEL.name)
                }
            )
        }

        composable(GenMusicAIScreens.AI_MODEL.name) {
            AIModelScreen(
                onBackButtonClick = { navController.navigateUp() }
            )
        }

        composable(GenMusicAIScreens.MUSIC_CHAT.name) {
            MusicChatScreen(onBackButtonClick = { navController.navigateUp() }
            )
        }
    }
}