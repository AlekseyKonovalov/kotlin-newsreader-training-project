package ru.example.newsreader.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
data class SourceEntity (
        @NonNull
        @PrimaryKey
        @ColumnInfo
        var name : String,
        @ColumnInfo
        var url : String
)