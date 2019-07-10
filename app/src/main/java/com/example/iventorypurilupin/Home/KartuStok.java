package com.example.iventorypurilupin.Home;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceKSFlake;
import com.example.iventorypurilupin.Network.ApiServiceKSSplit;
import com.example.iventorypurilupin.Network.ApiServiceKSWhole;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_ks_flake.Ks_flakeItem;
import com.example.iventorypurilupin.response.response_ks_flake.Response_ks_flake;
import com.example.iventorypurilupin.response.response_ks_split.Ks_SplitItem;
import com.example.iventorypurilupin.response.response_ks_split.Response_ks_split;
import com.example.iventorypurilupin.response.response_ks_whole.Ks_WholeItem;
import com.example.iventorypurilupin.response.response_ks_whole.Response_ks_whole;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KartuStok extends AppCompatActivity {

    private TextView judul;
    private RecyclerView rvKsWhole;
    private RecyclerView rvKsSplit;
    private RecyclerView rvKsFlake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_stok);
        rvKsWhole = findViewById(R.id.rv_whole_ks);
        rvKsWhole.setLayoutManager(new LinearLayoutManager(this));
        rvKsWhole.setHasFixedSize(true);

        rvKsSplit = findViewById(R.id.rv_split_ks);
        rvKsSplit.setLayoutManager(new LinearLayoutManager(this));
        rvKsSplit.setHasFixedSize(true);

        rvKsFlake = findViewById(R.id.rv_flake_ks);
        rvKsFlake.setLayoutManager(new LinearLayoutManager(this));
        rvKsFlake.setHasFixedSize(true);
        judul = (TextView) findViewById(R.id.tv_judul_event);


        judul.setText("Kartu Stok");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tampilKsWhole();
        tampilKsSplit();
        tampilKsFlake();
    }

    private void tampilKsWhole() {
        ApiServiceKSWhole api = InitRetrofit.getKSWhole();
        Call<Response_ks_whole> ksCall = api.ksWhole();
        ksCall.enqueue(new Callback<Response_ks_whole>() {
            @Override
            public void onResponse(Call<Response_ks_whole> call, Response<Response_ks_whole> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<Ks_WholeItem> data_split = response.body().getBarang();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterKsWhole adapter = new AdapterKsWhole(KartuStok.this, data_split);
                        rvKsWhole.setAdapter(adapter);
                    } else {
                        Toast.makeText(KartuStok.this, "Stok tidak ada", Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<Response_ks_whole> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void tampilKsSplit() {
        ApiServiceKSSplit api = InitRetrofit.getKSSplit();
        Call<Response_ks_split> ksCall = api.splitKs();
        ksCall.enqueue(new Callback<Response_ks_split>() {
            @Override
            public void onResponse(Call<Response_ks_split> call, Response<Response_ks_split> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<Ks_SplitItem> data_split = response.body().getBarang();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterSplitKs adapter = new AdapterSplitKs(KartuStok.this, data_split);
                        rvKsSplit.setAdapter(adapter);
                    } else {
                        Toast.makeText(KartuStok.this, "Stok tidak ada", Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<Response_ks_split> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void tampilKsFlake() {
        ApiServiceKSFlake api = InitRetrofit.getKsFlake();
        Call<Response_ks_flake> ksCall = api.flakeks();
        ksCall.enqueue(new Callback<Response_ks_flake>() {
            @Override
            public void onResponse(Call<Response_ks_flake> call, Response<Response_ks_flake> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<Ks_flakeItem> data_split = response.body().getBarang();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterFlakeKs adapter = new AdapterFlakeKs(KartuStok.this, data_split);
                        rvKsFlake.setAdapter(adapter);
                    } else {
                        Toast.makeText(KartuStok.this, "Stok tidak ada", Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<Response_ks_flake> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

