package com.example.stubee.notlar.veritabani;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotHelper {

    public NotDao notDao;
    public LiveData<List<NotVeri>> hepsiniGetir;


    public NotHelper(Application application) {
        NotVeritabani notVeritabani = NotVeritabani.getDatabaseInstance(application);
        notDao = notVeritabani.notDao();
        hepsiniGetir = notDao.hepsiniGetir();

    }

    void not_ekle(NotVeri notVeri)
    {
        notDao.ekle(notVeri);
    }

    void not_sil(int id)
    {
        notDao.sil(id);
    }

    void not_düzenle(NotVeri notVeri)
    {
        notDao.düzenle(notVeri);
    }

}
