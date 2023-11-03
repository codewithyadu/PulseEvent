package com.example.pulseevent

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pulseevent.util.AppConstants.USER_NAME
import com.example.pulseevent.util.GoogleAuthClient
import com.example.pulseevent.util.GoogleSignedInUserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class CommonViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPreferences: SharedPreferences,
    private val googleAuthClient: GoogleAuthClient
) : ViewModel() {

    fun signInUserViaGoogle(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
        viewModelScope.launch {
            val signInIntentSender = googleAuthClient.signInUserViaGoogle()
            launcher.launch(IntentSenderRequest.Builder(intentSender = signInIntentSender ?: return@launch).build())
        }
    }

    fun getGoogleSignedInUserResultFromIntent(
        result: ActivityResult,
        navigateToHomeScreen: () -> Unit
    ) {
        viewModelScope.launch {
            val signInResult = googleAuthClient.getGoogleSignedInUserResultFromIntent(
                intent = result.data ?: return@launch
            )
            onGoogleSignedInResult(signInResult, navigateToHomeScreen)
        }
    }

    private fun onGoogleSignedInResult(
        googleSignedInUserResult: GoogleSignedInUserResult,
        navigateToHomeScreen: () -> Unit
    ) {
        val username = googleSignedInUserResult.googleSignedInUserData?.username
        val email = googleSignedInUserResult.googleSignedInUserData?.email
        val errorMessage = googleSignedInUserResult.errorMessage
        if (errorMessage.isNullOrBlank().not()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        } else if ((username.isNullOrBlank() || email.isNullOrBlank())) {
            Toast.makeText(context, "SOMETHING WENT WRONG", Toast.LENGTH_LONG).show()
        } else {
            sharedPreferences.edit().putString(USER_NAME, username).apply()
            navigateToHomeScreen()
        }
    }

}