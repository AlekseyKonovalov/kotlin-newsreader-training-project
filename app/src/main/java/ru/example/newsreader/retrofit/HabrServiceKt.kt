package ru.example.newsreader.retrofit

import retrofit2.Call
import retrofit2.http.GET
import ru.example.newsreader.models.RSSFeed

interface HabrServiceKt {
    @GET("rss/hubs/all/")
    fun getArticles(): Call<RSSFeed>
}