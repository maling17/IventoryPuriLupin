package com.example.iventorypurilupin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iventorypurilupin.Gudang.SuratJalan;
import com.example.iventorypurilupin.Network.ApiServiceGudang;
import com.example.iventorypurilupin.Network.ApiServiceUpdateSj;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.response.response_form_sj.BarangItem;
import com.example.iventorypurilupin.response.response_mitra.Value;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterFormSj extends RecyclerView.Adapter<AdapterFormSj.MyViewHolder> {

    private Context context;
    private List<BarangItem> barangItems;

    public AdapterFormSj(Context context, List<BarangItem> barangItems) {
        this.context = context;
        this.barangItems = barangItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sj_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.etNrmSj.setText(barangItems.get(i).getIdPermintaan());
        myViewHolder.etSplitSj.setText(barangItems.get(i).getSplit());
        myViewHolder.etFlakeSj.setText(barangItems.get(i).getFlake());
        myViewHolder.etTujuan.setText(barangItems.get(i).getTujuan());
        myViewHolder.etTglSj.setText(barangItems.get(i).getTglPermintaan());

        myViewHolder.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.progress.setCancelable(false);
                myViewHolder.progress.setMessage("Loading ...");
                myViewHolder.progress.show();
                TambahSj(myViewHolder);
                update_flake_barang(myViewHolder);
                update_split_barang(myViewHolder);
                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        myViewHolder.progress.cancel();
                        Intent intent=new Intent(context,TampilPermintaan.class);
                        context.startActivity(intent);
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 3000);

            }

        });
    }

    @Override
    public int getItemCount() {
        return barangItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextInputEditText etSplitSj;
        private final TextInputEditText etFlakeSj;
        private final TextInputEditText etTglSj;
        private final EditText etTujuan;
        private final Button btnSimpan;
        private final TextInputEditText etNrmSj;
        private final ProgressDialog progress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            progress = new ProgressDialog(context);
            etNrmSj = itemView.findViewById(R.id.et_nmr_sj);
            etSplitSj = itemView.findViewById(R.id.et_split_sj);
            etFlakeSj = itemView.findViewById(R.id.et_flake_sj);
            etTglSj = itemView.findViewById(R.id.et_tgl_keluar);
            etTujuan = itemView.findViewById(R.id.et_tujuan);
            btnSimpan = itemView.findViewById(R.id.btn_simpan_sj);
        }
    }
    private void update_flake_barang(final MyViewHolder myViewHolder) {
        String flake_sj = myViewHolder.etFlakeSj.getText().toString();

        ApiServiceUpdateSj api = InitRetrofit.getUpdateGudang();
        Call<Value> update_flake_permintaan = api.update_flake_sj(flake_sj);
        update_flake_permintaan.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                myViewHolder.progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void update_split_barang(final MyViewHolder myViewHolder) {
        String split_sj = myViewHolder.etSplitSj.getText().toString();

        ApiServiceUpdateSj apiSplit = InitRetrofit.getUpdateGudang();
        Call<Value> update_split_permintaan = apiSplit.update_split_sj(split_sj);
        update_split_permintaan.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                myViewHolder.progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void TambahSj(final MyViewHolder myViewHolder) {

        String id_sj="";
        String id_permintaan = myViewHolder.etNrmSj.getText().toString();
        String tglsj = myViewHolder.etTglSj.getText().toString();
//        String tujuanSj = spTujuansj.getSelectedItem().toString();
        String split_sj = myViewHolder.etSplitSj.getText().toString();
        String flake_sj = myViewHolder.etFlakeSj.getText().toString();
        String tujuanSj = myViewHolder.etTujuan.getText().toString();
        ApiServiceGudang api = InitRetrofit.getInstanceGudang();

        Call<Value> SjCall = api.tambah_sj(id_sj,tglsj, tujuanSj, id_permintaan, split_sj, flake_sj);
        SjCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    myViewHolder.progress.dismiss();
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
