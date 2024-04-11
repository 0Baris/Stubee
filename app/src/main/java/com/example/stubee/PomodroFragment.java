package com.example.stubee;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.stubee.R.id;

import org.w3c.dom.Text;

import java.util.Locale;

public class PomodroFragment extends Fragment {

    TextView suretext;
    CountDownTimer countDownTimer;
    Button baslat;
    Button resetle;
    private int pomodrosuresi = 1500;
    private int calısmasuresi = 300;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pomodro, container, false);

        suretext = view.findViewById(R.id.suretext);
        baslat = view.findViewById(id.pbaslat);
        resetle = view.findViewById(id.resetlebutton);

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
            countDownTimer = new CountDownTimer(pomodrosuresi * 1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long dakika = ((millisUntilFinished /1000) % 3600) / 60;
                    long saniye = (millisUntilFinished / 1000) % 60;
                    String sureFor = String.format(Locale.getDefault(),"%02d:%02d", dakika,saniye);
                    suretext.setText(sureFor);
                    baslat.setText("Durdur");
                }

                @Override
                public void onFinish() {
                    countDownTimer.cancel();
                    suretext.setText("00:00");
                    baslat.setText("Başlat");
                    Toast.makeText(getContext(), "Mola süresi başlıyor.", Toast.LENGTH_SHORT).show();
                    MediaPlayer pomodroalarm = MediaPlayer.create(getActivity(), R.raw.pomodroalert);
                    pomodroalarm.start();
                    countDownTimer = new CountDownTimer(calısmasuresi*1000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            long dakika = ((millisUntilFinished /1000) % 3600) / 60;
                            long saniye = (millisUntilFinished / 1000) % 60;
                            String sureFor = String.format(Locale.getDefault(),"%02d:%02d", dakika,saniye);
                            suretext.setText(sureFor);
                            baslat.setText("Durdur");
                        }

                        @Override
                        public void onFinish() {
                            countDownTimer.cancel();
                            suretext.setText("00:00");
                            baslat.setText("Başlat");
                            Toast.makeText(getContext(), "Mola süresi tamamlandı.", Toast.LENGTH_SHORT).show();
                            pomodroalarm.start();
                            sureBaslat();
                        }
                    }.start();
                }

            }.start();

        } else {
            timerTemizle();
            baslat.setText("Başlat");
        }


    }

    public void resetleButon(){
        timerTemizle();
        suretext.setText("00:00");
        baslat.setText("Başlat");
        Toast.makeText(getContext(), "Süre sıfırlandı.", Toast.LENGTH_SHORT).show();
    }

    private void timerTemizle(){
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }

}