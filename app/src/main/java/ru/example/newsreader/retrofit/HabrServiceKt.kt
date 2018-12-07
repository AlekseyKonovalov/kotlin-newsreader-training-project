package ru.example.newsreader.retrofit


import io.reactivex.Observable
import retrofit2.http.GET
import ru.example.newsreader.models.RSSFeed

interface HabrServiceKt {
    @GET("rss/hubs/all/")
    fun getArticles(): Observable<RSSFeed>
}