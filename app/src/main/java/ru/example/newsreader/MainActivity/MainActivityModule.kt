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
    fun providePresenter(activity: NewMainActivity, context: Context,
                         interactor: MainActivityInteractor): MainPresenterKt {
        val presenter = MainPresenterKtImpl(interactor, context)
        activity.lifecycle.addObserver(presenter)
        presenter.attachView(activity)
        return presenter
    }

    @PerActivity
    @Provides
    fun provideInteractor(db: AppDatabase): MainActivityInteractor {
        return MainActivityInteractorImpl(db)
    }

}