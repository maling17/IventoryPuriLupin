package com.example.iventorypurilupin.Laporan;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_detail_lap_stok.DetailWholeItem;

import java.util.List;

public class AdapterDetailStokWhole extends RecyclerView.Adapter<AdapterDetailStokWhole.MyViewHolder> {
    private Context context;
    private List<DetailWholeItem> wholeItems;

    public AdapterDetailStokWhole(Context context, List<DetailWholeItem> wholeItems) {
        this.context = context;
        this.wholeItems = wholeItems;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_whole_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvWholeAkhir.setText(wholeItems.get(i).getWholeAkhir());
        myViewHolder.tvWholeAwal.setText(wholeItems.get(i).getWholeAwal());
        myViewHolder.tvTglStok.setText(wholeItems.get(i).getTglPengolahan());
    }

    @Override
    public int getItemCount() {
        return wholeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvWholeAwal;
        private final TextView tvWholeAkhir;
        private final TextView tvTglStok;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTglStok = itemView.findViewById(R.id.tv_tgl_detail_stok);
            tvWholeAwal = itemView.findViewById(R.id.tv_whole_awal_stok);
            tvWholeAkhir = itemView.findViewById(R.id.tv_whole_akhir_stok);
        }
    }
}
