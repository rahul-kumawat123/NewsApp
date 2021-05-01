package com.example.newsapp.model

import com.example.newsapp.model.DataModel
import com.google.gson.annotations.SerializedName

data class ResponseDataModel(
    @SerializedName("data")
    val data: ArrayList<DataModel>
)
