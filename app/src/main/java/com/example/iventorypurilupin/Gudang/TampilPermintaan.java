package com.example.iventorypurilupin.Gudang;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServicesTampilSj;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang.Response_permintaan_barang;
import com.example.iventorypurilupin.response.response_permintaan.response_permintaan_barang.SplitItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilPermintaan extends AppCompatActivity {

    private RecyclerView rvTampilPermintaan;
    private SwipeRefreshLayout srlPermintaan;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_permintaan);

        rvTampilPermintaan = findViewById(R.id.rv_tampil_permintaan);
        srlPermintaan = findViewById(R.id.srl_permitaan);
        rvTampilPermintaan.setLayoutManager(new LinearLayoutManager(this));
        rvTampilPermintaan.setHasFixedSize(true);

        judul = (TextView) findViewById(R.id.tv_judul_event);
        judul.setText("Entri Whole");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tampilSj();



    }

    private void tampilSj() {
        ApiServicesTampilSj api = InitRetrofit.getTampilSj();
        Call<Response_permintaan_barang> SjCall = api.requestSplitPermintaan();
        SjCall.enqueue(new Callback<Response_permintaan_barang>() {
            @Override
            public void onResponse(Call<Response_permintaan_barang> call, Response<Response_permintaan_barang> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<SplitItem> data_split = response.body().getPermintaan();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterTampilPermintaan adapter = new AdapterTampilPermintaan(TampilPermintaan.this, data_split);
                        rvTampilPermintaan.setAdapter(adapter);
                    } else {
                        Toast.makeText(TampilPermintaan.this, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<Response_permintaan_barang> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
  /*  private void tampilflake(){
        ApiServicesTampilSj apiflake = InitRetrofit.getTampilSj();
        Call<Response_permintaan_flake> flakeCall = apiflake.requestFlakePermintaan();
        flakeCall.enqueue(new Callback<Response_permintaan_flake>() {
            @Override
            public void onResponse(Call<Response_permintaan_flake> call, Response_form_sj<Response_permintaan_flake> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<FlakeItem> data_flake = response.body().getPermintaan();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterTampilFlake adapter = new AdapterTampilFlake(TampilPermintaan.this, data_flake);
                        rvTampilPermintaan.setAdapter(adapter);
                    } else {
                        Toast.makeText(TampilPermintaan.this, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Response_permintaan_flake> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }*/
}