package com.chikieblan4ik.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        connectURL("https://www.express.co.uk/")
    }

//    fun connectURL(url: String){
//        thread {
//            val doc:Document = Jsoup.connect(url).get()
//            val headerText: String = doc.select("header[class=eu-referendum-interloper]").select("h3").text()
//            val mainTitle: Elements = doc.select("div[class=clear clearfix]").select("div[class=homepage unit-1]").select("section[class=wide box main]").select("h2")
//            this@MainActivity.runOnUiThread(java.lang.Runnable {
//                header.text = headerText
//                maintext.text = mainTitle.toString()
//            })
//        }
//    }

}