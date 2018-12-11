package ru.example.newsreader.room.Dao

import android.arch.persistence.room.*
import ru.example.newsreader.room.Entity.SourceEntity

@Dao
interface SourceDao {
    @Insert
    fun insert(sourceEntity: SourceEntity)

    @Update
    fun update(sourceEntity: SourceEntity)

    @Delete
    fun delete(sourceEntity: SourceEntity)

    @Query("SELECT * FROM SourceEntity")
    fun getAllSources() : List<SourceEntity>

    @Query("DELETE FROM SourceEntity")
    fun deleteAll()
}