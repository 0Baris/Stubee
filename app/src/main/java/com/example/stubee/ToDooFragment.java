package com.example.stubee;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToDooFragment extends Fragment {

    RecyclerView todoo_gorevler;
    TextView gorev_tarih;
    EditText gorev_text;
    FloatingActionButton todoo_button;
    CardView todoo_cardview;


    //// Basit işler
    boolean todoo_durum = false;
    Calendar gününtakvimi = Calendar.getInstance();
    private int takyil = gününtakvimi.get(Calendar.YEAR);
    private int takAy = gününtakvimi.get(Calendar.MONTH);
    private int takGun = gününtakvimi.get(Calendar.DAY_OF_MONTH);




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view3 = inflater.inflate(R.layout.fragment_todoo, container, false);
        View view4 = inflater.inflate(R.layout.fragment_todooekle, container, false);
        View view5 = inflater.inflate(R.layout.fragment_todoo_gorev, container, false);

        todoo_button = view3.findViewById(R.id.todoo_button);
        todoo_cardview = view4.findViewById(R.id.todoo_cardview);
        todoo_gorevler = view3.findViewById(R.id.todoo_gorevler);
        gorev_tarih = view5.findViewById(R.id.todoo_tarihtext);
        gorev_text = view5.findViewById(R.id.todoo_edittext);

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
        todoo_durum = false;
        PopupWindow todoo_popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        todoo_popup.setFocusable(true);

        if  (todoo_durum == false){
            todoo_durum = true;

            todoo_popup.showAtLocation(anchorView, Gravity.CENTER,0,0);

            EditText gorevtext = popupView.findViewById(R.id.todoo_edittext);

            TextView tarihtext = popupView.findViewById(R.id.todoo_tarihtext);
            tarihtext.setText(takGun+"."+takAy+"."+ takyil);

            Button tarihb = popupView.findViewById(R.id.todoo_tarihbutton);
            tarihb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int yil, int ay, int gun) {

                            tarihtext.setText(String.valueOf(gun)+"."+String.valueOf(ay)+"."+String.valueOf(yil));

                        }
                    }, takyil, takAy, takGun);

                    datePickerDialog.show();
                }
            });


            Button to_kapat = popupView.findViewById(R.id.todoo_kapatbutton);
            to_kapat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todoo_durum = false;
                    todoo_popup.dismiss();
                }
            });

            Button to_kaydet = popupView.findViewById(R.id.todoo_kaydetbutton);
            to_kaydet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StubeeVeritabani beeDB = new StubeeVeritabani(getActivity());
                    beeDB.gorevEkle(gorevtext.getText().toString().trim(), tarihtext.getText().toString().trim());
                    todoo_durum = false;
                    todoo_popup.dismiss();
                }

            });

        }

    }
}