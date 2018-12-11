package ru.example.newsreader.MainActivity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import ru.example.newsreader.Utils
import ru.example.newsreader.models.ArticleKt
import ru.example.newsreader.room.Entity.ArticleEntity

interface MainPresenterKt{
    fun getArticles()
    fun getArticlesFromDB(): Observable<List<ArticleEntity>>
    fun saveArticles(articles: MutableList<ArticleKt>)
    fun deleteArticlesFromDB()

    fun detachView()
    fun attachView(view: MainActivityViewKt)
}

class MainPresenterKtImpl (private val interactor: MainActivityInteractor,
                           val context: Context) : LifecycleObserver, MainPresenterKt {

    private var view: MainActivityViewKt? = null
    private val disposables = CompositeDisposable()

    override fun attachView(view: MainActivityViewKt) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun getArticles() {
        if (Utils.hasConnection(context)){
            disposables += interactor.downloadArticles()
                    .subscribe { response ->
                        view?.showArticles(response.articleList!!)
                        saveArticles(response.articleList)
            }
        }
        else {
            disposables += getArticlesFromDB().subscribe {
                view?.showArticles(Utils.convertArticleEntityToArticle(it))
            }
        }
    }

    override fun getArticlesFromDB(): Observable<List<ArticleEntity>> {
        return interactor.getArticlesFromDB()
    }

    override fun saveArticles(articles: MutableList<ArticleKt>) {
        interactor.saveArticles(articles)
    }

    override fun deleteArticlesFromDB() {
        interactor.deleteArticlesFromDB()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun detachView() {
        disposables.dispose()
        view = null
    }
}



