package com.example.pulseevent.util

data class GoogleSignedInUserResult(
    val googleSignedInUserData: GoogleSignedInUserData?,
    val errorMessage: String?
)

data class GoogleSignedInUserData(
    val username: String?,
    val email: String?
)