package ru.example.newsreader.room.Entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import ru.example.newsreader.models.Article

@Entity
class ArticleEntity {
    @NotNull
    @PrimaryKey
    lateinit var title : String
    var description : String ?  = null
    var pubDate : String ? = null
    var link : String ? = null
    var source : String ? = null

    fun convertToArticle() : Article {
        var article = Article()
        article.title = this.title
        article.description=this.description
        article.pubDate=this.pubDate
        article.link=this.link
        return article
    }
}