    package com.example.stubee.notlar;

    import android.graphics.Color;
    import android.icu.util.Calendar;
    import android.os.Bundle;
    import android.view.View;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.lifecycle.ViewModelProvider;

    import com.example.stubee.databinding.FragmentNotEkleBinding;
    import com.example.stubee.notlar.veritabani.NotVeri;
    import com.example.stubee.notlar.veritabani.NotViewModel;


    public class NotEkle extends AppCompatActivity {

        FragmentNotEkleBinding binding;
        String baslik , icerik, tarih;
        NotViewModel notViewModel;

        Calendar gününtakvimi = Calendar.getInstance();
        private final int takyil = gününtakvimi.get(Calendar.YEAR);
        private final int takAy = gününtakvimi.get(Calendar.MONTH);
        private final int takGun = gününtakvimi.get(Calendar.DAY_OF_MONTH);
        private String TarihString = (takGun+"."+(takAy+1)+"."+ takyil);


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = FragmentNotEkleBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            getWindow().setNavigationBarColor(Color.parseColor("#C46C00"));
            getWindow().setStatusBarColor(Color.parseColor("#C46C00"));

            notViewModel = new ViewModelProvider(this).get(NotViewModel.class);

            binding.ekleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    baslik = binding.ekleEditbaslik.getText().toString();
                    icerik = binding.ekleNot.getText().toString();
                    tarih = TarihString;

                    NotOlustur(baslik, icerik, tarih);

                    finish();
                }

                private void NotOlustur(String baslik, String icerik, String tarih) {

                    NotVeri notVeri2 = new NotVeri();
                    notVeri2.not_baslik= baslik;
                    notVeri2.not_icerik = icerik;
                    notVeri2.not_tarih = tarih;

                    notViewModel.ekle(notVeri2);
                }
            });


        }

    }