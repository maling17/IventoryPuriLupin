package com.example.iventorypurilupin.Gudang;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServicePenerimaan;
import com.example.iventorypurilupin.Network.ApiServiceUpdatePenerimaan;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntriWhole extends AppCompatActivity {

    private TextInputEditText etIdPenerimaan;
    private TextInputEditText etNoPo;
    private TextInputEditText etTglMsk;
    private TextInputEditText etQtyPenerimaan;
    private ProgressDialog progress;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entri_whole);

        etIdPenerimaan = findViewById(R.id.et_id_penerimaan);
        etNoPo = findViewById(R.id.et_no_po);
        etTglMsk = findViewById(R.id.et_tgl_masuk);
        etQtyPenerimaan = findViewById(R.id.et_qty_penerimaan);
        progress = new ProgressDialog(this);
        Button btnSimpanPenerimaan = findViewById(R.id.btn_simpan_whole);

        judul = (TextView) findViewById(R.id.tv_judul_event);
        judul.setText("Entri Whole");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSimpanPenerimaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambah_penerimaan();
                UpdatePenerimaan();
            }
        });
    }

    private void tambah_penerimaan() {
        String id_pengolahan = etIdPenerimaan.getText().toString();
        String no_po = etNoPo.getText().toString();
        String qty_penerimaan = etQtyPenerimaan.getText().toString();
        String tgl_penerimaan = etTglMsk.getText().toString();

        ApiServicePenerimaan apiServicePenerimaan = InitRetrofit.getTambahPenerimaan();
        Call<Value> tambahCall = apiServicePenerimaan.tambah_penerimaan(id_pengolahan, tgl_penerimaan, no_po, qty_penerimaan);
        tambahCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(EntriWhole.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EntriWhole.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void UpdatePenerimaan() {
        String qty_penerimaan = etQtyPenerimaan.getText().toString();

        ApiServiceUpdatePenerimaan apiServiceUpdatePenerimaan = InitRetrofit.getUpdatePenerimaan();
        Call<Value> updateCall = apiServiceUpdatePenerimaan.update_penerimaan(qty_penerimaan);
        updateCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(EntriWhole.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EntriWhole.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
