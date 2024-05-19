package com.example.stubee.todoo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stubee.R;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class ToDooAdapter extends RecyclerView.Adapter<ToDooAdapter.ViewHolder> {


    private final Context context;
    private final ArrayList gorev_id;
    private final ArrayList db_gorev;
    private final ArrayList db_tarih;
    private final ArrayList db_durum;
    StubeeVeritabani beeDBB;
    GifImageView gifImageView2;
    TextView textView2;
    private final int completedGifResId = R.drawable.animasyon;


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

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_todoo_gorev, parent,false);
        View view2 = inflater.inflate(R.layout.fragment_todoo, parent,false);

        gifImageView2 = view2.findViewById(R.id.ToDoo_GIF);
        textView2 = view.findViewById(R.id.ToDoo_TEXT);

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
            final MediaPlayer todooonay = MediaPlayer.create(context, R.raw.todoo_onaylandi);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage("Silmek istediğinize emin misiniz?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Evet", (dialog, which) -> {
                    beeDBB.gorevSil(gorev_id.get(position).toString());
                    alertDialog.dismiss();
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, gorev_id.size());
                    beeDBB.close();

                } );
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Hayır", (dialog, which) -> {
                    alertDialog.dismiss();
                } );
                alertDialog.show();
            }
        } );

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
        TextView db_gorev_text, db_tarih_text,textView2;
        GifImageView gifImageView;
        StubeeVeritabani stubeeVeritabani;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MediaPlayer todooonay = MediaPlayer.create(context, R.raw.todoo_onaylandi);
            appCompatCheckBox = itemView.findViewById(R.id.todoo_checkbox2);

            gifImageView2 = itemView.findViewById(R.id.ToDoo_GIF);
            textView2 = itemView.findViewById(R.id.ToDoo_TEXT);
            stubeeVeritabani = new StubeeVeritabani(context);

            db_gorev_text = itemView.findViewById(R.id.todoo_checkbox);
            db_tarih_text = itemView.findViewById(R.id.todoo_viewtext);

        }

    }
}
