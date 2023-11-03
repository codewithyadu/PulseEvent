package com.example.pulseevent.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PulseEventRepository @Inject constructor(private val pulseEventService: PulseEventService) {
    companion object {
        private const val URL =
            "0892fa3f613fb74d49c676018feaaa7f/raw/b544c736b16a7c1551cf4405fa60bfbc1179acf2/events.json"
    }

    suspend fun getPulseAppModel() = flow {
        val response = pulseEventService.getPulseAppModel(URL)
        if (response.isSuccessful) {
            emit(response.body())
        }
    }.flowOn(Dispatchers.IO)
}
