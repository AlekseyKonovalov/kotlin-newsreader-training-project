/*
package ru.example.newsreader.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import ru.example.newsreader.room.Entity.ArticleEntity

@Root(name = "item", strict = true)
class ArticleKt {
    @field:Element(name = "title")
    var title: String? =null

    @field:Element(name = "link")
    var link: String? = null

    @field:Element(name = "description")
    var description: String? = null

    @field:Element(name = "pubDate")
    var pubDate: String? = null

    var source: String? = null

    fun convertToArticleEntity() : ArticleEntity {
        val articleEntity = ArticleEntity()
        articleEntity.title=this.title
        articleEntity.description=this.description
        articleEntity.pubDate= this.pubDate
        articleEntity.link=this.link
        articleEntity.source= this.link?.split("/".toRegex())?.get(2)
        return articleEntity
    }
}*/
