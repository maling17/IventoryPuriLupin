package com.example.iventorypurilupin.Gudang;

import android.app.ProgressDialog;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceFormSj;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_form_sj.BarangItem;
import com.example.iventorypurilupin.response.response_form_sj.Response_form_sj;

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
    private EditText etTujuan;
    private RecyclerView rvSj;
    private RecyclerView rvStok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_jalan);

        etNmrSj = findViewById(R.id.et_nmr_sj);

        etTglKlr = findViewById(R.id.et_tgl_keluar);
        etSplitSj = findViewById(R.id.et_split_sj);
        etFlakeSj = findViewById(R.id.et_flake_sj);
//        spTujuansj = findViewById(R.id.sp_tujuan_sj);
        Button btnSimpanSj = findViewById(R.id.btn_simpan_sj);
        etTujuan = findViewById(R.id.et_tujuan);
        judul = (TextView) findViewById(R.id.tv_judul_event);
        rvSj = findViewById(R.id.rv_sj);
        rvSj.setLayoutManager(new LinearLayoutManager(this));
        rvSj.setHasFixedSize(true);
        progress = new ProgressDialog(this);
        judul.setText("Surat Jalan");


//        btnSimpanSj.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progress.setCancelable(false);
//                progress.setMessage("Loading ...");
//                progress.show();
//                TambahSj();
////                update();
//            }
//        });

        String idsj = getIntent().getStringExtra("id_permintaan");
        String tglsj = getIntent().getStringExtra("tgl_permintaan");
        String tujuan = getIntent().getStringExtra("tujuan");

        etNmrSj.setText(idsj);
//        etTglKlr.setText(tglsj);
//        etTujuan.setText(tujuan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
//        TampilTujuan();
        TampilSj();


    }


    private void TampilSj() {

        String id_permintaan = etNmrSj.getText().toString();
        ApiServiceFormSj api = InitRetrofit.getFormSj();
        Call<Response_form_sj> sj = api.getFormSj(id_permintaan);
        sj.enqueue(new Callback<Response_form_sj>() {
            @Override
            public void onResponse(Call<Response_form_sj> call, Response<Response_form_sj> response) {
                Log.d("response api", response.body().toString());
                List<BarangItem> data_sj = response.body().getBarang();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterFormSj adapter = new AdapterFormSj(SuratJalan.this, data_sj);
                    rvSj.setAdapter(adapter);
                } else {
                    Toast.makeText(SuratJalan.this, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Response_form_sj> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

//    public void TambahSj() {
//        String id_sj = "";
//        String id_permintaan = etNmrSj.getText().toString();
//        String tglsj = etTglKlr.getText().toString();
////        String tujuanSj = spTujuansj.getSelectedItem().toString();
//        String split_sj = etSplitSj.getText().toString();
//        String flake_sj = etFlakeSj.getText().toString();
//        String tujuanSj = etTujuan.getText().toString();
//        ApiServiceGudang api = InitRetrofit.getInstanceGudang();
//
//        Call<Value> SjCall = api.tambah_sj(id_sj, tglsj, tujuanSj, id_permintaan, split_sj, flake_sj);
//        SjCall.enqueue(new Callback<Value>() {
//            @Override
//            public void onResponse(Call<Value> call, Response_split<Value> response) {
//                assert response.body() != null;
//                String value = response.body().getValue();
//                String message = response.body().getMessage();
//                progress.dismiss();
//                if (value.equals("1")) {
//                    Toast.makeText(SuratJalan.this, message, Toast.LENGTH_LONG).show();
//                    finish();
//                } else {
//                    Toast.makeText(SuratJalan.this, message, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Value> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }



}
//
//    private void update() {
//        update_flake_barang();
//        update_split_barang();
//
//    }



