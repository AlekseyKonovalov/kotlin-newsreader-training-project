/*
package ru.example.newsreader.MainActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.example.newsreader.App.App;
import ru.example.newsreader.R;
import ru.example.newsreader.models.Article;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ArticlesViewHolder> {

    private List<Article> articles;

    public RVAdapter(List<Article> articles) {
        this.articles = articles;
    }

    public static class ArticlesViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView pubDate;
        TextView description;


        ArticlesViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            title = (TextView) itemView.findViewById(R.id.title);
            pubDate = (TextView) itemView.findViewById(R.id.pub_date);
            description = (TextView) itemView.findViewById(R.id.desc);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ArticlesViewHolder pvh = new ArticlesViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder personViewHolder, int i) {
        if(articles.get(i).getTitle()!=null){
            personViewHolder.title.setText(articles.get(i).getTitle());
        }
        personViewHolder.pubDate.setText("Опубликовано: " + articles.get(i).getPubDate());
        personViewHolder.description.setText(articles.get(i).getDescription());

        personViewHolder.itemView.setOnClickListener(v -> {
            Uri uri = Uri.parse(articles.get(i).getLink());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            App.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
*/
