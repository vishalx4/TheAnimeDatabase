package com.example.theanimedatabase.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theanimedatabase.Adapters.AnimeListAdapter;
import com.example.theanimedatabase.Model.Anime;
import com.example.theanimedatabase.R;
import com.example.theanimedatabase.ViewModel.DataViewModel;

import java.util.ArrayList;
import java.util.List;


public class AnimeListFragment extends Fragment {

    RecyclerView recyclerView;
    AnimeListAdapter adapter;
    List<Anime> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_anime_list, container, false);
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataViewModel dataViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        dataViewModel.getListOfAnime().getValue();

        dataViewModel.getListOfAnime().observe(getActivity(), new Observer<List<Anime>>() {
            @Override
            public void onChanged(List<Anime> anime) {
                list = anime;
                adapter = new AnimeListAdapter(list);
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new AnimeListAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

    }
}