package ru.example.newsreader.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.example.newsreader.room.Dao.ArticleDao;
import ru.example.newsreader.room.Dao.SourceDao;
import ru.example.newsreader.room.Entity.ArticleEntity;
import ru.example.newsreader.room.Entity.SourceEntity;

@Database(entities = {ArticleEntity.class, SourceEntity.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleDao getArticleDao();

    public abstract SourceDao getSourceDao();
}