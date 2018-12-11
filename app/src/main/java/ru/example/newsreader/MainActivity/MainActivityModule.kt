package ru.example.newsreader.MainActivity

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.example.newsreader.retrofit.HabrService
import ru.example.newsreader.room.AppDatabase
import ru.saray.app.di.PerActivity

@Module
class MainActivityModule{

    @PerActivity
    @Provides
    fun providePresenter(activity: MainActivity, context: Context,
                         interactor: MainActivityInteractor): MainActivityPresenter {
        val presenter = MainActivityPresenterImpl(interactor, context)
        activity.lifecycle.addObserver(presenter)
        presenter.attachView(activity)
        return presenter
    }

    @PerActivity
    @Provides
    fun provideInteractor(db: AppDatabase, api: HabrService): MainActivityInteractor {
        return MainActivityInteractorImpl(db, api)
    }

}