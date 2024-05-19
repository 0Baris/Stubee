package com.example.stubee.notlar.veritabani;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DB_Notlar")
public class NotVeri {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "baslik")
    public String not_baslik;

    @ColumnInfo(name = "icerik")
    public String not_icerik;

    @ColumnInfo(name = "tarih")
    public String not_tarih;


}
