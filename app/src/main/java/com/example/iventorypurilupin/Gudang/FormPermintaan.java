package com.example.iventorypurilupin.Gudang;

import android.annotation.SuppressLint;
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
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.Value;
import com.example.iventorypurilupin.response_tujuan.MitraItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.iventorypurilupin.R.layout.activity_form_permintaan;

public class FormPermintaan extends AppCompatActivity {

    private TextView judul;
    private TextInputEditText etNmrPermintaan;
    private TextInputEditText etTglPermintaan;
    private Spinner spTujuan;
    private TextInputEditText etSplit;
    private TextInputEditText etFlake;
    private Button btnSimpan;
    private ProgressDialog progress;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_form_permintaan);


        etNmrPermintaan = findViewById(R.id.et_nmr_permintaan);
        etTglPermintaan = findViewById(R.id.et_tgl_permintaan);
        spTujuan = findViewById(R.id.sp_tujuan);
        etSplit = findViewById(R.id.et_split);
        etFlake = findViewById(R.id.et_flake);
        btnSimpan = findViewById(R.id.btn_simpan_permintaan);
        progress = new ProgressDialog(FormPermintaan.this);
        judul = (TextView) findViewById(R.id.tv_judul_event);

        judul.setText("Form Permintaan");

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setCancelable(false);
                progress.setMessage("Loading ...");
                progress.show();
                tambah_permintaan();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);
        TampilTujuan();

    }

    public void tambah_permintaan() {

        String idPermintaan = etNmrPermintaan.getText().toString();
        String TglPermintaan = etTglPermintaan.getText().toString();
        String tujuanPermintaan = spTujuan.getSelectedItem().toString();
        String split_permintaan = etSplit.getText().toString();
        String flake_permintaan = etFlake.getText().toString();

        ApiServiceGudang api = InitRetrofit.getInstanceGudang();

        Call<Value> EntriCall = api.tambah_permintaan(idPermintaan, TglPermintaan, tujuanPermintaan, split_permintaan, flake_permintaan);
        EntriCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(FormPermintaan.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(FormPermintaan.this, message, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void TampilTujuan() {
        final FormPermintaan mContext = this;

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
                    spTujuan.setAdapter(adapter);
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
