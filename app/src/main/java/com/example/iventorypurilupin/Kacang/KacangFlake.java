package com.example.iventorypurilupin.Kacang;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceBarang;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_kacang.response_flake.BarangFlakeItem;
import com.example.iventorypurilupin.response.response_kacang.response_flake.Response_barang_flake;
import com.example.iventorypurilupin.response.response_kacang.response_split.BarangSplitItem;
import com.example.iventorypurilupin.response.response_kacang.response_split.Response_split;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KacangFlake extends AppCompatActivity {

    private TextView judul;
    private RecyclerView rvFlake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacang_flake);

        judul = (TextView) findViewById(R.id.tv_judul_event);

        rvFlake = findViewById(R.id.rv_flake);
        rvFlake.setHasFixedSize(true);
        rvFlake.setLayoutManager(new LinearLayoutManager(this));
        judul.setText("Flake Lupin");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tampilFlake();
    }
    private void tampilFlake() {
        ApiServiceBarang api = InitRetrofit.getKacangBarang();
        Call<Response_barang_flake> wholeCall = api.getBarangFlake();
        wholeCall.enqueue(new Callback<Response_barang_flake>() {
            @Override
            public void onResponse(Call<Response_barang_flake> call, Response<Response_barang_flake> response) {
                Log.d("response api", response.body().toString());
                List<BarangFlakeItem> data_split = response.body().getBarang();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterFlake adapter = new AdapterFlake(KacangFlake.this, data_split);
                    rvFlake.setAdapter(adapter);
                } else {
                    Toast.makeText(KacangFlake.this, "Kacang tidak ada", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Response_barang_flake> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
