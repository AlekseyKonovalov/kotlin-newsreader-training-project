package ru.example.newsreader.room.dao

import android.arch.persistence.room.*
import ru.example.newsreader.room.entity.ArticleEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article : ArticleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articleList: List<ArticleEntity>)

    @Update
    fun update(article: ArticleEntity)

    @Delete
    fun delete(article: ArticleEntity)

    @Query("SELECT * FROM ArticleEntity")
    fun getAllArticles() : List<ArticleEntity>

    @Query("DELETE FROM ArticleEntity")
    fun deleteAll()
}