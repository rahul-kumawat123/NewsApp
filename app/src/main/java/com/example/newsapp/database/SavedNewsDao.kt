package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.SavedNews

@Dao
interface SavedNewsDao {

    @Query("Select * from News_table")
    fun getAllNews(): LiveData<List<SavedNews>>

    @Insert
    fun insertNews(savedNews: SavedNews)
}