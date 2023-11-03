package com.example.pulseevent.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pulseevent.screens.HomeScreen
import com.example.pulseevent.screens.LoginScreen
import com.example.pulseevent.util.AppConstants
import com.example.pulseevent.util.AppConstants.HOME_PAGE_ROUTE

@Composable
fun AppNavigation(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {

        composable(route = AppConstants.LOGIN_SCREEN_ROUTE) {
            LoginScreen(
                navigateToHomeScreen = {
                    navHostController.navigate(route = HOME_PAGE_ROUTE)
                }
            )
        }

        composable(route = HOME_PAGE_ROUTE) {
            HomeScreen()
        }
    }
}
