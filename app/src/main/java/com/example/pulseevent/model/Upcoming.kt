package com.example.pulseevent.model


import com.google.gson.annotations.SerializedName

data class Upcoming(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("title")
    val title: String
)
