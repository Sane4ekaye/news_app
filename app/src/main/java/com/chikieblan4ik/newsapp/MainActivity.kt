package com.chikieblan4ik.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), OnNewsClickListener {

    private val url: String = "https://www.express.co.uk/"
    private var newsArray = ArrayList<NewsItem>()
    var test: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainNews()

    }

    override fun onNewsItemClickListener(position: Int) {
        val intent = Intent(this, NewsItemActivity::class.java)
        intent.putExtra("newsItem", newsArray[position])
        startActivity(intent)
    }

    // MainNews
    // 1. wide box main (big news)
    // 2. box latest-stories (default news)
    // 3-5. block three-stories (3 нахуй разных видов новостей в одной хуйне, ахуенно)
    //      - Homepage block number 2 (default news)
    //      - first -second -third last

    fun parseHeaderNews(doc: Document, section: String, news: String = "", item: String = "") : String {
        var headerText: String = ""
        if(item.isNotEmpty() && news.isNotEmpty()){
            if(item.equals("third last")) return doc.select("div[class=clear clearfix]").select("div[class=homepage unit-1]").select("div[class=section-container hide-on-desktop clearfix before-ad]").select("section[data-vr-zone=$news]").select("ul").select("li[class=$item]").select("h4[class=hide-on-desktop photo-caption]").text()
            return doc.select("div[class=clear clearfix]").select("div[class=homepage unit-1]").select("section[data-vr-zone=$news]").select("ul").select("li[class=$item]").select("h4").text()
        } else {
            headerText = doc.select("div[class=clear clearfix]").select("div[class=homepage unit-1]").select("section[class=$section]").select("h2").text()
            return if(headerText.isEmpty()) doc.select("div[class=clear clearfix]").select("div[class=homepage unit-1]").select("section[class=$section]").select("h4").text() else headerText
        }
    }

    fun parseMaintextNews(doc: Document, section: String, news: String = "", item: String = "") : String {
        var headerText: String = ""
        if(item.isNotEmpty() && news.isNotEmpty()){
            return doc.select("div[class=clear clearfix]").select("div[class=homepage unit-1]").select("div[class=section-container hide-on-desktop clearfix before-ad]").select("section[data-vr-zone=$news]").select("ul").select("li[class=$item]").select("p").text()
        } else {
            return "pizda"
        }
    }

    fun mainNews() {
        thread {
            val doc:Document = Jsoup.connect(url).get()
            this@MainActivity.runOnUiThread(Runnable {
                newsArray.add(NewsItem(parseHeaderNews(doc, "wide box main"), doc.select("div[class=clear clearfix]").select("div[class=homepage unit-1]").select("section[data-vr-zone=Homepage Top Box]").select("p").text()))
                newsArray.add(NewsItem(parseHeaderNews(doc, "box latest-stories"), ""))
                newsArray.add(NewsItem(parseHeaderNews(doc, "block three-stories", "Homepage block number 2", "first"), parseMaintextNews(doc, "block three-stories", "Homepage block number 2", "first")))
                newsArray.add(NewsItem(parseHeaderNews(doc, "block three-stories", "Homepage block number 2", "second"), parseMaintextNews(doc, "block three-stories", "Homepage block number 2", "second")))
                newsArray.add(NewsItem(parseHeaderNews(doc, "block three-stories", "Homepage block number 2", "third last"), parseMaintextNews(doc, "block three-stories", "Homepage block number 2", "third last")))
                //newsArray.add(NewsItem(parseHeaderNews(doc, "block three-stories", "Homepage block number 2", "first"), parseMaintextNews(doc, "block three-stories")))

                listNews.layoutManager = LinearLayoutManager(this)
                val adapter = NewsAdapter(newsArray, this)
                listNews.adapter = adapter
            })
//            val headerText = parseHeaderNews(doc, "block three-stories", "Homepage block number 2", "third last")
//            val mainText = parseMaintextNews(doc, "wide box main")

//            this@MainActivity.runOnUiThread{
//                HeaderText.text = headerText
//                textMain.text = mainText
//                HeaderText.text = parseHeaderNews(doc, "box latest-stories")
//                HeaderText.text = parseHeaderNews(doc, "block three-stories", "Homepage block number 2", "first")
//                HeaderText.text = parseHeaderNews(doc, "block three-stories", "Homepage block number 2", "second")
//                HeaderText.text = parseHeaderNews(doc, "block three-stories", "Homepage block number 2", "third last")
//            }
        }
    }

}