package ru.example.newsreader.retrofit

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.saray.app.di.PerApplication

private const val BASE_URL : String = "https://habr.com/"

@Module
class ApiModule{

    @PerApplication
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
    }

    @PerApplication
    @Provides
    fun provideApi(retrofit: Retrofit): HabrServiceKt {
        return retrofit.create(HabrServiceKt::class.java)
    }

}