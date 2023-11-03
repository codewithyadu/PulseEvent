package com.example.pulseevent.service

import com.example.pulseevent.model.PulseAppModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PulseEventService {
    @GET
    suspend fun getPulseAppModel(
        @Url url: String,
    ): Response<PulseAppModel>
}

