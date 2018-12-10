package ru.example.newsreader.MainActivity

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
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

class MainPresenterKt (private var mainActivityView : MainActivityViewKt?, private val appDatabase: AppDatabase, private val context: Context) {

    private val disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun downloadArticles() {

        if (UtilsKt.hasConnection(context)) {
            disposables+= RetrofitClientKt().getArticles()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                            { response ->
                                mainActivityView?.showArticles(response.articleList!!)
                                saveArticles(response.articleList)
                            },
                            { error -> Log.d("retrofitError", error.toString()) }

                    )!!
        }
        else {
            disposables+= getArticlesFromDB()?.subscribe{mainActivityView?.showArticles(UtilsKt.convertArticleEntityToArticle(it))}
        }
    }

    fun getArticlesFromDB(): Observable<List<ArticleEntity>> {
        return Observable.fromCallable { appDatabase.articleDao.getAllArticles() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    private fun saveArticles(articles: MutableList<ArticleKt>) {
        disposables+=Observable.fromCallable {articles}
                .subscribeOn(Schedulers.io())
                .flatMap { articles -> Observable.fromIterable(articles) }
                .subscribe{ article -> appDatabase.articleDao.insert(article.convertToArticleEntity()) }
    }

    fun deleteArticlesFromDB() {
        disposables+=Observable.fromCallable {appDatabase}
                .subscribeOn(Schedulers.io())
                .subscribe{db -> db.articleDao.deleteAll()}
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachView() {
        disposables.dispose()
        mainActivityView = null
    }

}



