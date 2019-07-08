package com.example.iventorypurilupin.Gudang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_stok_awal.Stok_awal_Item;

import java.util.List;

public class AdapterStokAwal extends RecyclerView.Adapter<AdapterStokAwal.MyViewHolder> {
   private Context context;
   private List<Stok_awal_Item> stokItems;

    public AdapterStokAwal(Context context, List<Stok_awal_Item> stokItems) {
        this.context = context;
        this.stokItems = stokItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(context).inflate(R.layout.stok_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvJenisBrg.setText(stokItems.get(i).getJenisBrg());
        myViewHolder.tvStokAwal.setText(stokItems.get(i).getStokAwal());
    }

    @Override
    public int getItemCount() {
        return stokItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvJenisBrg;
        private final TextView tvStokAwal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJenisBrg = itemView.findViewById(R.id.tv_nm_brg);
            tvStokAwal = itemView.findViewById(R.id.tv_stokAwal);
        }
    }
}
