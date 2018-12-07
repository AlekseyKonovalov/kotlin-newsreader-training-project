

package ru.example.newsreader.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="rss", strict=false)
public class RSSFeed {

    @Element(name="title")
    @Path("channel")
    private String channelTitle;

    @ElementList(name="item", inline=true)
    @Path("channel")
    private List<ArticleKt> articleList;

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public List<ArticleKt> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleKt> articleList) {
        this.articleList = articleList;
    }


}

