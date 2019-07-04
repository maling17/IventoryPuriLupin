package com.example.iventorypurilupin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.response.response_sj.LaporanItem;

import java.util.List;

public class AdapterLp extends RecyclerView.Adapter<AdapterLp.MyViewHolder> {
    private Context context;
    private List<LaporanItem>laporanItems;

    public AdapterLp(Context context, List<LaporanItem> laporanItems) {
        this.context = context;
        this.laporanItems = laporanItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.lp_item, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvIdLp.setText(laporanItems.get(i).getIdSj());
        myViewHolder.tvTglLp.setText(laporanItems.get(i).gettgl_sj());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LaporanPengiriman.class);
                intent.putExtra("id_sj", laporanItems.get(i).getIdSj());
                intent.putExtra("tgl_sj", laporanItems.get(i).gettgl_sj());
                intent.putExtra("tujuan", laporanItems.get(i).getTujuan());
                intent.putExtra("split_sj", laporanItems.get(i).getSplitSj());
                intent.putExtra("flake_sj", laporanItems.get(i).getFlakeSj());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return laporanItems.size()  ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvIdLp;
        private final TextView tvTglLp;
        private final RecyclerView rvLp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdLp = itemView.findViewById(R.id.tv_id_lp);
            tvTglLp = itemView.findViewById(R.id.tv_tanggal_lp);
            rvLp = itemView.findViewById(R.id.rv_lp);

        }
    }
}
