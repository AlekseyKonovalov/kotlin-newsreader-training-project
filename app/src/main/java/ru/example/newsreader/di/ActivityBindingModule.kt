package ru.example.newsreader.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.example.newsreader.screens.main_activity.MainActivityModule
import ru.example.newsreader.screens.main_activity.MainActivity
import ru.saray.app.di.PerActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun mainActivity(): MainActivity

}