package com.example.iventorypurilupin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.response.MitraItem;

import java.util.List;

class AdapterMitra extends RecyclerView.Adapter<AdapterMitra.MyViewHolder> {
    Context context;
    List<MitraItem> mitra;

    public AdapterMitra(Context context, List<MitraItem> mitra) {
        this.context = context;
        this.mitra = mitra;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mitra, viewGroup, false);

        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMitra.MyViewHolder holder, int position) {

        holder.tvDaerah.setText(mitra.get(position).getDaerahMitra());
        holder.tvPIC.setText(mitra.get(position).getPicMitra());
        holder.tvTelp.setText(mitra.get(position).getTlpMitra());
        holder.tvAlamat.setText(mitra.get(position).getAlamatMitra());

    }

    @Override
    public int getItemCount() {
        return mitra.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDaerah;
        private final TextView tvPIC;
        private final TextView tvTelp;
        private final TextView tvAlamat;

        public MyViewHolder(View view) {
            super(view);

            tvDaerah = view.findViewById(R.id.tv_daerah);
            tvPIC = view.findViewById(R.id.tv_pic);
            tvTelp = view.findViewById(R.id.tv_telp);
            tvAlamat = view.findViewById(R.id.tv_alamat);


        }
    }
}
