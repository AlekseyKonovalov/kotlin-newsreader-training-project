package ru.example.newsreader.room.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import ru.example.newsreader.models.Article;

@Entity/*(foreignKeys = @ForeignKey(
        entity = SourceEntity.class,
        parentColumns = "title",
        childColumns = "source",
        onDelete =  ForeignKey.CASCADE))*/
public class ArticleEntity {
    @NonNull
    @PrimaryKey
    private String title;
    private String description;
    private String pubDate;
    private String link;
    private String source;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Article convertToArticle(){
        Article article = new Article();
        article.setTitle(this.title);
        article.setDescription(this.description);
        article.setPubDate(this.pubDate);
        article.setLink(this.link);
        return  article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
