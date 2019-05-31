package com.example.iventorypurilupin.Gudang;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceGudang;
import com.example.iventorypurilupin.Network.ApiServiceTujuan;
import com.example.iventorypurilupin.Network.ApiServiceUpdateSj;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.Value;
import com.example.iventorypurilupin.response_tujuan.MitraItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuratJalan extends AppCompatActivity {

    private TextInputEditText etNmrSj;
    private TextInputEditText etTglKlr;
    private TextInputEditText etSplitSj;
    private TextInputEditText etFlakeSj;
    private Spinner spTujuansj;
    private TextView judul;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_jalan);

        etNmrSj = findViewById(R.id.et_nmr_sj);
        etTglKlr = findViewById(R.id.et_tgl_keluar);
        etSplitSj = findViewById(R.id.et_split_sj);
        etFlakeSj = findViewById(R.id.et_flake_sj);
        spTujuansj = findViewById(R.id.sp_tujuan_sj);
        Button btnSimpanSj = findViewById(R.id.btn_simpan_sj);
        judul = (TextView) findViewById(R.id.tv_judul_event);
        progress = new ProgressDialog(this);
        judul.setText("Form Permintaan");

        btnSimpanSj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setCancelable(false);
                progress.setMessage("Loading ...");
                progress.show();
                TambahSj();
                update();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);
        TampilTujuan();
    }

    public void TambahSj() {
        String idSj = etNmrSj.getText().toString();
        String tglKlr = etTglKlr.getText().toString();
        String tujuanSj = spTujuansj.getSelectedItem().toString();
        String split_sj = etSplitSj.getText().toString();
        String flake_sj = etFlakeSj.getText().toString();

        ApiServiceGudang api = InitRetrofit.getInstanceGudang();

        Call<Value> SjCall = api.tambah_sj(idSj, tglKlr, tujuanSj, split_sj, flake_sj);
        SjCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(SuratJalan.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(SuratJalan.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void update_split_barang() {
        String split_sj = etSplitSj.getText().toString();

        ApiServiceUpdateSj apiSplit = InitRetrofit.getUpdateGudang();
        Call<Value> update_split_permintaan = apiSplit.update_split_sj(split_sj);
        update_split_permintaan.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(SuratJalan.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(SuratJalan.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void update_flake_barang() {
        String flake_sj = etFlakeSj.getText().toString();

        ApiServiceUpdateSj api = InitRetrofit.getUpdateGudang();
        Call<Value> update_flake_permintaan = api.update_flake_sj(flake_sj);
        update_flake_permintaan.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(SuratJalan.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(SuratJalan.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void update() {
        update_flake_barang();
        update_split_barang();

    }

    private void TampilTujuan() {
        final SuratJalan mContext = this;

        final ApiServiceTujuan apiServiceTujuan = InitRetrofit.getTujuan();
        Call<com.example.iventorypurilupin.response_tujuan.Response> tampilCall = apiServiceTujuan.getTujuan();
        tampilCall.enqueue(new Callback<com.example.iventorypurilupin.response_tujuan.Response>() {
            @Override
            public void onResponse(Call<com.example.iventorypurilupin.response_tujuan.Response> call, Response<com.example.iventorypurilupin.response_tujuan.Response> response) {
                if (response.isSuccessful()) {
                    List<MitraItem> tujuanItems = response.body().getMitra();
                    List<String> listTujuan = new ArrayList<String>();
                    for (int i = 0; i < tujuanItems.size(); i++) {
                        listTujuan.add(tujuanItems.get(i).getDaerahMitra());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, listTujuan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spTujuansj.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<com.example.iventorypurilupin.response_tujuan.Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
