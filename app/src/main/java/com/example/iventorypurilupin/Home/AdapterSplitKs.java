package com.example.iventorypurilupin.Home;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_ks_split.Ks_SplitItem;

import java.util.List;

public class AdapterSplitKs extends RecyclerView.Adapter<AdapterSplitKs.MyViewHolder> {

    private Context context;
    private List<Ks_SplitItem> splitItems;

    public AdapterSplitKs(Context context, List<Ks_SplitItem> splitItems) {
        this.context = context;
        this.splitItems = splitItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.ks_split_item,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvSplitAwal.setText(splitItems.get(i).getSplitAwal());
        myViewHolder.tvSplitOlah.setText(splitItems.get(i).getSOlah());
        myViewHolder.tvSplitAkhir.setText(splitItems.get(i).getSplitAkhir());

    }

    @Override
    public int getItemCount() {
        return splitItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvSplitAwal;
        private final TextView tvSplitOlah;
        private final TextView tvSplitAkhir;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSplitAwal = itemView.findViewById(R.id.tv_split_awal);
            tvSplitOlah = itemView.findViewById(R.id.tv_split_olah);
            tvSplitAkhir = itemView.findViewById(R.id.tv_split_akhir);

        }
    }
}
