package com.example.pulseevent.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.pulseevent.util.AppConstants.SHARED_PREF
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideSharedPreferences(@ApplicationContext applicationContext: Context): SharedPreferences = applicationContext.getSharedPreferences(SHARED_PREF, MODE_PRIVATE)

    @Provides
    fun provideOneTapClient(@ApplicationContext applicationContext: Context) = Identity.getSignInClient(applicationContext)

}