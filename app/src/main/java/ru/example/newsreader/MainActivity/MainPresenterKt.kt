package ru.example.newsreader.MainActivity

import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.example.newsreader.UtilsKt
import ru.example.newsreader.models.ArticleKt
import ru.example.newsreader.retrofit.RetrofitClientKt
import ru.example.newsreader.room.AppDatabase
import java.util.*

class MainPresenterKt (private val mainActivityView: MainActivityViewKt, private val appDatabase: AppDatabase, private val context: Context) {

    private val articlesFromDB: List<ArticleKt>
        get() {

            val articleEntities = appDatabase.articleDao.getAllArticles()
            val articleList = ArrayList<ArticleKt>()
            for (articleEntity in articleEntities) {
                articleList.add(articleEntity.convertToArticle())
            }
            return articleList
        }

    fun downloadArticles() {
        if (UtilsKt.hasConnection(context)) {

            RetrofitClientKt().getArticles()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
/*                    ?.subscribeBy(
                    onNext = {
                        mainActivityView.showArticles(it.articleList!!)
                        Thread {
                            if(articlesFromDB.isNotEmpty()){
                                deleteArticlesFromDB()
                            }
                            saveArticles(it.articleList)
                        }.start()
                    },
                    onComplete = {
                        Log.d("Complete", "Complete")
                    },
                    onError = {
                        Log.d("retrofitError", it.toString())
                    }
                    )*/
                    ?.subscribe(
                            { response -> mainActivityView.showArticles(response.articleList!!)
                                Thread {
                                    if(articlesFromDB.isNotEmpty()){
                                        deleteArticlesFromDB()
                                    }
                                    saveArticles(response.articleList)
                                }.start()},
                            { error -> Log.d("retrofitError", error.toString())}
                    )

        }
        else {
            Thread {
                mainActivityView.showArticles(articlesFromDB)
            }.start()
        }
    }

    private fun saveArticles(articles: List<ArticleKt>) {
        Thread{
            for (article in articles) {
                appDatabase.articleDao.insert(article.convertToArticleEntity())
            }
        }.start()

    }

    fun deleteArticlesFromDB() {
        Thread {
            appDatabase.articleDao.deleteAll()
            try {
                appDatabase.articleDao.deleteAll()
            } catch (e: Exception) {

            }
        }.start()
    }
}
