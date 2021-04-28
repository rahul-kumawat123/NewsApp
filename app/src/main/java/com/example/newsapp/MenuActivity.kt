package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_menu.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isNotEmpty
import java.util.*

class MenuActivity : AppCompatActivity() {

    private  var categoryValue = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        searchKeywordNews()

        searchCustomNewsBTN.setOnClickListener {
            showCustomNews()
        }


    }

   fun showCustomNews(){
        autoCompleteTextView_Categories.setOnItemClickListener { parent, view, position, id ->
            val categorySelected = parent.getItemAtPosition(position)
            categoryValue = if (categorySelected.equals("")){
                "general"
            }else{
                categorySelected.toString().toLowerCase()
            }
            val intentCategoryBar = Intent(this , MainActivity::class.java)
            intentCategoryBar.putExtra("categories" , categoryValue)
            startActivity(intentCategoryBar)
        }
    }


    private fun searchKeywordNews() {
        val keywordSearch = search_news.query.toString()

        searchBTN.setOnClickListener {
                val intentSearchBar = Intent(this , MainActivity::class.java)
                intentSearchBar.putExtra("keywords" , keywordSearch)
                intentSearchBar.putExtra("check" , true)
                startActivity(intentSearchBar)
        }
    }

    override fun onResume() {
        super.onResume()

        inflateLanguageDropDownData()

        inflateCategoriesDropDownData()
    }

    private fun inflateCategoriesDropDownData() {
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(applicationContext , R.layout.dropdown_item_categories , categories)
        autoCompleteTextView_Categories.setAdapter(arrayAdapter)

        autoCompleteTextView_Categories.setOnItemClickListener { parent, view, position, id ->
            showToast("${parent.getItemAtPosition(position)}" +
                    " News Category is Selected")
        }
    }

    private fun inflateLanguageDropDownData() {
        val languages = resources.getStringArray(R.array.languages)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.dropdown_item_languages , languages)
        autoCompleteTextView_Languages.setAdapter(arrayAdapter)

        //Log.i("languages" , "$languages")

        //to retrieve selected data
        autoCompleteTextView_Languages.setOnItemClickListener { parent, view, position, id ->
            showToast("${parent.getItemAtPosition(position)} " +
                    " Language is Selected")
        }
    }
}