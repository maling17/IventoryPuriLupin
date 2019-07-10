package com.example.iventorypurilupin.Kacang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_kacang.response_whole.WholeItem;

import java.util.List;

public class AdapterWhole extends RecyclerView.Adapter<AdapterWhole.MyViewHolder> {
   private Context context;
   private List<WholeItem>wholeItems;

    public AdapterWhole(Context context, List<WholeItem> wholeItems) {
        this.context = context;
        this.wholeItems = wholeItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.barang_whole_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvStok.setText(wholeItems.get(position).getStok());

    }

    @Override
    public int getItemCount() {
        return wholeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvStok;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStok = itemView.findViewById(R.id.tv_stok_whole);
        }
    }
}
