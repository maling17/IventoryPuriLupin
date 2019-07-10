package com.example.iventorypurilupin.Home;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_ks_flake.Ks_flakeItem;

import java.util.List;

public class AdapterFlakeKs extends RecyclerView.Adapter<AdapterFlakeKs.MyViewHolder> {
  private Context context;
  private List<Ks_flakeItem> flakeItems;

    public AdapterFlakeKs(Context context, List<Ks_flakeItem> flakeItems) {
        this.context = context;
        this.flakeItems = flakeItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.ks_flake_item,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvFlakeAwal.setText(flakeItems.get(i).getFlakeAwal());
        myViewHolder.tvFlakeOlah.setText(flakeItems.get(i).getFlakeOlah());
        myViewHolder.tvFlakeAkhir.setText(flakeItems.get(i).getFlakeAkhir());

    }

    @Override
    public int getItemCount() {
        return flakeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFlakeAkhir;
        private final TextView tvFlakeOlah;
        private final TextView tvFlakeAwal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFlakeAwal = itemView.findViewById(R.id.tv_flake_awal);
            tvFlakeOlah = itemView.findViewById(R.id.tv_flake_olah);
            tvFlakeAkhir = itemView.findViewById(R.id.tv_flake_akhir);
        }
    }
}
