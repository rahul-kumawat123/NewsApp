package com.example.newsapp.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("title")
    val title : String,

    @SerializedName("author")
    val author: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("sources")
    val source: String,

    @SerializedName("countries")
    val country: String,

    @SerializedName("published_at")
    val time: String

)
