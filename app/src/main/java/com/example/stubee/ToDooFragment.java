package com.example.stubee;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToDooFragment extends Fragment {

    FloatingActionButton todoo_button;
    CardView todoo_cardview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view3 = inflater.inflate(R.layout.fragment_todoo, container, false);
        View view4 = inflater.inflate(R.layout.fragment_todooekle, container, false);


        todoo_button = view3.findViewById(R.id.todoo_button);
        todoo_cardview = view4.findViewById(R.id.todoo_cardview);


        todoo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpGoster(v);
            }
        });

        return view3;
    }


    public void popUpGoster(View anchorView){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View popupView = layoutInflater.inflate(R.layout.fragment_todooekle, null);

        PopupWindow todoo_popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);

        todoo_popup.showAtLocation(anchorView, Gravity.CENTER,0,0);
        todoo_popup.setFocusable(true);

        Button to_kapat = popupView.findViewById(R.id.todoo_kapatbutton);

        to_kapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoo_popup.dismiss();
            }
        });


        Button to_kaydet = popupView.findViewById(R.id.todoo_kaydetbutton);
        to_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoo_popup.dismiss();
            }
        });

    }



}