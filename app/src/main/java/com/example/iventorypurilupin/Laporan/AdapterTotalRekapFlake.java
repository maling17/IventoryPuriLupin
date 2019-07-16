package com.example.iventorypurilupin.Laporan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_total_rekap_flake.LaporanTotalFlakeItem;

import java.util.List;

public class AdapterTotalRekapFlake extends RecyclerView.Adapter<AdapterTotalRekapFlake.MyViewHolder> {
    private Context context;
    private List<LaporanTotalFlakeItem> totalFlakeItems;

    public AdapterTotalRekapFlake(Context context, List<LaporanTotalFlakeItem> totalFlakeItems) {
        this.context = context;
        this.totalFlakeItems = totalFlakeItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.detil_rekap_total_flake_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTotalFlake.setText(totalFlakeItems.get(position).getFlake());

    }

    @Override
    public int getItemCount() {
        return totalFlakeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTotalFlake;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTotalFlake = itemView.findViewById(R.id.tv_total_rekap_flake);


        }
    }
}
