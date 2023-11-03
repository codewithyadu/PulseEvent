package com.example.pulseevent.model


import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("about")
    val about: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("organizer")
    val organizer: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("venue")
    val venue: String
)
