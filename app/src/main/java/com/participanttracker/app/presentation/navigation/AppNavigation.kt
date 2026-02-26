package com.participanttracker.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.participanttracker.app.presentation.screens.home.HomeScreen
import com.participanttracker.app.presentation.screens.scanner.ScannerScreen
import com.participanttracker.app.presentation.screens.splash.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onScanQR = { navController.navigate(Screen.Scanner.route) }
            )
        }
        composable(Screen.Scanner.route) {
            ScannerScreen(
                onQRCodeScanned = { participantId ->
                    navController.previousBackStackEntry?.savedStateHandle?.set("participantId", participantId)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
