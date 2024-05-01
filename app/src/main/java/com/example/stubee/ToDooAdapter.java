package com.example.stubee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ToDooAdapter extends RecyclerView.Adapter<ToDooAdapter.ViewHolder> {


    private Context context;
    private ArrayList gorev_id;
    private ArrayList db_gorev;
    private ArrayList db_tarih;
    private ArrayList db_durum;
    StubeeVeritabani beeDBB;
    private boolean firstClick = true;

    ToDooAdapter(Context context,
                 ArrayList gorev_id,
                 ArrayList db_gorev,
                 ArrayList db_tarih,
                 ArrayList db_durum){

        this.context = context;
        this.gorev_id = gorev_id;
        this.db_gorev = db_gorev;
        this.db_tarih = db_tarih;
        this.db_durum = db_durum;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_todoo_gorev, parent,false);
        beeDBB = new StubeeVeritabani(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.db_gorev_text.setText(String.valueOf(db_gorev.get(position)));
        holder.db_tarih_text.setText(String.valueOf(db_tarih.get(position)));

        holder.appCompatCheckBox.setChecked(booleandonustur(Integer.parseInt(db_durum.get(position).toString())));

        holder.appCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int a = Integer.parseInt(gorev_id.get(position).toString());
                    beeDBB.updateTodoDurum(a,1);
                    System.out.println(a);
                    Log.d("UI", "Buton tiklendi");
                }
                else
                {
                    int a = Integer.parseInt(gorev_id.get(position).toString());
                    System.out.println(a);
                    beeDBB.updateTodoDurum(a,0);
                    Log.d("UI", "Butondan tik kaldırıldı");
                }

            }
        });
        holder.appCompatCheckBox.setOnClickListener(new View.OnClickListener() {
            MediaPlayer todooonay = MediaPlayer.create(context, R.raw.todoo_onaylandi);
            @Override
            public void onClick(View v) {
                if (holder.appCompatCheckBox.isChecked()) {
                    todooonay.start();
                    holder.db_gorev_text.setPaintFlags(holder.db_gorev_text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    System.out.println("Buton Tikli");
                } else {
                    System.out.println("Buton Tiksiz");
                    holder.db_gorev_text.setPaintFlags(holder.db_gorev_text.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }
        });

        if (holder.appCompatCheckBox.isChecked()) {
            holder.db_gorev_text.setPaintFlags(holder.db_gorev_text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.db_gorev_text.setPaintFlags(holder.db_gorev_text.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

    }

    public boolean booleandonustur(int num){
        return num!=0;
    }


    @Override
    public int getItemCount() {
        return gorev_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatCheckBox appCompatCheckBox;
        TextView db_gorev_text, db_tarih_text;
        StubeeVeritabani stubeeVeritabani;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MediaPlayer todooonay = MediaPlayer.create(context, R.raw.todoo_onaylandi);
            appCompatCheckBox = itemView.findViewById(R.id.todoo_checkbox2);

            stubeeVeritabani = new StubeeVeritabani(context);

            db_gorev_text = itemView.findViewById(R.id.todoo_checkbox);
            db_tarih_text = itemView.findViewById(R.id.todoo_viewtext);




        }

    }
}
