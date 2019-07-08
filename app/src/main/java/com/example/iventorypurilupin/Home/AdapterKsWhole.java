package com.example.iventorypurilupin.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_ks_whole.Ks_WholeItem;

import java.util.List;

public class AdapterKsWhole extends RecyclerView.Adapter<AdapterKsWhole.MyViewHolder> {
    private Context context;
    private List<Ks_WholeItem>ks_wholeItems;

    public AdapterKsWhole(Context context, List<Ks_WholeItem> ks_wholeItems) {
        this.context = context;
        this.ks_wholeItems = ks_wholeItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(context).inflate(R.layout.ks_whole_item,viewGroup,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvTgl.setText(ks_wholeItems.get(i).getTanggalOlah());
        myViewHolder.tvWholeAwal.setText(ks_wholeItems.get(i).getWholeAwal());
        myViewHolder.tvWholeOlah.setText(ks_wholeItems.get(i).getWOlah());
        myViewHolder.tvWholeAkhir.setText(ks_wholeItems.get(i).getWholeAkhir());

    }

    @Override
    public int getItemCount() {
        return ks_wholeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvWholeAkhir;
        private final TextView tvWholeOlah;
        private final TextView tvWholeAwal;
        private final TextView tvTgl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTgl = itemView.findViewById(R.id.tv_tgl_ks);
            tvWholeAwal = itemView.findViewById(R.id.tv_whole_awal);
            tvWholeOlah = itemView.findViewById(R.id.tv_whole_olah);
            tvWholeAkhir = itemView.findViewById(R.id.tv_whole_akhir);

        }
    }
}
