package com.example.newsapp.rests

import com.example.newsapp.model.ResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    //end point of base_url

    @GET("news")
    fun getData(
        @Query("access_key") key : String,
        @Query("languages") lang: String,

    ):retrofit2.Call<ResponseDataModel>


    @GET("news")
    fun getSearchData(
        @Query("access_key") key : String,
        @Query("languages") lang: String,
        @Query("keywords") keywords : String

    ):retrofit2.Call<ResponseDataModel>

    @GET("news")
    fun getCategorisedData(
            @Query("access_key") key : String,
            @Query("languages") lang: String,
            @Query("categories") categories: String

    ):retrofit2.Call<ResponseDataModel>

    @GET("news")
    fun getLanguageData(
            @Query("access_key") key : String,
            @Query("languages") lang: String,

    ):retrofit2.Call<ResponseDataModel>

    @GET("news")
    fun getCountryData(
            @Query("access_key") key : String,
            @Query("countries") country: String

    ):retrofit2.Call<ResponseDataModel>

    @GET("news")
    fun getSourceData(
        @Query("access_key") key : String,
        @Query("languages") lang: String,
        @Query("sources") source: String

        ):retrofit2.Call<ResponseDataModel>
}