package ru.example.newsreader.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import ru.example.newsreader.room.Entity.ArticleEntity

@Root(name = "item", strict = false)
class ArticleKt () {
    @field:Element(name = "title")
    var title: String?=null

    @field:Element(name = "link")
    var link: String?=null

    @field:Element(name = "description")
    var description: String?=null

    @field:Element(name = "pubDate")
    var pubDate: String?=null
    var source: String?=null

    fun convertToArticleEntity() : ArticleEntity {
        return ArticleEntity(this.title!!, this.description!!, this.pubDate!!, this.link!!, this.link?.split("/".toRegex())?.get(2)!!)
    }
}
