package ru.example.newsreader.MainActivity

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.example.newsreader.UtilsKt
import ru.example.newsreader.models.Article
import ru.example.newsreader.models.RSSFeed
import ru.example.newsreader.retrofit.RetrofitClientKt
import ru.example.newsreader.room.AppDatabase
import java.util.*

class MainPresenterKt (private val mainActivityView: MainActivityView, private val appDatabase: AppDatabase, private val context: Context) {

    private val articlesFromDB: List<Article>
        get() {

            val articleEntities = appDatabase.getArticleDao().getAllArticles()
            val articleList = ArrayList<Article>()
            for (articleEntity in articleEntities) {
                articleList.add(articleEntity.convertToArticle())
            }
            return articleList
        }

    fun downloadArticles() {
        if (UtilsKt.hasConnection(context)) {
            RetrofitClientKt().getArticles()?.enqueue(object : Callback<RSSFeed> {
                override fun onResponse(call: Call<RSSFeed>, response: Response<RSSFeed>) {
                    response.body()?.articleList?.let { mainActivityView.showArticles(it) }
                    Thread {
                        try {
                            if (articlesFromDB.isEmpty()) {
                                response.body()?.articleList?.let { saveArticles(it) }
                            }

                        } catch (e: Exception) {
                        }
                    }.start()
                }

                override fun onFailure(call: Call<RSSFeed>, t: Throwable) {}
            })
        } else {
            Thread {
                try {
                    mainActivityView.showArticles(articlesFromDB)
                } catch (e: Exception) {
                }
            }.start()
        }
    }

    private fun saveArticles(articles: List<Article>) {
        Thread{
            for (article in articles) {
                appDatabase.articleDao.insert(article.convertToArticleEntity()!!)
            }
        }.start()

    }

    fun deleteArticlesFromDB() {
        Thread {
            appDatabase.getArticleDao().deleteAll()
            try {
                appDatabase.getArticleDao().deleteAll()
            } catch (e: Exception) {

            }
        }.start()
    }

    fun getArticlesInSource() {
        Thread {
            try {
                //List<ArticleEntity> articleEntities = appDatabase.getArticleDao().findArticlesForSource("habr.com");
            } catch (e: Exception) {
            }
        }.start()
    }
}
