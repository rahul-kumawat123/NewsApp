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
        showToast("url news variable is $urlNews")

        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("news_url_key" , urlNews)
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