package com.example.stubee.notlar.veritabani;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotDao {

    @Query("SELECT * FROM DB_Notlar")
    LiveData<List<NotVeri>> hepsiniGetir();


    @Insert
    void ekle(NotVeri... not);

    @Query("DELETE FROM DB_Notlar WHERE id=:id")
    void sil(int id);

    @Update
    void düzenle(NotVeri not);


}
