package ru.example.newsreader.MainActivity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import ru.example.newsreader.UtilsKt
import ru.example.newsreader.models.ArticleKt
import ru.example.newsreader.retrofit.RetrofitClientKt
import ru.example.newsreader.room.AppDatabase
import ru.example.newsreader.room.Entity.ArticleEntity

interface MainPresenterKt{
    fun downloadArticles()
    fun getArticlesFromDB(): Observable<List<ArticleEntity>>
    fun saveArticles(articles: MutableList<ArticleKt>)
    fun deleteArticlesFromDB()
    fun detachView()
}

class MainPresenterKtImpl (private var mainActivityView : MainActivityViewKt?,
                           private val appDatabase: AppDatabase,
                           private val context: Context) : LifecycleObserver, MainPresenterKt {

    private val disposables = CompositeDisposable()

    override fun downloadArticles() {
        disposables += if (UtilsKt.hasConnection(context)) {
            RetrofitClientKt().habrApi.getArticles()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { response ->
                                mainActivityView?.showArticles(response.articleList!!)
                                saveArticles(response.articleList)
                            },
                            { error ->
                                Log.d("retrofitError", error.toString())
                            })
        }
        else {
            getArticlesFromDB().subscribe{mainActivityView?.showArticles(UtilsKt.convertArticleEntityToArticle(it))}
        }
    }

    override fun getArticlesFromDB(): Observable<List<ArticleEntity>> {
        return Observable.fromCallable { appDatabase.articleDao.getAllArticles() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    override fun saveArticles(articles: MutableList<ArticleKt>) {
        disposables += Observable.fromCallable {articles}
                .subscribeOn(Schedulers.io())
                .flatMap { articles -> Observable.fromIterable(articles) }
                .subscribe{ article -> appDatabase.articleDao.insert(article.convertToArticleEntity()) }
    }

    override fun deleteArticlesFromDB() {
        disposables += Observable.fromCallable {appDatabase}
                .subscribeOn(Schedulers.io())
                .subscribe{db -> db.articleDao.deleteAll()}
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun detachView() {
        Log.d("ON_DESTROY", "dispose")
        disposables.dispose()
        mainActivityView = null
    }

}



