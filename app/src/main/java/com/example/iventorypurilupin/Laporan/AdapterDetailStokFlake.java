package com.example.iventorypurilupin.Laporan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_detail_lap_stok_flake.DetailFlakeItem;

import java.util.List;

public class AdapterDetailStokFlake extends RecyclerView.Adapter<AdapterDetailStokFlake.MyViewHolder> {
    private Context context;
    private List<DetailFlakeItem> flakeItems;

    public AdapterDetailStokFlake(Context context, List<DetailFlakeItem> flakeItems) {
        this.context = context;
        this.flakeItems = flakeItems;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_flake_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvflakeAkhir.setText(flakeItems.get(i).getFlakeAkhir());
        myViewHolder.tvflakeAwal.setText(flakeItems.get(i).getFlakeAwal());

    }

    @Override
    public int getItemCount() {
        return flakeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvflakeAwal;
        private final TextView tvflakeAkhir;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvflakeAwal = itemView.findViewById(R.id.tv_flake_awal_stok);
            tvflakeAkhir = itemView.findViewById(R.id.tv_flake_akhir_stok);
        }
    }
}