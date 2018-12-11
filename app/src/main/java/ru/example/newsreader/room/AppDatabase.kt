
package ru.example.newsreader.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.example.newsreader.room.Dao.ArticleDao
import ru.example.newsreader.room.Dao.SourceDao
import ru.example.newsreader.room.Entity.ArticleEntity
import ru.example.newsreader.room.Entity.SourceEntity

@Database(entities = [ArticleEntity::class, SourceEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticleDao() : ArticleDao
    abstract fun getSourceDao() : SourceDao
}
