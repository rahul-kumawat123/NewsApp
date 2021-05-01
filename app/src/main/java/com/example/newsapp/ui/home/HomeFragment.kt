package com.example.newsapp.ui.home

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.model.SavedNews
import com.example.newsapp.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import java.net.URL

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val pref: SharedPreferences = requireContext().getSharedPreferences("NewsSharedPreferences" , 0)
        val urlData : String? = pref.getString("news_url_key" , null)
        val titleData: String? = pref.getString("news_title_key" , null)
        val descriptionData: String? = pref.getString("news_desc_key" , null)
//        val authorData: String? = pref.getString("news_author_key" , null)
//        val sourceData: String? = pref.getString("news_source_key" , null)
        val timeData: String? = pref.getString("news_time_key" , null)

        //context?.showToast("url is $urlData")

        //initializing webView
        val webView :WebView = root.findViewById(R.id.webView)
        val bookmark :ImageView = root.findViewById(R.id.bookmark_click)

        webView.settings.javaScriptEnabled = true

        //loading url using sharedPreferences in home fragment
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                if (url !=null){
                    view?.loadUrl(url)
                }
                return true
            }
        }
        if (urlData != null){
            webView.loadUrl(urlData)
        }

        bookmark.setOnClickListener {
            bookmark_click.setColorFilter(resources.getColor(R.color.red))
            val application = requireActivity().application
            val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel(application)::class.java).also {
                    it.addSavedNewsDetails(
                        SavedNews(
                            news_title = titleData.toString(),
                            news_desc = descriptionData,
//                            news_author = authorData,
//                            news_source = sourceData,
                            news_time = timeData
                        )
                    )
                }
            Toast.makeText(activity, "News Saved Successfully", Toast.LENGTH_LONG).show()
        }
        return root
    }
}