/*
package ru.example.newsreader.retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import ru.example.newsreader.models.RSSFeed;

public class RetrofitClient {
    private Retrofit retrofit;
    public RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://habr.com/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    public Call<RSSFeed> getArticles(){
        HabrServiceKt service = retrofit.create(HabrServiceKt.class);
        Call<RSSFeed> repos = service.getArticles();
        return repos;
    }
}
*/
