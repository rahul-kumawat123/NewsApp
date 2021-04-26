package com.example.newsapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val KEY = "e85ab53a52e9aa1ca6a1d01bf7bc3b23"
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var progressDialog: ProgressDialog
    var newsList =  ArrayList<DataModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createProgressDialog()

        setupUI()

        showNews()


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

        val call = ApiClient.getClient.getData(KEY, "en", "in")
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
               // progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
               // progressDialog.dismiss()
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