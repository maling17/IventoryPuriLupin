package com.example.iventorypurilupin.Home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.iventorypurilupin.Network.ApiServiceAntrian;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_antrian.AntrianItem;
import com.example.iventorypurilupin.response.response_antrian.Response_antrian;

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


        judul.setText("Antrian");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TampilAntrian();

      /*  srlAntrian.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        });*/

    }

    private void TampilAntrian() {

        ApiServiceAntrian api = InitRetrofit.getAntrian();
        Call<Response_antrian> antrianCall = api.getAntrian();
        antrianCall.enqueue(new Callback<Response_antrian>() {
            @Override
            public void onResponse(Call<Response_antrian> call, Response<Response_antrian> response) {
                Log.d("response api", response.body().toString());
                List<AntrianItem> data_antrian = response.body().getLaporan();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterAntrian adapter = new AdapterAntrian(AntrianBarang.this, data_antrian);
                    rvAntrian.setAdapter(adapter);
                } else {
                    Toast.makeText(AntrianBarang.this, "Antrian tidak ada", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Response_antrian> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}
