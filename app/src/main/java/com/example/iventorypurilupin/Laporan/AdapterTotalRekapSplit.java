package com.example.iventorypurilupin.Laporan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_total_rekap_split.LaporanTotalSplitItem;

import java.util.List;

public class AdapterTotalRekapSplit extends RecyclerView.Adapter<AdapterTotalRekapSplit.MyViewHolder> {
    private Context context;
    private List<LaporanTotalSplitItem>totalSplitItems;

    public AdapterTotalRekapSplit(Context context, List<LaporanTotalSplitItem> totalSplitItems) {
        this.context = context;
        this.totalSplitItems = totalSplitItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.detil_rekap_total_split_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTotalSplit.setText(totalSplitItems.get(position).getSplit());

    }

    @Override
    public int getItemCount() {
        return totalSplitItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTotalSplit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTotalSplit = itemView.findViewById(R.id.tv_total_rekap_split);


        }
    }
}
