package com.example.iventorypurilupin.Kacang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_kacang.response_flake.BarangFlakeItem;
import com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang.FlakeItem;

import java.util.List;

public class AdapterFlake extends RecyclerView.Adapter<AdapterFlake.MyViewHolder> {
    private Context context;
    private List<BarangFlakeItem> FlakeItems;

    public AdapterFlake(Context context, List<BarangFlakeItem> FlakeItems) {
        this.context = context;
        this.FlakeItems = FlakeItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.barang_flake_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvStok.setText(FlakeItems.get(position).getStok());

    }

    @Override
    public int getItemCount() {
        return FlakeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvStok;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStok = itemView.findViewById(R.id.tv_stok_flake);
        }
    }
}
