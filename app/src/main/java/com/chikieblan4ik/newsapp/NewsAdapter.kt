package com.chikieblan4ik.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val newsArray: ArrayList<NewsItem>, private val onNewsClickListener: OnNewsClickListener) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitle: TextView = view.findViewById<View>(R.id.textTitle) as TextView
        val textDescription: TextView = view.findViewById<View>(R.id.textDescription) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_news_item, parent, false)
        return MyViewHolder(itemView)
    }

    // выводим всю хуйню ну там setText() и прочее
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val newsItem = newsArray[position]

        holder.textTitle.text = newsItem.title
        holder.textDescription.text = newsItem.description

        holder.itemView.setOnClickListener {
            onNewsClickListener.onNewsItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return newsArray.size
    }

}