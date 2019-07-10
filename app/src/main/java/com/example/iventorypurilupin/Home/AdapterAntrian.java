package com.example.iventorypurilupin.Home;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServicePermintaan;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_antrian.AntrianItem;
import com.example.iventorypurilupin.response.response_mitra.Value;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAntrian extends RecyclerView.Adapter<AdapterAntrian.MyViewHolder> {
    private Context context;
    //  aslinya permintaaanItem
    private List<AntrianItem> permintaan;

    public AdapterAntrian(Context context, List<AntrianItem> permintaan) {
        this.context = context;
        this.permintaan = permintaan;
    }

    @NonNull
    @Override

    public AdapterAntrian.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.antrian_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterAntrian.MyViewHolder Holder, int i) {
       Holder.tvId.setText(permintaan.get(i).getIdSj());
        Holder.tvTujuanAntrian.setText(permintaan.get(i).getTujuan());
        Holder.tvQtySplit.setText(permintaan.get(i).getSplitSj());
        Holder.tvQtyFlake.setText(permintaan.get(i).getFlakeSj());
        Holder.btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Barang dikirim  " + Holder.tvId.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return permintaan.size();
    }

    private void hapus_permintaan(AdapterAntrian.MyViewHolder holder) {
        String id_permintaan = holder.tvId.getText().toString();
        ApiServicePermintaan apiServicePermintaan = InitRetrofit.getPermintaan();
        Call<Value> call = apiServicePermintaan.hapus(id_permintaan);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = null;
                if (response.body() != null) {
                    value = response.body().getValue();
                }
                String message = null;
                if (response.body() != null) {
                    message = response.body().getMessage();
                }
                if (value != null) {
                    if (value.equals("1")) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvQtySplit;
        private final TextView tvQtyFlake;
        private final TextView tvTujuanAntrian;
        private final TextView tvId;
        private final Button btnKirim;

        public MyViewHolder(@NonNull View View) {
            super(View);
            tvQtySplit = View.findViewById(R.id.tv_qty_split);
            tvQtyFlake = View.findViewById(R.id.tv_qty_flake);
            tvTujuanAntrian = View.findViewById(R.id.tv_tujuan_antrian);
            btnKirim = View.findViewById(R.id.btn_kirim_antrian);
            tvId = View.findViewById(R.id.tv_id);

        }
    }
}
