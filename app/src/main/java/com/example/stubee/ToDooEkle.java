package com.example.stubee;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;


public class ToDooEkle extends Fragment {

    Calendar gününtakvimi = Calendar.getInstance();
    private int takYıl = gününtakvimi.get(Calendar.YEAR);
    private int takAy = gününtakvimi.get(Calendar.MONTH);
    private int takGun = gününtakvimi.get(Calendar.DAY_OF_MONTH);
    CardView todoo_cardview;
    TextView tarihyazi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view4 = inflater.inflate(R.layout.fragment_todooekle, container, false);



        return inflater.inflate(R.layout.fragment_todooekle, container, false);
    }


}