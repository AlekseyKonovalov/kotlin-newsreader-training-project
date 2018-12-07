package ru.example.newsreader.MainActivity

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.example.newsreader.UtilsKt
import ru.example.newsreader.models.ArticleKt
import ru.example.newsreader.retrofit.RetrofitClientKt
import ru.example.newsreader.room.AppDatabase
import ru.example.newsreader.room.Entity.ArticleEntity

class MainPresenterKt (private val mainActivityView: MainActivityViewKt, private val appDatabase: AppDatabase, private val context: Context) {
    @SuppressLint("CheckResult")
    fun downloadArticles() {

        if (UtilsKt.hasConnection(context)) {
            RetrofitClientKt().getArticles()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                            { response ->
                                mainActivityView.showArticles(response.articleList!!)
                                Thread{
                                    appDatabase.articleDao.deleteAll()
                                    saveArticles(response.articleList)
                                }.start()
                            },
                            { error -> Log.d("retrofitError", error.toString()) }
                    )
        }
        else {
            getArticlesFromDB()
                    ?.subscribe{mainActivityView.showArticles(UtilsKt.convertArticleEntityToArticle(it))}
        }
    }

    fun getArticlesFromDB(): Observable<List<ArticleEntity>>? {
        return Observable.fromCallable { appDatabase.articleDao.getAllArticles() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    private fun saveArticles(articles: MutableList<ArticleKt>) {
        Observable.fromCallable {articles}
                .subscribeOn(Schedulers.io())
                .flatMap { articles -> Observable.fromIterable(articles) }
                .subscribe{article -> appDatabase.articleDao.insert(article.convertToArticleEntity())}
    }

    fun deleteArticlesFromDB() {
        Observable.fromCallable {appDatabase}
                .subscribeOn(Schedulers.io())
                .subscribe{db -> db.articleDao.deleteAll()}
    }

}



