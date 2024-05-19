package com.example.stubee.notlar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stubee.R;
import com.example.stubee.notlar.veritabani.NotVeri;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class NotAdapter extends RecyclerView.Adapter<NotAdapter.notViewHolder> {


    private Context context;
    private List<NotVeri> notVeri;


    public NotAdapter(Context context, List<NotVeri> notVeri) {
        this.context = context;
        this.notVeri = notVeri;
    }

    @Override
    public notViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.not_row, parent, false));
    }

    @Override
    public void onBindViewHolder(notViewHolder holder, int position) {
        NotVeri notVeri1 = notVeri.get(position);

        holder.baslik.setText(notVeri1.not_baslik);
        holder.aciklama.setText(notVeri1.not_icerik);
        holder.tarih.setText(notVeri1.not_tarih);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,
                    NotDüzenle.class);

            intent.putExtra("id", notVeri1.id);
            intent.putExtra("baslik", notVeri1.not_baslik);
            intent.putExtra("aciklama", notVeri1.not_icerik);
            intent.putExtra("tarih", notVeri1.not_tarih);

            context.startActivity(intent);
        });



    }

    @Override
    public int getItemCount() {
        return notVeri.size();
    }

    class notViewHolder extends RecyclerView.ViewHolder {

        TextView baslik, aciklama, tarih;

        public notViewHolder( View itemView) {

            super(itemView);

            baslik = itemView.findViewById(R.id.notrow_baslik);
            aciklama = itemView.findViewById(R.id.notrow_notkisimi);
            tarih = itemView.findViewById(R.id.notrow_tarih);



        }


    }



}
