package com.example.stubee;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class PomodoroFragment extends Fragment {

    TextView suretext;
    CountDownTimer countDownTimer;
    Button baslat;
    Button resetle;
    private final int pomodrosuresi = 1500;
    private final int calismasuresi = 300;
    long kalanSure;

    GifImageView pomodorogif,timergif;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pomodro, container, false);

        suretext = view.findViewById(R.id.suretext);
        baslat = view.findViewById(R.id.pbaslat);
        resetle = view.findViewById(R.id.resetlebutton);
        pomodorogif = view.findViewById(R.id.gifImageView3);
        timergif = view.findViewById(R.id.gifImageView4);
        timergif.setVisibility(View.GONE);
        pomodorogif.setVisibility(View.VISIBLE);
        pomodorogif.setImageResource(R.drawable.pomodorobreak2);


        baslat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureBaslat();
            }
        });



        resetle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetleButon();
            }
        });

        return view;
    }

    private void sureBaslat() {

        if (baslat.getText().equals("Başlat")){
            Toast.makeText(getContext(), "Çalışma süresi başlıyor.", Toast.LENGTH_SHORT).show();
            pomodorogif.setImageResource(R.drawable.pomodorocalisma2);
            countDownTimer = new CountDownTimer(pomodrosuresi * 1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    kalanSure = millisUntilFinished;
                    long dakika = ((millisUntilFinished /1000) % 3600) / 60;
                    long saniye = (millisUntilFinished / 1000) % 60;
                    String sureFor = String.format(Locale.getDefault(),"%02d:%02d", dakika,saniye);
                    timergif.setVisibility(View.VISIBLE);
                    suretext.setText(sureFor);
                    baslat.setText(R.string.durdur);
                }

                @Override
                public void onFinish() {
                    countDownTimer.cancel();
                    suretext.setText("00:00");
                    baslat.setText(R.string.baslat);
                    Toast.makeText(getContext(), "Mola süresi başlıyor.", Toast.LENGTH_SHORT).show();
                    MediaPlayer pomodroalarm = MediaPlayer.create(getActivity(), R.raw.pomodroalert);
                    pomodroalarm.start();
                    pomodorogif.setImageResource(R.drawable.pomodorobreak2);
                    calismaSuresi();
                    timergif.setVisibility(View.GONE);
                }

            }.start();

        }
        else if (baslat.getText().equals("Başlat!")) {
            durdurButon();
        }
        else {
            timerTemizle();
            Toast.makeText(getContext(), "Süre durduruldu.", Toast.LENGTH_SHORT).show();
            baslat.setText(R.string.ba_lat);
        }


    }

    public void calismaSuresi(){
        pomodorogif.setImageResource(R.drawable.pomodorobreak2);
        countDownTimer = new CountDownTimer(calismasuresi*1000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                kalanSure = millisUntilFinished;
                long dakika = ((millisUntilFinished /1000) % 3600) / 60;
                long saniye = (millisUntilFinished / 1000) % 60;
                String sureFor = String.format(Locale.getDefault(),"%02d:%02d", dakika,saniye);
                suretext.setText(sureFor);
                baslat.setText(R.string.durdur);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                pomodorogif.setImageResource(R.drawable.pomodorocalisma2);
                suretext.setText("00:00");
                baslat.setText(R.string.baslat);
                Toast.makeText(getContext(), "Mola süresi tamamlandı.", Toast.LENGTH_SHORT).show();
                MediaPlayer pomodroalarm = MediaPlayer.create(getActivity(), R.raw.pomodroalert);
                pomodroalarm.start();
                sureBaslat();
            }
        }.start();
    }

    public void durdurButon(){
        if (baslat.getText().equals("Başlat!")){
            Toast.makeText(getContext(), "Tekrardan iyi çalışmalar <3", Toast.LENGTH_SHORT).show();
            pomodorogif.setImageResource(R.drawable.pomodorocalisma2);
            countDownTimer = new CountDownTimer(kalanSure,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    kalanSure = millisUntilFinished;
                    long dakika = ((millisUntilFinished /1000) % 3600) / 60;
                    long saniye = (millisUntilFinished / 1000) % 60;
                    String sureFor = String.format(Locale.getDefault(),"%02d:%02d", dakika,saniye);
                    suretext.setText(sureFor);
                    baslat.setText(R.string.durdur);
                }

                @Override
                public void onFinish() {
                    calismaSuresi();
                    pomodorogif.setImageResource(R.drawable.pomodorobreak2);
                }
            }.start();
        }
        else {
            pomodorogif.setImageResource(R.drawable.pomodorobreak2);
            timerTemizle();
            baslat.setText("Başlat!");

        }
    }

    @SuppressLint("SetTextI18n")
    public void resetleButon(){
        timerTemizle();
        timergif.setVisibility(View.GONE);
        pomodorogif.setImageResource(R.drawable.pomodorobreak2);
        suretext.setText("00:00");
        baslat.setText(R.string.baslat);
        Toast.makeText(getContext(), "Süre sıfırlandı.", Toast.LENGTH_SHORT).show();
    }

    private void timerTemizle(){
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}