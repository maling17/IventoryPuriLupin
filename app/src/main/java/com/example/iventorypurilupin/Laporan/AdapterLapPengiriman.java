package com.example.iventorypurilupin.Laporan;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_laporan_pengiriman.LaporanItem;

import java.util.List;

public class AdapterLapPengiriman extends RecyclerView.Adapter<AdapterLapPengiriman.MyViewHolder> {
   private Context context;
   private List<LaporanItem> laporanItems;

    public AdapterLapPengiriman(Context context, List<LaporanItem> laporanItems) {
        this.context = context;
        this.laporanItems = laporanItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.lp_item,viewGroup,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvTgl.setText(laporanItems.get(i).getTglSj());


        final String tgl=myViewHolder.tvTgl.getText().toString();
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LaporanPengiriman.class);
                intent.putExtra("tgl",tgl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return laporanItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTgl;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTgl = itemView.findViewById(R.id.tv_tanggal_lp);

        }
    }
}
