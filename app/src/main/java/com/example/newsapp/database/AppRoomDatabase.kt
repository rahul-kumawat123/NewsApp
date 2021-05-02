package com.example.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.model.SavedNews

@Database(entities = [SavedNews::class] , version = 4)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun savedNewsDao(): SavedNewsDao
}