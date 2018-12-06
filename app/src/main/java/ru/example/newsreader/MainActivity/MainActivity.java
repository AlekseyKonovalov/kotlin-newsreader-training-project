
package ru.example.newsreader.MainActivity;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;

import java.util.List;

import ru.example.newsreader.R;
import ru.example.newsreader.models.Article;
import ru.example.newsreader.room.AppDatabase;
import ru.example.newsreader.room.MigrationsKt;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    private MainPresenterKt presenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Stetho.initializeWithDefaults(this);



        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "rss2-database")
                .addMigrations(MigrationsKt.INSTANCE.getMIGRATION_1_2(), MigrationsKt.INSTANCE.getMIGRATION_2_3(), MigrationsKt.INSTANCE.getMIGRATION_3_4(),
                        MigrationsKt.INSTANCE.getMIGRATION_4_5())
                .fallbackToDestructiveMigration()
                .build();

        presenter = new MainPresenterKt(this, database, getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter.getArticlesInSource();
        presenter.downloadArticles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.remove_db) {
            presenter.deleteArticlesFromDB();
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showArticles(List<Article> articleList) {
        if(articleList!=null){
            mAdapter = new RVAdapterKt(articleList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}

