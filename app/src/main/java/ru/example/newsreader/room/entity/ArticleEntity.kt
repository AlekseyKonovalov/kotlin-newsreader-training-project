package ru.example.newsreader.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import ru.example.newsreader.models.Article

@Entity
class ArticleEntity(
        @NotNull
        @PrimaryKey
        var title : String,
        var description : String,
        var pubDate : String,
        var link : String,
        var source : String
) {
    fun convertToArticle() : Article {
        val article = Article()
        this.apply {
            article.title = title
            article.description = description
            article.pubDate = pubDate
            article.link = link
        }
        return article
    }
}