/*

package ru.example.newsreader.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import ru.example.newsreader.room.Entity.ArticleEntity;

@Root(name = "item", strict = false)
public class Article {

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String link;

    @Element(name = "description")
    private String description;

    @Element(name = "pubDate")
    private String pubDate;

    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public ArticleEntity convertToArticleEntity(){
        return new ArticleEntity(this.title, this.description, this.pubDate, this.link, this.link.split("/")[2]);
    }
}
*/
