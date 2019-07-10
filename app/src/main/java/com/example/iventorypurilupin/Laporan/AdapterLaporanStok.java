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
import com.example.iventorypurilupin.response.response_lap_stok.LaporanStokItem;

import java.util.List;

public class AdapterLaporanStok extends RecyclerView.Adapter<AdapterLaporanStok.MyViewHolder> {
   private Context context;
    private List<LaporanStokItem>stokList;

    public AdapterLaporanStok(Context context, List<LaporanStokItem> stokList) {
        this.context = context;
        this.stokList = stokList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(context).inflate(R.layout.lap_stok_item,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvTanggal.setText(stokList.get(i).getTglPengolahan());
        final String tgl_pengolahan=myViewHolder.tvTanggal.getText().toString();
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailLapStok.class);
                intent.putExtra("tgl_pengolahan",tgl_pengolahan);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stokList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTanggal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tgl_stok);
        }
    }
}
