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

import com.example.iventorypurilupin.Network.ApiServiceOlah;
import com.example.iventorypurilupin.Network.ApiServiceUpdateOlah;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntriOlahWhole extends AppCompatActivity {

    private TextInputEditText etIdPengolahan;
    private TextInputEditText etTglPengolahan;
    private TextInputEditText etQtyOlah;
    private ProgressDialog progress;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entri_olah_whole);

        etIdPengolahan = findViewById(R.id.et_id_pengolahan);
        etTglPengolahan = findViewById(R.id.et_tgl_olah);
        etQtyOlah = findViewById(R.id.et_qty_olah);
        progress = new ProgressDialog(this);
        Button btnSimpan = findViewById(R.id.btn_simpan_olah);

        judul = (TextView) findViewById(R.id.tv_judul_event);
        judul.setText("Entri Whole");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setCancelable(false);
                progress.setMessage("Loading ...");
                progress.show();
                TambahOlah();
                UpdateOlah();
            }
        });

    }

    private void TambahOlah() {

        String id_pengolahan = etIdPengolahan.getText().toString();
        String tgl_pengolahan = etTglPengolahan.getText().toString();
        String qty_olah = etQtyOlah.getText().toString();

        ApiServiceOlah apiServiceOlah = InitRetrofit.getTambahOlah();
        Call<Value> olahCall = apiServiceOlah.tambah_pengolahan(id_pengolahan, tgl_pengolahan, qty_olah);
        olahCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(EntriOlahWhole.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EntriOlahWhole.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void UpdateOlah() {
        String qty_olah = etQtyOlah.getText().toString();

        ApiServiceUpdateOlah apiServiceUpdateOlah = InitRetrofit.getUpdateolah();
        Call<Value> updateCall = apiServiceUpdateOlah.update_whole_olah(qty_olah);
        updateCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(EntriOlahWhole.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EntriOlahWhole.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
