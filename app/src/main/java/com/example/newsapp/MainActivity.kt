package com.example.newsapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val KEY = "e85ab53a52e9aa1ca6a1d01bf7bc3b23"
    private val LANGUAGE = "en"
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var progressDialog: ProgressDialog
    var newsList =  ArrayList<DataModel>()
    private var searchBar : String = ""
    private var categoryBar : String = ""
    private var languageBar : String = ""
    private var countryBar : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bundleSearch=intent
        when {
            bundleSearch.getBooleanExtra("checkKeyword",false) -> {
                searchBar= bundleSearch.getStringExtra("keywords").toString()
                createProgressDialog()
                setupUI()
                showSearchNews()

            }
            bundleSearch.getBooleanExtra("checkCategory" , false) -> {
                categoryBar = bundleSearch.getStringExtra("categories").toString()
                createProgressDialog()
                setupUI()
                showCategorisedNews()
            }
            bundleSearch.getBooleanExtra("checkLanguage" , false) -> {
                languageBar = bundleSearch.getStringExtra("languages").toString()
                createProgressDialog()
                setupUI()
                showLanguageWiseNews()
            }
            bundleSearch.getBooleanExtra("checkCountry" ,  false) ->{
                countryBar = bundleSearch.getStringExtra("countries").toString()
                createProgressDialog()
                setupUI()
                showCountryWiseNews()
            }
            else -> {
                createProgressDialog()
                setupUI()
                showNews()
            }
        }


        floatingActionButton.setOnClickListener {
            showToast("Floating Button Clicked")
            startActivity(Intent(this , MenuActivity::class.java))
        }


    }

    private fun showCountryWiseNews() {
        progressDialog.show()

        val call = ApiClient.getClient.getCountryData(KEY , countryBar )
        //Log.i("ApiClient" , call.toString())
        call.enqueue(object : Callback<ResponseDataModel>{
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if(response.isSuccessful){
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.e("Data", "Data is ${response.body()}\n\n")
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("Failure","Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }
        })
    }

    private fun showLanguageWiseNews() {
         progressDialog.show()

        val call = ApiClient.getClient.getLanguageData(KEY , languageBar )
        //Log.i("ApiClient" , call.toString())
        call.enqueue(object : Callback<ResponseDataModel>{
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if(response.isSuccessful){
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.e("Data", "Data is ${response.body()}\n\n")
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                 progressDialog.dismiss()
                Log.e("Failure","Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }
        })
    }

    private fun showCategorisedNews() {
        progressDialog.show()

        val call = ApiClient.getClient.getCategorisedData(KEY , "en" , categoryBar )
        //Log.i("ApiClient" , call.toString())
        call.enqueue(object : Callback<ResponseDataModel>{
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if(response.isSuccessful){
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.e("Data", "Data is ${response.body()}\n\n")
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("Failure","Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }
        })
    }

    private fun showSearchNews() {

        progressDialog.show()
        val call = ApiClient.getClient.getSearchData(KEY, "en",searchBar)
        call.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if (response.isSuccessful) {
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.e("Data", "Data is ${response.body()}\n\n")
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                 progressDialog.dismiss()
                Log.e("Failure", "Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }
        })


//
//        progressDialog.show()
//
//        val call = ApiClient.getClient.getSearchData(KEY , "en" , searchBar)
//        //Log.i("ApiClient" , call.toString())
//        call.enqueue(object : Callback<ResponseDataModel>{
//            @RequiresApi(Build.VERSION_CODES.N)
//            override fun onResponse(
//                call: Call<ResponseDataModel>,
//                response: Response<ResponseDataModel>
//            ) {
//                if(response.isSuccessful){
//                    newsList.addAll(response.body()?.data ?: ArrayList())
//                    recyclerView.adapter?.notifyDataSetChanged()
//                    Log.e("Data", "Data is ${response.body()}\n\n")
//                }
//               progressDialog.dismiss()
//            }
//
//            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
//                progressDialog.dismiss()
//                Log.e("Failure","Error is ${t.localizedMessage}")
//                showToast("Some Error Occurred while fetching data")
//            }
//        })
    }

    private fun setupUI() {
        //recycler view
        val layoutManager = LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager

        //attaching adapter to recycler view
        itemAdapter = ItemAdapter(this,newsList)
        recyclerView.adapter = itemAdapter
    }

    private fun showNews() {

        progressDialog.show()

        val call = ApiClient.getClient.getData(KEY, LANGUAGE )
        //Log.i("ApiClient" , call.toString())
        call.enqueue(object : Callback<ResponseDataModel>{
            override fun onResponse(
                call: Call<ResponseDataModel>,
                response: Response<ResponseDataModel>
            ) {
                if(response.isSuccessful){
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.e("Data", "Data is ${response.body()}\n\n")
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("Failure","Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }
        })
    }

    private fun createProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading..")
        progressDialog.setMessage("Please wait while we fetch data..")
        progressDialog.setCancelable(false)
    }
}