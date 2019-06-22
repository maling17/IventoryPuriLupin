package com.example.iventorypurilupin.Home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServicePermintaan;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_permintaan.AntrianItem;
import com.example.iventorypurilupin.response.response_permintaan.ResponsePermintaan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AntrianBarang extends AppCompatActivity {

    private TextView judul;
    private RecyclerView rvAntrian;
    private SwipeRefreshLayout srlAntrian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian_barang);
        judul = (TextView) findViewById(R.id.tv_judul_event);
        rvAntrian = findViewById(R.id.rv_antrian);
        rvAntrian.setLayoutManager(new LinearLayoutManager(this));
        srlAntrian = findViewById(R.id.srl_antrian);

        judul.setText("Antrian");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TampilAntrian();

        srlAntrian.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TampilAntrian();
                        srlAntrian.setRefreshing(false);
                    }
                }, 5000);
            }
        });

    }

    private void TampilAntrian() {
        ApiServicePermintaan apiServicePermintaan = InitRetrofit.getPermintaan();
        Call<ResponsePermintaan> Call = apiServicePermintaan.request_all_permintaan();
        Call.enqueue(new Callback<ResponsePermintaan>() {
            @Override
            public void onResponse(retrofit2.Call<ResponsePermintaan> call, Response<ResponsePermintaan> response) {
                Log.d("response api", response.body().toString());
                List<AntrianItem> data_antrian = response.body().getBarang();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterAntrian adapter = new AdapterAntrian(AntrianBarang.this, data_antrian);
                    rvAntrian.setAdapter(adapter);
                } else {
                    Toast.makeText(AntrianBarang.this, "Mitra tidak ada", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponsePermintaan> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}
