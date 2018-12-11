package ru.example.newsreader.MainActivity

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.example.newsreader.room.AppDatabase
import ru.saray.app.di.PerActivity

@Module
class MainActivityModule{

    @PerActivity
    @Provides
    fun providePresenter(activity: NewMainActivity, database: AppDatabase, context: Context): MainPresenterKt {
        val presenter = MainPresenterKtImpl(database, context)
        activity.lifecycle.addObserver(presenter)
        presenter.attachView(activity)
        return presenter
    }

}