package com.example.pulseevent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pulseevent.CommonViewModel
import com.example.pulseevent.screens.HomeScreen
import com.example.pulseevent.screens.LoginScreen
import com.example.pulseevent.util.AppConstants
import com.example.pulseevent.util.AppConstants.HOME_PAGE_ROUTE

@Composable
fun AppNavigation(
    navHostController: NavHostController,
    startDestination: String,
    commonViewModel: CommonViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {

        composable(route = AppConstants.LOGIN_SCREEN_ROUTE) {
            LoginScreen(
                commonViewModel = commonViewModel,
                navigateToHomeScreen = {
                    navHostController.navigate(route = HOME_PAGE_ROUTE)
                }
            )
        }

        composable(route = HOME_PAGE_ROUTE) {
            LaunchedEffect(key1 = Unit) { commonViewModel.getPulseAppModel() }
            HomeScreen(
                commonViewModel = commonViewModel
            )
        }
    }
}
