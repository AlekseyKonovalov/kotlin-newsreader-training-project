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
                .addMigrations(MigrationsKt.MIGRATION_1_2, MigrationsKt.MIGRATION_2_3,
                        MigrationsKt.MIGRATION_3_4, MigrationsKt.MIGRATION_4_5)
                .fallbackToDestructiveMigration()
                .build()
    }

}