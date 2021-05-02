package com.example.newsapp.ui.home

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.database.RoomDatabaseBuilder
import com.example.newsapp.model.SavedNews
import java.util.concurrent.Executors

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var savedData: LiveData<List<SavedNews>> = MutableLiveData()
    private val roomDatabaseBuilder = RoomDatabaseBuilder.getInstance(context)

    init {
        // to getAllSavedNewsDetails()
        savedData = roomDatabaseBuilder.savedNewsDao().getAllNews()
    }

    fun addSavedNewsDetails(savedNews: SavedNews) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.savedNewsDao().insertNews(
                SavedNews(
                    news_title = savedNews.news_title,
                    news_desc = savedNews.news_desc,
                    news_time = savedNews.news_time
                )
            )
        }
    }
}