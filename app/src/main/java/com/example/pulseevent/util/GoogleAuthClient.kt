package com.example.pulseevent.util

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.pulseevent.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GoogleAuthClient @Inject constructor(
    @ApplicationContext private val context: Context,
    private val firebaseAuth: FirebaseAuth,
    private val oneTapClient: SignInClient
) {

    suspend fun signInUserViaGoogle(): IntentSender? {
        return try {
            oneTapClient.beginSignIn(getBeginSignInRequest()).await()
        } catch (e: Exception) {
            null
        }?.pendingIntent?.intentSender
    }

    suspend fun getGoogleSignedInUserResultFromIntent(intent: Intent): GoogleSignedInUserResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = firebaseAuth.signInWithCredential(googleCredentials).await().user
            if (true) {
                GoogleSignedInUserResult(
                    googleSignedInUserData = user?.run {
                        GoogleSignedInUserData(
                            username = displayName,
                            email = email
                        )
                    },
                    errorMessage = null
                )
            } else {
                GoogleSignedInUserResult(
                    googleSignedInUserData = null,
                    errorMessage = "PLEASE USE SRM ID"
                )
            }
        } catch (e: Exception) {
            GoogleSignedInUserResult(
                googleSignedInUserData = null,
                errorMessage = e.message
            )
        }
    }

    private fun getBeginSignInRequest() = BeginSignInRequest.Builder()
        .setGoogleIdTokenRequestOptions(
            GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.auth_web_client_id))
                .build()
        ).setAutoSelectEnabled(true)
        .build()

    private fun isSRMEmail(email: String?) = email?.contains("@srmist.edu.in") ?: false

}
