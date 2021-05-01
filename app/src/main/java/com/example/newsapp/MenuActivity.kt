package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isNotEmpty
import java.util.*

class MenuActivity : AppCompatActivity() {

    private  var categoryValue = ""
    private  var languageValue = ""
    private  var countryValue = ""
    private  var sourceValue = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        searchKeywordNews()
    }


    private fun searchKeywordNews() {
        searchBTN.setOnClickListener(View.OnClickListener {
            val customSearch=search_news.query.toString()
            Toast.makeText(this,customSearch, Toast.LENGTH_SHORT).show()
            val intentSearchBar=Intent(this,MainActivity::class.java)
            intentSearchBar.putExtra("keywords",customSearch)
            intentSearchBar.putExtra("checkKeyword",true)
            startActivity(intentSearchBar)
        })
    }

    override fun onResume() {
        super.onResume()

        inflateLanguageDropDownData()

        inflateCategoriesDropDownData()

        inflateCountriesDropDownData()

        inflateSourcesDropDownData()
    }

    private fun inflateSourcesDropDownData() {
        val sources = resources.getStringArray(R.array.sources)
        val arrayAdapter = ArrayAdapter(applicationContext , R.layout.dropdown_item ,sources)
        autoCompleteTextView_Sources.setAdapter(arrayAdapter)

        autoCompleteTextView_Sources.setOnItemClickListener { parent, view, position, id ->

            val sourceSelected = parent.getItemAtPosition(position)
//           if (sourceSelected.equals("")){
//               sourceValue =  "cnn"
//            }else{
//               sourceValue =  sourceSelected.toString().toLowerCase()
//            }
            sourceValue = sourceSelected.toString().toLowerCase()
            val intentSourceBar = Intent(this , MainActivity::class.java)
            intentSourceBar.putExtra("sources" , sourceValue)
            intentSourceBar.putExtra("checkSource" , true)
//            intentCategoryBar.putExtra("flag" , 4)
            startActivity(intentSourceBar)
            showToast("$sourceSelected" +
                    " News Source is Selected")

        }
    }

    private fun inflateCountriesDropDownData() {
        val countries = resources.getStringArray(R.array.countries)
        val arrayAdapter = ArrayAdapter(applicationContext , R.layout.dropdown_item ,countries)
        autoCompleteTextView_Countries.setAdapter(arrayAdapter)

        autoCompleteTextView_Countries.setOnItemClickListener { parent, view, position, id ->

            val countrySelected = parent.getItemAtPosition(position)
            if (countrySelected.equals("")){
                 countryValue = "us"
            }else{
                when(countrySelected) {

                    "Argentina" -> countryValue = "ar"
                    "Australia" -> countryValue = "au"
                    "Austria" -> countryValue = "at"
                    "Belgium" -> countryValue = "be"
                    "Brazil" -> countryValue = "br"
                    "Bulgaria" -> countryValue = "bg"
                    "Canada" -> countryValue = "ca"
                    "China" -> countryValue = "cn"
                    "Colombia" -> countryValue = "co"
                    "Czech Republic" -> countryValue = "cz"
                    "Egypt" -> countryValue = "eg"
                    "France" -> countryValue = "fr"
                    "Germany" -> countryValue = "de"
                    "Greece" -> countryValue = "gr"
                    "Hong Kong" -> countryValue = "hk"
                    "Hungary" -> countryValue = "hu"
                    "India" -> countryValue = "in"
                    "Indonesia" -> countryValue = "id"
                    "Ireland" -> countryValue = "ie"
                    "Israel" -> countryValue = "il"
                    "Italy" -> countryValue = "it"
                    "Japan" -> countryValue = "jp"
                    "Latvia" -> countryValue = "lv"
                    "Lithuania" -> countryValue = "lt"
                    "Malaysia" ->  countryValue = "my"
                    "Mexico" -> countryValue = "mx"
                    "Morocco" -> countryValue = "ma"
                    "Netherlands" -> countryValue = "nl"
                    "New Zealand" ->  countryValue = "nz"
                    "Nigeria" -> countryValue = "ng"
                    "Norway" -> countryValue = "no"
                    "Philippines" -> countryValue = "ph"
                    "Poland" -> countryValue = "pl"
                    "Portugal" -> countryValue = "pt"
                    "Romania" -> countryValue = "ro"
                    "Saudi Arabia" -> countryValue = "sa"
                    "Serbia" -> countryValue = "rs"
                    "Singapore" -> countryValue = "sg"
                    "Slovakia"-> countryValue = "sk"
                    "Slovenia" -> countryValue = "si"
                    "South Africa" -> countryValue = "za"
                    "South Korea" -> countryValue = "kr"
                    "Sweden" -> countryValue = "se"
                    "Switzerland" -> countryValue = "ch"
                    "Taiwan" -> countryValue = "tw"
                    "Thailand" -> countryValue = "th"
                    "Turkey" -> countryValue = "tr"
                    "UAE" -> countryValue = "ae"
                    "Ukraine" -> countryValue = "ua"
                    "United Kingdom" -> countryValue = "gb"
                    "United States" -> countryValue = "us"
                    "Venuzuela" -> countryValue = "ve"
                }
            }
            val intentCountryBar = Intent(this , MainActivity::class.java)
            intentCountryBar.putExtra("countries" , countryValue)
            intentCountryBar.putExtra("checkCountry" , true)
//            intentCategoryBar.putExtra("flag" , 3)
            startActivity(intentCountryBar)
            showToast("$countrySelected" +
                    " Country is Selected")
        }

    }

    private fun inflateCategoriesDropDownData() {
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(applicationContext , R.layout.dropdown_item , categories)
        autoCompleteTextView_Categories.setAdapter(arrayAdapter)

        autoCompleteTextView_Categories.setOnItemClickListener { parent, view, position, id ->

            val categorySelected = parent.getItemAtPosition(position)
            categoryValue = if (categorySelected.equals("")){
                "general"
            }else{
                categorySelected.toString().toLowerCase()
            }
            val intentCategoryBar = Intent(this , MainActivity::class.java)
            intentCategoryBar.putExtra("categories" , categoryValue)
            intentCategoryBar.putExtra("checkCategory" , true)
//            intentCategoryBar.putExtra("flag" , 3)
            startActivity(intentCategoryBar)
            showToast("$categorySelected" +
                    " News Category is Selected")
        }

    }

    private fun inflateLanguageDropDownData() {
        val languages = resources.getStringArray(R.array.languages)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.dropdown_item , languages)
        autoCompleteTextView_Languages.setAdapter(arrayAdapter)

        //Log.i("languages" , "$languages")

        //to retrieve selected data
        autoCompleteTextView_Languages.setOnItemClickListener { parent, view, position, id ->

            val languageSelected = parent.getItemAtPosition(position)
            if (languageSelected.equals("")){
               languageValue =  "en"
            }else{
                when (languageSelected){
                    "Arabic" -> languageValue = "ar"
                    "English" -> languageValue = "en"
                    "German" -> languageValue = "de"
                    "Spanish" -> languageValue = "es"
                    "French" -> languageValue = "fr"
                    "Hebrew" -> languageValue = "he"
                    "Italian" -> languageValue = "it"
                    "Dutch" -> languageValue = "nl"
                    "Norwegian" -> languageValue = "no"
                    "Portuguese" -> languageValue = "pt"
                    "Russian" -> languageValue = "ru"
                    "Swedish" -> languageValue = "se"
                    "Chinese" -> languageValue = "zh"
                }
            }

            val intentLanguageBar = Intent(this , MainActivity::class.java)
            intentLanguageBar.putExtra("languages" , languageValue)
            intentLanguageBar.putExtra("checkLanguage" , true)
//            intentLanguageBar.putExtra("flag" , 2)
            startActivity(intentLanguageBar)

            showToast("$languageSelected " +
                    " Language is Selected")
        }
    }
}