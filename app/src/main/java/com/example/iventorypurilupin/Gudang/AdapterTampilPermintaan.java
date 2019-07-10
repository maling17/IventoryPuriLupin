package com.example.iventorypurilupin.Gudang;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang.SplitItem;

import java.util.List;

public class AdapterTampilPermintaan extends RecyclerView.Adapter<AdapterTampilPermintaan.MyViewHolder> {
    private Context context;
    private List<SplitItem> splitItems;

    public AdapterTampilPermintaan(Context context, List<SplitItem> splitItems) {
        this.context = context;
        this.splitItems = splitItems;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.permintaan_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvid.setText(splitItems.get(i).getIdPermintaan());
        myViewHolder.tvTgl.setText(splitItems.get(i).getTglPermintaan());
        myViewHolder.tvTujuan.setText(splitItems.get(i).getTujuan());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SuratJalan.class);
                intent.putExtra("id_permintaan", splitItems.get(i).getIdPermintaan());
                intent.putExtra("tgl_permintaan", splitItems.get(i).getTglPermintaan());
                intent.putExtra("tujuan", splitItems.get(i).getTujuan());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return splitItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvid;
        private final TextView tvTgl;
        private final TextView tvTujuan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_nmr_permintaan);
            tvTgl = itemView.findViewById(R.id.tv_tgl_permintaan);
            tvTujuan = itemView.findViewById(R.id.tv_tujuan_permintaan);
        }
    }
}
