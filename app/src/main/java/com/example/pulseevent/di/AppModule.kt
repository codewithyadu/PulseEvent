package com.example.pulseevent.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.pulseevent.service.PulseEventService
import com.example.pulseevent.util.AppConstants.SHARED_PREF
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl("https://gist.githubusercontent.com/mRahulJain/")
        .build()

    @Provides
    fun provideLoginService(retrofit: Retrofit): PulseEventService =
        retrofit.create(PulseEventService::class.java)
}
