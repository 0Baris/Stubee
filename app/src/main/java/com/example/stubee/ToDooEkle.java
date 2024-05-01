package com.example.stubee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ToDooEkle extends AppCompatActivity {

    EditText gorev_text;
    TextView gorev_tarih;
    AppCompatButton gorev_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_todooekle);
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View popupView = layoutInflater.inflate(R.layout.fragment_todooekle, null);

        gorev_text = findViewById(R.id.todoo_edittext);
        gorev_tarih = findViewById(R.id.todoo_tarihtext);
        gorev_button = popupView.findViewById(R.id.todoo_kaydetbutton);

    }
}