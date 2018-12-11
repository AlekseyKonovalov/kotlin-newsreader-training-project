package ru.example.newsreader.screens.main_activity

import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.facebook.stetho.Stetho
import dagger.android.support.DaggerAppCompatActivity
import ru.example.newsreader.R
import ru.example.newsreader.models.ArticleKt
import ru.example.newsreader.screens.main_activity.adapter.ArticlesAdapter
import javax.inject.Inject

interface MainActivityView{
    fun showArticles(articleList: List<ArticleKt>)

    fun showProgressBar()

    fun hideProgressBar()

    fun initViews()
}

class MainActivity :  DaggerAppCompatActivity(), MainActivityView {
    @Inject
    lateinit var presenter : MainActivityPresenter

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: ArticlesAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    private var mProgressBar : ContentLoadingProgressBar? = null
    private var mArticlesView: LinearLayout ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)

        initViews()
    }

    override fun hideProgressBar() {
        mProgressBar?.visibility = View.GONE
        mArticlesView?.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        mArticlesView?.visibility = View.GONE
        mProgressBar?.visibility = View.VISIBLE
    }


    override fun initViews() {
        mArticlesView=findViewById(R.id.articles_view)
        mProgressBar = findViewById(R.id.progressbar)

        mRecyclerView = findViewById(R.id.recycler_view)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView?.layoutManager = mLayoutManager
    }

    override fun showArticles(articleList: List<ArticleKt>) {
        mAdapter = ArticlesAdapter(articleList)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.remove_db -> {
                presenter.deleteArticlesFromDB()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}