/*
package ru.example.newsreader.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RetrofitClientKt {
    var BASE_URL : String = "https://habr.com/"
    var retrofit : Retrofit
    var habrApi : HabrServiceKt

    init {
        retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
        habrApi = retrofit.create(HabrServiceKt::class.java)
    }
}*/
