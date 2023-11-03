package com.example.pulseevent

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pulseevent.navigation.AppNavigation
import com.example.pulseevent.ui.theme.PulseEventTheme
import com.example.pulseevent.util.AppConstants.HOME_PAGE_ROUTE
import com.example.pulseevent.util.AppConstants.LOGIN_SCREEN_ROUTE
import com.example.pulseevent.util.AppConstants.USER_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var appNavController: NavHostController

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
            setContent {
            PulseEventTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    appNavController = rememberNavController()

                    val currentUserName = sharedPreferences.getString(USER_NAME, "")

                    AppNavigation(
                        navHostController = appNavController,
                        startDestination = if (currentUserName.isNullOrBlank()) LOGIN_SCREEN_ROUTE else HOME_PAGE_ROUTE
                    )
                }
            }
        }
    }
}
