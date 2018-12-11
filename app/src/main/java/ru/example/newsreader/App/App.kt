package ru.example.newsreader.App

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val daggerAppComponent = DaggerAppComponent.builder().create(this)
        return daggerAppComponent
    }
}