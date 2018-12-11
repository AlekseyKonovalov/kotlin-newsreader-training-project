package ru.example.newsreader.MainActivity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.facebook.stetho.Stetho
import dagger.android.support.DaggerAppCompatActivity
import ru.example.newsreader.R
import ru.example.newsreader.models.ArticleKt
import javax.inject.Inject

interface MainActivityViewKt{
    fun showArticles(articleList: List<ArticleKt>)
}

class NewMainActivity :  DaggerAppCompatActivity(), MainActivityViewKt {

    @Inject
    lateinit var presenter : MainPresenterKt

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RVAdapterKt? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)

        mRecyclerView = findViewById(R.id.recycler_view)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView?.layoutManager = mLayoutManager
    }

    override fun showArticles(articleList: List<ArticleKt>) {
        mAdapter = RVAdapterKt(articleList)
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