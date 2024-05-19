package com.example.stubee.notlar.veritabani;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotVeri.class}, version = 1)
public abstract class NotVeritabani extends RoomDatabase {

    public abstract NotDao notDao();

    public static NotVeritabani notVeritabani;

    public static NotVeritabani getDatabaseInstance(Context context){
        if (notVeritabani == null){
            notVeritabani = Room.databaseBuilder(context.getApplicationContext(),
                    NotVeritabani.class,
                    "DB_Notlar").allowMainThreadQueries().build();
        }
        return notVeritabani;
    }

}
