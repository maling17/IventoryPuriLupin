package com.example.iventorypurilupin.Kacang;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.Network.ApiServiceBarang;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_kacang.response_split.BarangSplitItem;
import com.example.iventorypurilupin.response.response_kacang.response_split.Response_split;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KacangSplit extends AppCompatActivity {

    private TextView judul;
    private RecyclerView rvSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacang_split);

        judul = (TextView) findViewById(R.id.tv_judul_event);

        rvSplit = findViewById(R.id.rv_split);
        rvSplit.setLayoutManager(new LinearLayoutManager(this));
        rvSplit.setHasFixedSize(true);
        judul.setText("Split Lupin");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));


        tampilSplit();
    }

    private void tampilSplit() {
        ApiServiceBarang api = InitRetrofit.getKacangBarang();
        Call<Response_split> wholeCall = api.getBarangSplit();
        wholeCall.enqueue(new Callback<Response_split>() {
            @Override
            public void onResponse(Call<Response_split> call, Response<Response_split> response) {
                Log.d("response api", response.body().toString());
                List<BarangSplitItem> data_split = response.body().getBarang();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterSplit adapter = new AdapterSplit(KacangSplit.this, data_split);
                    rvSplit.setAdapter(adapter);
                } else {
                    Toast.makeText(KacangSplit.this, "Kacang tidak ada", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Response_split> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
