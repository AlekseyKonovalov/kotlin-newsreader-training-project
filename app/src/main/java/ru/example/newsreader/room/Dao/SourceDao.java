package ru.example.newsreader.room.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.example.newsreader.room.Entity.SourceEntity;

@Dao
public interface SourceDao {
    @Insert
    void insert(SourceEntity sourceEntity);

    @Update
    void update(SourceEntity sourceEntity);

    @Delete
    void delete(SourceEntity sourceEntity);

    @Query("SELECT * FROM SourceEntity")
    List<SourceEntity> getAllSources();

    @Query("DELETE FROM SourceEntity")
    void deleteAll();


}
