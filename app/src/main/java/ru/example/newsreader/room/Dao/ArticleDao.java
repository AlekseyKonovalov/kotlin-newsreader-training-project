package ru.example.newsreader.room.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.example.newsreader.room.Entity.ArticleEntity;

@Dao
public interface ArticleDao {
    @Insert
    void insert(ArticleEntity article);

    @Update
    void update(ArticleEntity article);

    @Delete
    void delete(ArticleEntity article);

    @Query("SELECT * FROM ArticleEntity")
    List<ArticleEntity> getAllArticles();

    @Query("DELETE FROM ArticleEntity")
    void deleteAll();

   /* @Query("SELECT * FROM ArticleEntity WHERE source=:source")
    List<ArticleEntity> findArticlesForSource(final String source);*/
}
