package ru.example.newsreader

import android.content.Context
import android.net.ConnectivityManager
import ru.example.newsreader.models.ArticleKt
import ru.example.newsreader.room.Entity.ArticleEntity

class UtilsKt {
    companion object {
        fun hasConnection(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val internetInfo = cm.activeNetworkInfo

//        if (wifiInfo != null && wifiInfo.isConnected) {
//            return true
//        }
//        return false

            return internetInfo != null && internetInfo.isConnected
        }

        fun convertArticleEntityToArticle(articles : List<ArticleEntity>): MutableList<ArticleKt> {
            val articleList: MutableList<ArticleKt> = arrayListOf()
            for (articleEntity in articles) {
                articleList.add(articleEntity.convertToArticle())
            }
            return articleList
        }
    }
}