package com.example.pulseevent.model


import com.google.gson.annotations.SerializedName

data class Trending(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("title")
    val title: String
)
