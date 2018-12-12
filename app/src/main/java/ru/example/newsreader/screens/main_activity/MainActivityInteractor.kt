package ru.example.newsreader.screens.main_activity

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.example.newsreader.models.Article
import ru.example.newsreader.models.RSSFeed
import ru.example.newsreader.retrofit.HabrService
import ru.example.newsreader.room.AppDatabase
import ru.example.newsreader.room.entity.ArticleEntity
import ru.example.newsreader.utils.Utils

interface MainActivityInteractor{
    fun downloadArticles(): Observable<RSSFeed>
    fun getArticlesFromDB(): Observable<List<ArticleEntity>>
    fun saveArticles(articles: MutableList<Article>)
    fun deleteArticlesFromDB()
}

class MainActivityInteractorImpl(private val appDatabase: AppDatabase,
                                 private val api: HabrService) : MainActivityInteractor{
    override fun downloadArticles(): Observable<RSSFeed> {
        return api.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getArticlesFromDB(): Observable<List<ArticleEntity>> {
        return Observable.fromCallable { appDatabase.getArticleDao().getAllArticles() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveArticles(articles: MutableList<Article>) {
        Completable.fromCallable {
            appDatabase.getArticleDao().insertAll(Utils.convertArticleListToArticleEntityList(articles))
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun deleteArticlesFromDB() {
        Completable.fromCallable {
            appDatabase.getArticleDao().deleteAll()
        }.subscribeOn(Schedulers.io())
                .subscribe()
    }

}