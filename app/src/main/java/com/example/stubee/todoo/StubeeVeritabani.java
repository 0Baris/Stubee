package com.example.stubee.todoo;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StubeeVeritabani extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "Stubee.db";
    public static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "stubee_database";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_GOREV = "todoo_görev";
    private static final String COLUMN_TARIH = "todoo_tarih";
    private static final String  COLUMN_DURUM = "todoo_durum";


    public StubeeVeritabani(@Nullable Context context) {
        super(context, DATABASE_NAME, null, Integer.parseInt(String.valueOf(DATABASE_VERSION)));
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_GOREV + " TEXT, " +
                        COLUMN_TARIH + " TEXT, " +
                        COLUMN_DURUM + " INTEGER );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void gorevEkle(String gorev,String tarih,int durum){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TARIH,tarih);
        cv.put(COLUMN_GOREV,gorev);
        cv.put(COLUMN_DURUM,durum);
        cv.put(COLUMN_DURUM,0);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context,"İşlem başarısız.",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Görev eklendi!",Toast.LENGTH_SHORT).show();
        }
    }

    public void gorevSil(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Bir hata oluştu.", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.i(TAG, "Row with _id=" + row_id + " deleted successfully");
            Toast.makeText(context, "Başarıyla Silindi!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    Cursor dataOku(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }



    public void updateTodoDurum(int gorevId, int durum) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (db == null) {
            Log.e("Database", "SQLiteDatabase is null. Database not available.");
            return;
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_DURUM , durum);
        int affectedRows = db.update(TABLE_NAME , values , "_id=?" , new String[]{String.valueOf(gorevId)});


        if (affectedRows > 0) {
            Log.d("Database", "Güncelleme Başarılı");
        } else {
            Log.d("Database", "Güncelleme Başarısız");
        }

        db.close();
    }





}
