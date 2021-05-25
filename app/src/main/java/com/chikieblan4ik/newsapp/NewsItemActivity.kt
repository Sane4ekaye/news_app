package com.chikieblan4ik.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_news_item.*

class NewsItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_item)

        val newsItem: NewsItem = intent.getSerializableExtra("newsItem") as NewsItem
        textTtielItem.text = newsItem.title
        textDescriptionItem.text = newsItem.description
    }
}