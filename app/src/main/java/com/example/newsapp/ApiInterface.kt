package com.example.newsapp

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    //end point of base_url

    @GET("news")
    fun getData(
        @Query("access_key") key : String,
        @Query("languages") lang: String,
        @Query("countries") country: String
    ):retrofit2.Call<ResponseDataModel>
}