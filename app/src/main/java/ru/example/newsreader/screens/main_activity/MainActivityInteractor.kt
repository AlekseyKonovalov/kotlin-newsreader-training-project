package ru.example.newsreader.screens.main_activity

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.example.newsreader.models.ArticleKt
import ru.example.newsreader.models.RSSFeed
import ru.example.newsreader.retrofit.HabrService
import ru.example.newsreader.room.AppDatabase
import ru.example.newsreader.room.entity.ArticleEntity

interface MainActivityInteractor{
    fun downloadArticles(): Observable<RSSFeed>
    fun getArticlesFromDB(): Observable<List<ArticleEntity>>
    fun saveArticles(articles: MutableList<ArticleKt>)
    fun deleteArticlesFromDB()
}

class MainActivityInteractorImpl(private val appDatabase: AppDatabase, private val api: HabrService) : MainActivityInteractor{
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

    override fun saveArticles(articles: MutableList<ArticleKt>) {
        Observable.fromCallable {articles}
                .subscribeOn(Schedulers.io())
                .flatMap { articleList -> Observable.fromIterable(articleList) }
                .subscribe{ article ->
                    appDatabase.getArticleDao().insert(article.convertToArticleEntity())
                }
    }

    override fun deleteArticlesFromDB() {
        Observable.fromCallable {appDatabase}
                .subscribeOn(Schedulers.io())
                .subscribe{db -> db.getArticleDao().deleteAll()}
    }

}