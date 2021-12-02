package com.example.theanimedatabase.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.theanimedatabase.R;
import com.example.theanimedatabase.ViewModel.DataViewModel;
import com.google.android.material.textfield.TextInputEditText;


public class SearchFragment extends Fragment {

    TextInputEditText searched_anime_name_et;
    ImageView search_anime_btn;

    public SearchFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        searched_anime_name_et = view.findViewById(R.id.anime_name_tv);
        search_anime_btn = view.findViewById(R.id.search_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataViewModel dataViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);

        search_anime_btn.setOnClickListener(v->{
            String anime_name = searched_anime_name_et.getText().toString();

            if(!anime_name.isEmpty() && anime_name.length() > 3)
            {
                dataViewModel.setAnime_name_searched(anime_name);
                dataViewModel.fetchList();
            }
            else Toast.makeText(getContext(),"invalid name",Toast.LENGTH_SHORT).show();
        });
    }
}