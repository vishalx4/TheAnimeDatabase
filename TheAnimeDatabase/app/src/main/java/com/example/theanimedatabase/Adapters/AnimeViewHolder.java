package com.example.theanimedatabase.Adapters;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.theanimedatabase.Model.Anime;
import com.example.theanimedatabase.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AnimeViewHolder extends RecyclerView.ViewHolder {

    TextView anime_name_tv;
    ImageView imageView;

    public AnimeViewHolder(@NonNull View itemView) {
        super(itemView);

        anime_name_tv = itemView.findViewById(R.id.anime_name);
        imageView = itemView.findViewById(R.id.anime_image);

    }

    public void bindAnime(Anime anime){
        anime_name_tv.setText(anime.getName());
        Glide.with(itemView)
                .load(anime.getImage_url())
                .into(imageView);
    }

}
