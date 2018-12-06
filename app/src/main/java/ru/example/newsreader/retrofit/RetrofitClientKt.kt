package ru.example.newsreader.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.example.newsreader.models.RSSFeed

class RetrofitClientKt {
    var BASE_URL : String = "https://habr.com/"
    var retrofit : Retrofit? = null

    init {
        retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
    }

    fun getArticles() : Call<RSSFeed>? {
        val service = retrofit?.create(HabrServiceKt::class.java)
        val repos = service?.getArticles()
        return repos
    }
}