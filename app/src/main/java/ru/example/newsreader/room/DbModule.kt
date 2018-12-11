package ru.example.newsreader.room

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.saray.app.di.PerApplication

@Module
class DbModule {

    @PerApplication
    @Provides
    fun provideDbRepository(context: Context): AppDatabase {
        return  Room.databaseBuilder(context, AppDatabase::class.java, "rss2-database")
                .addMigrations(Migrations.MIGRATION_1_2, Migrations.MIGRATION_2_3,
                        Migrations.MIGRATION_3_4, Migrations.MIGRATION_4_5)
                .fallbackToDestructiveMigration()
                .build()
    }

}