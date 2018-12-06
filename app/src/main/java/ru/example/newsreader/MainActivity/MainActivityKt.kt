/*
package ru.example.newsreader.MainActivity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.facebook.stetho.Stetho
import ru.example.newsreader.R
import ru.example.newsreader.models.Article
import ru.example.newsreader.room.AppDatabase
import ru.example.newsreader.room.MigrationsKt

interface MainActivityViewKt{
    fun showArticles(articleList : List<Article>)
}

class MainActivityKt : AppCompatActivity(), MainActivityViewKt {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RVAdapterKt? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var database: AppDatabase? = null
    private var presenter : MainPresenterKt? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)

        database = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "rss2-database")
                .addMigrations(MigrationsKt.MIGRATION_1_2, MigrationsKt.MIGRATION_2_3,
                        MigrationsKt.MIGRATION_3_4, MigrationsKt.MIGRATION_4_5)
                .fallbackToDestructiveMigration()
                .build()

        database?.let {
            presenter = MainPresenterKt(this, it, applicationContext)
            mRecyclerView = findViewById(R.id.recycler_view)
            mLayoutManager = LinearLayoutManager(this)
            mRecyclerView?.layoutManager = mLayoutManager

            presenter?.getArticlesInSource()
            presenter?.downloadArticles()
        }


    }

    override fun showArticles(articleList: List<Article>) {
        mAdapter = RVAdapterKt(articleList)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.remove_db -> {
                presenter?.deleteArticlesFromDB()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}*/
