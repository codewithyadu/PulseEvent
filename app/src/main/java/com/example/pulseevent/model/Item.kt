package com.example.pulseevent.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("date")
    val date: String,
    @SerializedName("details")
    val details: Details,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("month")
    val month: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("title")
    val title: String
)
