package ru.example.newsreader.screens.main_activity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import ru.example.newsreader.models.ArticleKt
import ru.example.newsreader.room.entity.ArticleEntity
import ru.example.newsreader.utils.Utils

interface MainActivityPresenter{
    fun getArticles()
    fun getArticlesFromDB(): Observable<List<ArticleEntity>>
    fun saveArticles(articles: MutableList<ArticleKt>)
    fun deleteArticlesFromDB()

    fun detachView()
    fun attachView(view: MainActivityView)
}

class MainActivityPresenterImpl (private val interactor: MainActivityInteractor,
                                 val context: Context) : LifecycleObserver, MainActivityPresenter {

    private var view: MainActivityView? = null
    private val disposables = CompositeDisposable()

    override fun attachView(view: MainActivityView) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun getArticles() {
        view?.showProgressBar()
        if (Utils.hasConnection(context)){
            disposables += interactor.downloadArticles()
                    .subscribe { response ->
                        view?.hideProgressBar()
                        view?.showArticles(response.articleList!!)
                        saveArticles(response.articleList)
            }
        }
        else {
            disposables += getArticlesFromDB().subscribe {
                view?.hideProgressBar()
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



