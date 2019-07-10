package com.example.iventorypurilupin.Laporan;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_detail_lap_stok_split.DetailSplitItem;

import java.util.List;

public class AdapterDetailStokSplit extends RecyclerView.Adapter<AdapterDetailStokSplit.MyViewHolder> {
    private Context context;
    private List<DetailSplitItem> splitItems;

    public AdapterDetailStokSplit(Context context, List<DetailSplitItem> splitItems) {
        this.context = context;
        this.splitItems = splitItems;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_split_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvsplitAkhir.setText(splitItems.get(i).getSplitAkhir());
        myViewHolder.tvsplitAwal.setText(splitItems.get(i).getSplitAwal());

    }

    @Override
    public int getItemCount() {
        return splitItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvsplitAwal;
        private final TextView tvsplitAkhir;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvsplitAwal = itemView.findViewById(R.id.tv_split_awal_stok);
            tvsplitAkhir = itemView.findViewById(R.id.tv_split_akhir_stok);
        }
    }
}
