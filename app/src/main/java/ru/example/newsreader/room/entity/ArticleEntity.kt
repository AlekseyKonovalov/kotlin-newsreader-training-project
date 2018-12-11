package ru.example.newsreader.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import ru.example.newsreader.models.ArticleKt

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
    fun convertToArticle() : ArticleKt {
        val article = ArticleKt()
        this.apply {
            article.title = title
            article.description = description
            article.pubDate = pubDate
            article.link = link
        }
        return article
    }
}