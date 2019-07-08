package com.example.iventorypurilupin.Laporan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_lap_rekap.LaporanRekapItem;
import com.example.iventorypurilupin.response.response_lap_rekap_flake.LaporanRekapFlakeItem;

import java.util.List;

public class AdapterFlakeRekap extends RecyclerView.Adapter<AdapterFlakeRekap.MyViewHolder> {

    private Context context;
    private List<LaporanRekapFlakeItem>flakeRekaps;

    public AdapterFlakeRekap(Context context, List<LaporanRekapFlakeItem> flakeRekaps) {
        this.context = context;
        this.flakeRekaps = flakeRekaps;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view= LayoutInflater.from(context).inflate(R.layout.detail_rekap_flake_item,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.tvFlake.setText(flakeRekaps.get(i).getFlake());
    }

    @Override
    public int getItemCount() {
        return flakeRekaps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final TextView tvFlake;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFlake = itemView.findViewById(R.id.tv_flake_rekap);
        }
    }
}
