package ru.example.newsreader.retrofit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.example.newsreader.models.RSSFeed

class RetrofitClientKt {
    var BASE_URL : String = "https://habr.com/"
    var retrofit : Retrofit? = null

    init {
        retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
    }

    fun getArticles() : Observable<RSSFeed>? {
        val service = retrofit?.create(HabrServiceKt::class.java)
        return service?.getArticles()
    }
}