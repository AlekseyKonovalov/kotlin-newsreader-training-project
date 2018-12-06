/*
package ru.example.newsreader.MainActivity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.example.newsreader.UtilsKt;
import ru.example.newsreader.models.Article;
import ru.example.newsreader.models.RSSFeed;
import ru.example.newsreader.retrofit.RetrofitClientKt;
import ru.example.newsreader.room.AppDatabase;
import ru.example.newsreader.room.Entity.ArticleEntity;

public class MainActivityPresenter {
    private MainActivityView mainActivityView;
    private Context context;
    private AppDatabase appDatabaseKt;

    MainActivityPresenter(MainActivityView mainActivityView, AppDatabase appDatabaseKt, Context context){
        this.mainActivityView=mainActivityView;
        this.appDatabaseKt = appDatabaseKt;
        this.context = context;
    }

    public void downloadArticles(){
        if (UtilsKt.Companion.hasConnection(context)) {
            new RetrofitClientKt().getArticles().enqueue(new Callback<RSSFeed>() {
                @Override
                public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
                    mainActivityView.showArticles(response.body().getArticleList());
                    new Thread(() -> {
                        if(getArticlesFromDB().size()>0){}
                        else saveArticles(response.body().getArticleList());
                    }).start();
                }
                @Override
                public void onFailure(Call<RSSFeed> call, Throwable t) {
                }
            });
        }
        else{
            new Thread(() -> {
                try {
                    mainActivityView.showArticles(getArticlesFromDB());
                } catch (Exception e) {
                }
            }).start();
        }
    }

    private void saveArticles(List<Article> articles){
        Thread t = new Thread() {
            public void run() {
                for (Article article: articles) {
                    appDatabaseKt.getArticleDao().insert(article.convertToArticleEntity());
                }
            }
        };
        t.start();

    }

    private List<Article> getArticlesFromDB(){
        List<ArticleEntity> articleEntities = appDatabaseKt.getArticleDao().getAllArticles();
        List<Article> articleList = new ArrayList<>();
        for (ArticleEntity articleEntity: articleEntities) {
            articleList.add(articleEntity.convertToArticle());
        }
        return articleList;
    }

    public void deleteArticlesFromDB(){
        new Thread(() -> {
            appDatabaseKt.getArticleDao().deleteAll();
            try {
                appDatabaseKt.getArticleDao().deleteAll();
            }
            catch (Exception e) {

            }
        }).start();
    }

    public void getArticlesInSource(){
        new Thread(() -> {
            try {
                //List<ArticleEntity> articleEntities = appDatabase.getArticleDao().findArticlesForSource("habr.com");
            }
            catch (Exception e) {
            }
        }).start();
    }
}
*/
