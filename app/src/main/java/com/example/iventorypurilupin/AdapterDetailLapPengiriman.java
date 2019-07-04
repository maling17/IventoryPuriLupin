package com.example.iventorypurilupin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.response.response_detail.LaporanDetailItem;

import java.util.List;

public class AdapterDetailLapPengiriman extends RecyclerView.Adapter<AdapterDetailLapPengiriman.MyViewHolder> {

    private Context context;
    private List<LaporanDetailItem> laporanDetailItems;

    public AdapterDetailLapPengiriman(Context context, List<LaporanDetailItem> laporanDetailItems) {
        this.context = context;
        this.laporanDetailItems = laporanDetailItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.laporan_pengiriman_item,viewGroup,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvIdDetail.setText(laporanDetailItems.get(i).getIdSj());
        myViewHolder.tvTglDetail.setText(laporanDetailItems.get(i).getTglSj());
        myViewHolder.tvTujuanDetail.setText(laporanDetailItems.get(i).getTujuan());
        myViewHolder.tvSplitDetail.setText(laporanDetailItems.get(i).getSplitSj());
        myViewHolder.tvFlakeDetail.setText(laporanDetailItems.get(i).getFlakeSj());

    }

    @Override
    public int getItemCount() {
        return laporanDetailItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvIdDetail;
        private final TextView tvTujuanDetail;
        private final TextView tvTglDetail;
        private final TextView tvSplitDetail;
        private final TextView tvFlakeDetail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdDetail = itemView.findViewById(R.id.tv_no_detail);
            tvTglDetail = itemView.findViewById(R.id.tv_tgl_detail);
            tvTujuanDetail = itemView.findViewById(R.id.tv_tujuan_detail);
            tvSplitDetail = itemView.findViewById(R.id.tv_split_detail);
            tvFlakeDetail = itemView.findViewById(R.id.tv_flake_detail);



        }
    }
}
