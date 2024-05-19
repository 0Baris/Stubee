package com.example.stubee.notlar.veritabani;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotViewModel extends AndroidViewModel {


    public NotHelper notHelper;
    public LiveData<List<NotVeri>> hepsiniGetir;


    public NotViewModel(@NonNull Application application) {
        super(application);

        notHelper = new NotHelper(application);

        hepsiniGetir = notHelper.hepsiniGetir;

    }

    public void ekle(NotVeri notVeri){

        if (notHelper != null) {
            notHelper.not_ekle(notVeri);
            Log.w("NotViewModel", "Eklendi");
        } else {
            Log.w("NotViewModel", "Eklenemedi");
        }

    }

    public void sil(int id){
        notHelper.not_sil(id);
    }

    public void guncelle(NotVeri notVeri){
        notHelper.not_düzenle(notVeri);
    }



}
