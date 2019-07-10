package com.example.iventorypurilupin.Laporan;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_lap_rekap.LaporanRekapItem;

import java.util.List;

public class AdapterSplitRekap extends RecyclerView.Adapter<AdapterSplitRekap.MyViewHolder> {

    private Context context;
    private List<LaporanRekapItem>splitRekaps;

    public AdapterSplitRekap(Context context, List<LaporanRekapItem> splitRekaps) {
        this.context = context;
        this.splitRekaps = splitRekaps;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view= LayoutInflater.from(context).inflate(R.layout.detail_rekap_split_item,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvMitra.setText(splitRekaps.get(i).getDaerahMitra());
        myViewHolder.tvSplit.setText(splitRekaps.get(i).getSplit());
    }

    @Override
    public int getItemCount() {
        return splitRekaps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvMitra;
        private final TextView tvSplit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMitra = itemView.findViewById(R.id.tv_mitra_rekap);
            tvSplit = itemView.findViewById(R.id.tv_split_rekap);
        }
    }
}
