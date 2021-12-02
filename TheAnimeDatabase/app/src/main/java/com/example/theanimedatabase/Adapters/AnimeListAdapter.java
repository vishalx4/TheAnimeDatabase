package com.example.theanimedatabase.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theanimedatabase.Model.Anime;
import com.example.theanimedatabase.R;

import java.util.List;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeViewHolder> {
    
    List<Anime> animeList;
    
    public AnimeListAdapter(List<Anime> animeList){
        this.animeList = animeList;    
    }


    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimeViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.anime_card,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        holder.bindAnime(animeList.get(position));
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }
}
