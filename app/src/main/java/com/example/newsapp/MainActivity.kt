package com.example.newsapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val KEY = "e85ab53a52e9aa1ca6a1d01bf7bc3b23"
    private val LANGUAGE = "en"
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var progressDialog: ProgressDialog
    var newsList =  ArrayList<DataModel>()
    private var searchBar : String = ""
    private var categoryBar : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val bundle: Bundle ?= intent.extras
//        searchBar = bundle?.get("keywords") as String
//        Log.i("SearchBar" , searchBar)



        val bundleSearch = intent
        if(bundleSearch.getBooleanExtra("check" , false)){
            searchBar = bundleSearch.getStringExtra("keywords").toString()
            createProgressDialog()
            setupUI()
            showSearchNews()
        }else{
            categoryBar = bundleSearch.getStringExtra("categories").toString()
            createProgressDialog()
            setupUI()
            showCategorisedNews()
        }

        createProgressDialog()
        setupUI()
        showNews()

       /* val bundle : Bundle? = intent.extras
        categoryBar = bundle?.get("categories") as String*/

        floatingActionButton.setOnClickListener {
            showToast("Floating Button Clicked")
            startActivity(Intent(this , MenuActivity::class.java))
        }


    }

//    override fun onRestart() {
//        super.onRestart()
//
//        recyclerView.removeAllViewsInLayout()
//
//    }

    private fun showCategorisedNews() {
       // progressDialog.show()

        val call = ApiClient.getClient.getCategoriesData(KEY , "en" , categoryBar )
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
                //progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
               // progressDialog.dismiss()
                Log.e("Failure","Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }
        })
    }

    private fun showSearchNews() {
        //progressDialog.show()

        val call = ApiClient.getClient.getSearchData(KEY , "en" , searchBar)
        //Log.i("ApiClient" , call.toString())
        call.enqueue(object : Callback<ResponseDataModel>{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(
                call: Call<ResponseDataModel>,
                response: Response<ResponseDataModel>
            ) {
                if(response.isSuccessful){
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.e("Data", "Data is ${response.body()}\n\n")
                }
               // progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
               // progressDialog.dismiss()
                Log.e("Failure","Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }
        })
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

        //progressDialog.show()

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
                //progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                //progressDialog.dismiss()
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