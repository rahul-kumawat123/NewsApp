package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(private val context: Context, private val dataList : List<DataModel> ,
                  private val listener: OnRecyclerViewItemClickListener) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

   inner class ViewHolder(view : View): RecyclerView.ViewHolder(view), View.OnClickListener {

        val title: TextView = view.findViewById(R.id.newsTitleTV)
        val desc: TextView = view.findViewById(R.id.newsDescriptionTV)
        val image: ImageView = view.findViewById(R.id.imageView)
        val urlNews: TextView = view.findViewById(R.id.newsURL)
        val newsTime: TextView = view.findViewById(R.id.publishedTimeTV)

       init {
           view.setOnClickListener(this)
       }

       override fun onClick(v: View?) {
           val position : Int = adapterPosition
           if (position != RecyclerView.NO_POSITION){
               listener.onItemClicked(position , url_adapter = dataList[position].url)
           }
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.news_items,parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = dataList[position].title
            desc.text = dataList[position].description

            Glide.with(context)
                .load(dataList[position].image)
                .placeholder(R.drawable.no_image_available)
                .into(image)

            urlNews.text = dataList[position].url
            newsTime.text = dataList[position].time
        }
    }

    interface OnRecyclerViewItemClickListener{
        fun onItemClicked(position: Int ,url_adapter: String)
    }

    override fun getItemCount(): Int = dataList.size
}