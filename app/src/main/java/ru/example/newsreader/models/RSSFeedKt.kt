/*
package ru.example.newsreader.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class RSSFeedKt {

    @field:Element(name = "title",required = true)
    @Path("channel")
    var chanelTitle: String ? = null

    @field:ElementList(name = "item", inline = true, required = false)
    @Path("channel")
    var articleList : List<ArticleKt> ? = null
}*/
