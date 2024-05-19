package com.example.stubee.notlar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stubee.R;
import com.example.stubee.notlar.veritabani.NotVeri;
import com.example.stubee.notlar.veritabani.NotVeritabani;
import com.example.stubee.notlar.veritabani.NotViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;


public class NotlarFragment extends Fragment {

    FloatingActionButton NotlarButton;
    NotViewModel notViewModel;
    RecyclerView NotlarRecycler;
    NotAdapter notAdapter;
    NotVeri notVeri;
    GifImageView gifImageView;
    TextView textView;
    private List<NotVeri> notVeri2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notlar, container, false);
        gifImageView = view.findViewById(R.id.NotYok_GIF);
        textView = view.findViewById(R.id.NotYok_Text);
        NotlarButton = view.findViewById(R.id.NotlarButton);
        NotlarRecycler = view.findViewById(R.id.NotlarRecycler);
        notViewModel = new ViewModelProvider(this).get(NotViewModel.class);

        NotlarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotEkle.class);
                startActivity(intent);
            }
        });

        notViewModel.hepsiniGetir.observe(getViewLifecycleOwner(), notVeri -> {
                NotlarRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                notAdapter = new NotAdapter(getContext(),notVeri);
                NotlarRecycler.setAdapter(notAdapter);
                if (notVeri != null && notVeri.isEmpty()) {
                    gifImageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                } else {
                    gifImageView.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
        } );


        return view;
    }

}