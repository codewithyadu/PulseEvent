package com.example.pulseevent.model


import com.google.gson.annotations.SerializedName

data class PulseAppModel(
    @SerializedName("trending")
    val trending: Trending,
    @SerializedName("upcoming")
    val upcoming: Upcoming
)
