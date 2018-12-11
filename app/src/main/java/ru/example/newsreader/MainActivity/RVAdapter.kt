package ru.example.newsreader.MainActivity

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.example.newsreader.R
import ru.example.newsreader.models.ArticleKt


class RVAdapter(private val articles: List<ArticleKt>) : RecyclerView.Adapter<RVAdapter.ArticlesViewHolder>() {

    class ArticlesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cv: CardView = itemView.findViewById<View>(R.id.cv) as CardView
        val title: TextView = itemView.findViewById<View>(R.id.title) as TextView
        val pubDate: TextView = itemView.findViewById<View>(R.id.pub_date) as TextView
        val description: TextView = itemView.findViewById<View>(R.id.desc) as TextView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ArticlesViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item, viewGroup, false)
        return ArticlesViewHolder(v)
    }

    override fun onBindViewHolder(personViewHolder: ArticlesViewHolder, i: Int) {

        personViewHolder.title.text = articles[i].title
        personViewHolder.pubDate.text = "Опубликовано: ${articles[i].pubDate}"
        articles[i].description?.let {description ->
            personViewHolder.description.text = description
        }

        personViewHolder.itemView.setOnClickListener {
            val uri = Uri.parse(articles[i].link)
            val intent = Intent (Intent.ACTION_VIEW, uri)

            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

}