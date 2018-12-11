package ru.example.newsreader.utils

import android.content.Context
import android.net.ConnectivityManager
import ru.example.newsreader.models.Article
import ru.example.newsreader.room.entity.ArticleEntity

class Utils {
    companion object {
        fun hasConnection(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val internetInfo = cm.activeNetworkInfo

            return internetInfo != null && internetInfo.isConnected
        }

        fun convertArticleEntityToArticle(articles : List<ArticleEntity>): MutableList<Article> {
            val articleList: MutableList<Article> = arrayListOf()
            for (articleEntity in articles) {
                articleList.add(articleEntity.convertToArticle())
            }
            return articleList
        }
    }
}