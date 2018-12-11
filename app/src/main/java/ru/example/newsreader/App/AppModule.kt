package ru.example.newsreader.App

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.example.newsreader.retrofit.ApiModule
import ru.example.newsreader.room.DbModule


@Module(
        includes = [
            (DbModule::class),
            (ApiModule::class)
        ])
class AppModule {

    @Provides
    fun provideApplicationContext(app: App): Context = app.applicationContext

}