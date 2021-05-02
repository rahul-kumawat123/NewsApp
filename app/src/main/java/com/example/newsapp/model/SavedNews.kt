package com.example.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News_table")
data class SavedNews(

    @PrimaryKey()
    @ColumnInfo(name= "News_title")
    var news_title: String,

    @ColumnInfo(name = "News_description")
    var news_desc: String?= null,

    @ColumnInfo(name = "News_url")
    var news_url: String?= null,

    @ColumnInfo(name = "news_publishedTime")
    var news_time: String? = null,

)
