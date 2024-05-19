package com.example.stubee.notlar;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.stubee.databinding.FragmentNotduzenleBinding;
import com.example.stubee.notlar.veritabani.NotVeri;
import com.example.stubee.notlar.veritabani.NotViewModel;

public class NotDüzenle extends AppCompatActivity {

    FragmentNotduzenleBinding binding;
    int upid;
    String upbaslik,upaciklama,uptarih;
    NotViewModel notViewModel;
    Calendar gününtakvimi = Calendar.getInstance();
    private final int takyil = gününtakvimi.get(Calendar.YEAR);
    private final int takAy = gününtakvimi.get(Calendar.MONTH);
    private final int takGun = gününtakvimi.get(Calendar.DAY_OF_MONTH);
    private String TarihString = (takGun+"."+(takAy+1)+"."+ takyil);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNotduzenleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setNavigationBarColor(Color.parseColor("#C46C00"));
        getWindow().setStatusBarColor(Color.parseColor("#C46C00"));

        upid = getIntent().getIntExtra("id",0);
        upbaslik = getIntent().getStringExtra("baslik");
        upaciklama = getIntent().getStringExtra("aciklama");
        uptarih = getIntent().getStringExtra("tarih");

        binding.notekleEditbaslik.setText(upbaslik);
        binding.notekleEditnot.setText(upaciklama);

        notViewModel = new ViewModelProvider(this).get(NotViewModel.class);

        binding.notduzenleButton.setOnClickListener(v -> {
            String baslik = binding.notekleEditbaslik.getText().toString();
            String aciklama = binding.notekleEditnot.getText().toString();
            String tarih = TarihString;

            notDüzenle(baslik,aciklama,tarih);
        });

        binding.notdZenleSil.setOnClickListener(v -> {
            notViewModel.sil(upid);
            Toast.makeText(this, "Not başarıyla temizlendi!", Toast.LENGTH_SHORT).show();
            finish();
        });

    }

    private void notDüzenle(String baslik, String aciklama, String tarih) {

        NotVeri düzenle = new NotVeri();
        düzenle.id=upid;
        düzenle.not_baslik = baslik;
        düzenle.not_icerik = aciklama;
        düzenle.not_tarih = tarih;

        notViewModel.guncelle(düzenle);

        finish();
    }

}