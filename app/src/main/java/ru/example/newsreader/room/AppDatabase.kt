
package ru.example.newsreader.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.example.newsreader.room.dao.ArticleDao
import ru.example.newsreader.room.dao.SourceDao
import ru.example.newsreader.room.entity.ArticleEntity
import ru.example.newsreader.room.entity.SourceEntity

@Database(entities = [ArticleEntity::class, SourceEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticleDao() : ArticleDao
    abstract fun getSourceDao() : SourceDao
}
