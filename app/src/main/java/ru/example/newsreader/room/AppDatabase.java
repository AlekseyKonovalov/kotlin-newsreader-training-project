package ru.example.newsreader.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.example.newsreader.room.Dao.ArticleDaoKt;
import ru.example.newsreader.room.Dao.SourceDaoKt;
import ru.example.newsreader.room.Entity.ArticleEntity;
import ru.example.newsreader.room.Entity.SourceEntity;

@Database(entities = {ArticleEntity.class, SourceEntity.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 5)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleDaoKt getArticleDao();

    public abstract SourceDaoKt getSourceDao();
}