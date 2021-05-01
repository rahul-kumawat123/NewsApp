package com.example.newsapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class WebActivity : AppCompatActivity() {

    private val sharedPrefFileName = "NewsSharedPreferences"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFileName , Context.MODE_PRIVATE)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        //news url received by clicking news item
        val urlNews = intent.getStringExtra("url_news")
        val titleNews = intent.getStringExtra("title_news")
        val descNews = intent.getStringExtra("desc_news")
//        val authorNews = intent.getStringExtra("author_news")
//        val sourceNews = intent.getStringExtra("source_news")
        val timeNews = intent.getStringExtra("time_news")
        showToast("title news variable is $titleNews")

        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("news_url_key" , urlNews)
        editor.putString("news_title_key" , titleNews)
        editor.putString("news_desc_key" , descNews)
//        editor.putString("news_author_key", authorNews)
//        editor.putString("news_source_key" , sourceNews)
        editor.putString("news_time_key" , timeNews)
        editor.apply()
        editor.commit()

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_bookmarks, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}