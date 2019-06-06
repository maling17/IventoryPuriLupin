package com.example.iventorypurilupin.Mitra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiService;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_mitra.MitraItem;
import com.example.iventorypurilupin.response.response_mitra.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Mitra extends AppCompatActivity {

    private TextView judul;
    private RecyclerView rv_mitra;
    private SwipeRefreshLayout srlMitra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitra);

        srlMitra = findViewById(R.id.srl_mitra);
        judul = (TextView) findViewById(R.id.tv_judul_event);
        rv_mitra = findViewById(R.id.rv_mitra);
        rv_mitra.setHasFixedSize(true);
        rv_mitra.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fabMitra = findViewById(R.id.fab_entri_mitra);
        fabMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mitra.this, EntryMitra.class);
                startActivity(intent);
            }
        });

        srlMitra.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tampilmitra();
                        // Berhenti berputar/refreshing
                        srlMitra.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        judul.setText("Mitra");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);
        tampilmitra();
    }

    private void tampilmitra() {

        ApiService api = InitRetrofit.getInstance();
        Call<Response> mitraCall =
                api.requset_all_mitra();
        mitraCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<MitraItem> data_mitra = response.body().getMitra();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterMitra adapter = new AdapterMitra(Mitra.this, data_mitra);
                        rv_mitra.setAdapter(adapter);
                    } else {
                        Toast.makeText(Mitra.this, "Mitra tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
