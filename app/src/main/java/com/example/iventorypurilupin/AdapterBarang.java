package com.example.iventorypurilupin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.response_barang.BarangItem;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.MyViewHolder> {
    private Context context;
    private List<BarangItem> barang;

    public AdapterBarang(Context context, List<BarangItem> barang) {
        this.context = context;
        this.barang = barang;

    }

    @NonNull
    @Override
    public AdapterBarang.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(context).inflate(R.layout.barang_item,viewGroup,false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBarang.MyViewHolder Holder, int i) {

        Holder.tvProduk.setText(barang.get(i).getJenisBrg());
        Holder.tvQty.setText(barang.get(i).getStok());
    }

    @Override
    public int getItemCount() {
        return barang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvProduk;
        private final TextView tvQty;

        public MyViewHolder(@NonNull View View) {
            super(View);

            tvProduk = View.findViewById(R.id.tv_produk);
            tvQty = View.findViewById(R.id.tv_qty);
        }
    }
}
