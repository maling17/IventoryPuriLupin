package com.example.iventorypurilupin.Home;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceAntrian;
import com.example.iventorypurilupin.Network.ApiServicePermintaan;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_antrian.AntrianItem;
import com.example.iventorypurilupin.response.response_antrian.Response_antrian;
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
        Holder.tvTlp.setText(permintaan.get(i).getTlp_mitra());
        final String tlp=Holder.tvTlp.getText().toString();
        final String split=Holder.tvQtySplit.getText().toString();
        final String flake=Holder.tvQtyFlake.getText().toString();
        Holder.btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Barang dikirim  " , Toast.LENGTH_LONG).show();
                String Pesan = "Barang sudah dikirim Silahkan ditunggu dengan Split dan Flake";
                String pesan=Pesan+split+flake;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, pesan);
                intent.putExtra("jid", tlp + "@s.whatsapp.net");
                intent.setPackage("com.whatsapp");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return permintaan.size();
    }


    private void hapusAntrian( final MyViewHolder myViewHolder){
        ApiServiceAntrian api = InitRetrofit.getAntrian();
        Call<Response_antrian> antrianCall = api.getAntrian2();
        antrianCall.enqueue(new Callback<Response_antrian>() {
            @Override
            public void onResponse(Call<Response_antrian> call, Response<Response_antrian> response) {
                Log.d("response api", response.body().toString());
                List<AntrianItem> data_antrian = response.body().getLaporan();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterAntrian adapter = new AdapterAntrian(context, data_antrian);
                    myViewHolder.rvAntrian.setAdapter(adapter);
                } else {
                    Toast.makeText(context, "Antrian tidak ada", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Response_antrian> call, Throwable t) {
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
        private final RecyclerView rvAntrian;
        private final TextView tvTlp;

        public MyViewHolder(@NonNull View View) {
            super(View);
            tvQtySplit = View.findViewById(R.id.tv_qty_split);
            tvQtyFlake = View.findViewById(R.id.tv_qty_flake);
            tvTujuanAntrian = View.findViewById(R.id.tv_tujuan_antrian);
            btnKirim = View.findViewById(R.id.btn_kirim_antrian);
            tvId = View.findViewById(R.id.tv_id);
            tvTlp = View.findViewById(R.id.tv_no_tlp_mitra);

            rvAntrian = View.findViewById(R.id.rv_antrian);

        }
    }

}
