package com.example.iventorypurilupin.Gudang;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceDetailPengolahanFlake;
import com.example.iventorypurilupin.Network.ApiServiceDetailPengolahanSplit;
import com.example.iventorypurilupin.Network.ApiServiceDetailPengolahanWhole;
import com.example.iventorypurilupin.Network.ApiServiceOlah;
import com.example.iventorypurilupin.Network.ApiServiceStokAwal;
import com.example.iventorypurilupin.Network.ApiServiceUpdateOlah;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_mitra.Value;
import com.example.iventorypurilupin.response.response_stok_awal.Response_stok_awal;
import com.example.iventorypurilupin.response.response_stok_awal.Stok_awal_Item;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntriOlahWhole extends AppCompatActivity {

    private TextInputEditText etIdPengolahan;


    private ProgressDialog progress;
    private TextView judul;
    private TextInputEditText etSplit;
    private TextInputEditText etFlake;
    private TextInputEditText etWhole;
    private EditText etTglPengolahan;
    private RecyclerView rvStok;
    private TextInputEditText etWholeAwal;
    private TextInputEditText etSplitAwal;
    private TextInputEditText etFlakeAwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entri_olah_whole);

        etIdPengolahan = findViewById(R.id.et_id_pengolahan);
        etTglPengolahan = findViewById(R.id.et_tanggal_olah);
        etSplit = findViewById(R.id.et_split);
        etFlake = findViewById(R.id.et_flake);
        etWhole = findViewById(R.id.etWhole);

        etWholeAwal = findViewById(R.id.etWhole_awal);
        etSplitAwal = findViewById(R.id.et_split_awal);
        etFlakeAwal = findViewById(R.id.et_flake_awal);
        progress = new ProgressDialog(this);
        rvStok = findViewById(R.id.rv_stok);
        rvStok.setLayoutManager(new LinearLayoutManager(this));
        rvStok.setHasFixedSize(true);

        Button btnSimpan = findViewById(R.id.btn_simpan_olah);
        Button btnTgl = findViewById(R.id.btn_tanggal);
        judul = (TextView) findViewById(R.id.tv_judul_event);
        judul.setText("Entri Whole");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tampilStokAwal();
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setCancelable(false);
                progress.setMessage("Loading ...");
                progress.show();
                TambahOlah();
                tambahDetailWhole();
                tambahDetailSplit();
                tambahDetailFlake();
                UpdateOlah();
            }
        });
        btnTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(EntriOlahWhole.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                        etTglPengolahan.setText(dateFormater.format(date.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });
    }

    private void TambahOlah() {

        String id_pengolahan = etIdPengolahan.getText().toString();
        String tgl_pengolahan = etTglPengolahan.getText().toString();
        String w_awal=etWholeAwal.getText().toString();
        String s_awal=etSplitAwal.getText().toString();
        String f_awal=etFlakeAwal.getText().toString();
        ApiServiceOlah apiServiceOlah = InitRetrofit.getTambahOlah();
        Call<Value> olahCall = apiServiceOlah.tambah_pengolahan(id_pengolahan, tgl_pengolahan,w_awal,s_awal,f_awal);
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

        String whole = etWhole.getText().toString();
        String split = etSplit.getText().toString();
        String flake = etFlake.getText().toString();

        ApiServiceUpdateOlah apiServiceUpdateOlah = InitRetrofit.getUpdateolah();
        Call<Value> updateCall = apiServiceUpdateOlah.update_whole(whole);
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

        ApiServiceUpdateOlah api = InitRetrofit.getUpdateolah();
        Call<Value> updateSplit = api.update_split(split);
        updateSplit.enqueue(new Callback<Value>() {
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
        ApiServiceUpdateOlah api2 = InitRetrofit.getUpdateolah();
        Call<Value> updateFlake = api2.update_flake(flake);
        updateFlake.enqueue(new Callback<Value>() {
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

    private void tambahDetailWhole() {

        String id_pengolahan = etIdPengolahan.getText().toString();
        int id_brg = 1;
        String whole = etWhole.getText().toString();
        String tgl_pengolahan=etTglPengolahan.getText().toString();

        ApiServiceDetailPengolahanWhole api = InitRetrofit.getDetailPengolahanWhole();
        Call<Value> WholeDetail = api.getWhole(id_brg, id_pengolahan, whole,tgl_pengolahan);
        WholeDetail.enqueue(new Callback<Value>() {
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
    private void tambahDetailSplit() {

        String id_pengolahan = etIdPengolahan.getText().toString();
        int id_brg = 2;
        String split = etSplit.getText().toString();
        String tgl_pengolahan=etTglPengolahan.getText().toString();

        ApiServiceDetailPengolahanSplit api = InitRetrofit.getDetailPengolahanSplit();
        Call<Value> SplitDetail = api.getSplit(id_brg,id_pengolahan,split,tgl_pengolahan);
        SplitDetail.enqueue(new Callback<Value>() {
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
    private void tambahDetailFlake() {

        String id_pengolahan = etIdPengolahan.getText().toString();
        int id_brg = 3;
        String flake = etFlake.getText().toString();
        String tgl_pengolahan=etTglPengolahan.getText().toString();
        ApiServiceDetailPengolahanFlake api = InitRetrofit.getDetailPengolahanFlake();
        Call<Value> FlakeDetail = api.getFlake(id_brg, id_pengolahan, flake,tgl_pengolahan);
        FlakeDetail.enqueue(new Callback<Value>() {
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

    private void tampilStokAwal(){
        ApiServiceStokAwal api=InitRetrofit.getStokAwal();
        Call<Response_stok_awal> stokawal=api.stok_awal();
        stokawal.enqueue(new Callback<Response_stok_awal>() {
            @Override
            public void onResponse(Call<Response_stok_awal> call, Response<Response_stok_awal> response) {
                Log.d("response api", response.body().toString());
                List<Stok_awal_Item> data_sj = response.body().getBarang();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterStokAwal adapter = new AdapterStokAwal(EntriOlahWhole.this, data_sj);
                    rvStok.setAdapter(adapter);
                } else {
                    Toast.makeText(EntriOlahWhole.this, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Response_stok_awal> call, Throwable t) {

            }
        });
    }
}
