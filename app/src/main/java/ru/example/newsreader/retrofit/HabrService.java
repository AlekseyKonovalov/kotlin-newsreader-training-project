package ru.example.newsreader.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.example.newsreader.models.RSSFeed;

public interface HabrService {
    @GET("rss/hubs/all/")
    Call<RSSFeed> getArticles();
}
