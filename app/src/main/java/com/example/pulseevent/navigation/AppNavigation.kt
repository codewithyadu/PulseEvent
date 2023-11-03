package com.example.pulseevent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pulseevent.CommonViewModel
import com.example.pulseevent.screens.EventDetailsScreen
import com.example.pulseevent.screens.HomeScreen
import com.example.pulseevent.screens.LoginScreen
import com.example.pulseevent.util.AppConstants
import com.example.pulseevent.util.AppConstants.DETAILS_PAGE_ROUTE
import com.example.pulseevent.util.AppConstants.DETAILS_PAGE_ROUTE_ARG
import com.example.pulseevent.util.AppConstants.DETAILS_PAGE_ROUTE_INIT
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
                commonViewModel = commonViewModel,
                navigateToDetailsScreen = {
                    navHostController.navigate(route = "$DETAILS_PAGE_ROUTE_INIT/$it")
                }
            )
        }

        composable(
            route = DETAILS_PAGE_ROUTE,
            arguments = listOf(navArgument(DETAILS_PAGE_ROUTE_ARG) {
                type = NavType.StringType
            })
        ) {
            val eventName = it.arguments!!.getString(DETAILS_PAGE_ROUTE_ARG)
            eventName?.let { name ->
                commonViewModel.getEventDetails(name)?.let { currentItem ->
                    EventDetailsScreen(item = currentItem)
                }
            }
        }
    }
}
