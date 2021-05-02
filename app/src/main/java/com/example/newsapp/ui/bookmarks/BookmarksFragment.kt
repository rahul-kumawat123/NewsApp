package com.example.newsapp.ui.bookmarks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.SavedNewsItemAdapter
import com.example.newsapp.model.SavedNews
import com.example.newsapp.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_bookmarks.*

class BookmarksFragment : Fragment() {

    private lateinit var bookmarksViewModel: BookmarksViewModel
    private lateinit var adapter: SavedNewsItemAdapter
    var savedNewsList = ArrayList<SavedNews>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bookmarksViewModel =
                ViewModelProvider(this).get(BookmarksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bookmarks, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarksViewModel = ViewModelProvider(this).get(BookmarksViewModel::class.java)

        val layoutManager = LinearLayoutManager(activity , LinearLayoutManager.VERTICAL,false)
        recyclerViewSavedNews.layoutManager = layoutManager

        showSavedNewsItemFromRoom()
    }

    private fun showSavedNewsItemFromRoom() {
        val application = requireActivity().application

        val savedNewsViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        savedNewsViewModel.savedData.observe(viewLifecycleOwner , Observer {
            savedNewsList = it as ArrayList<SavedNews>

            adapter = SavedNewsItemAdapter(this, savedNewsList)
            recyclerViewSavedNews.adapter = adapter
            adapter.notifyDataSetChanged()
            Log.e("New", adapter.itemCount.toString())
        })
    }
}