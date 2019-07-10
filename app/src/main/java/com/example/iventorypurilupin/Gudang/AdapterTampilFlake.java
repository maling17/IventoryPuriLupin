package com.example.iventorypurilupin.Gudang;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang.FlakeItem;

import java.util.List;

public class AdapterTampilFlake extends RecyclerView.Adapter<AdapterTampilFlake.MyViewHolder> {

    private Context context;
    private List<FlakeItem> flakeItems;

    public AdapterTampilFlake(Context context, List<FlakeItem> flakeItems) {
        this.context = context;
        this.flakeItems = flakeItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.flake_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvFlake.setText(flakeItems.get(i).getFlake());
    }

    @Override
    public int getItemCount() {
        return flakeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFlake;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFlake = itemView.findViewById(R.id.tv_flake_permintaan);

        }
    }
}
