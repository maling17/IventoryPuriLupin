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
import com.example.iventorypurilupin.response.response_kacang.response_whole.Response_whole;
import com.example.iventorypurilupin.response.response_kacang.response_whole.WholeItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KacangWhole extends AppCompatActivity {

    private TextView judul;
    private RecyclerView rvWhole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacang_whole);

        judul = (TextView) findViewById(R.id.tv_judul_event);
        rvWhole = findViewById(R.id.rv_whole);
        rvWhole.setLayoutManager(new LinearLayoutManager(this));
        rvWhole.setHasFixedSize(true);

        judul.setText("Whole Lupin");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));

        tampilWhole();

    }

    private void tampilWhole() {
        ApiServiceBarang api = InitRetrofit.getKacangBarang();
        Call<Response_whole> wholeCall = api.getBarangWhole();
        wholeCall.enqueue(new Callback<Response_whole>() {
            @Override
            public void onResponse(Call<Response_whole> call, Response<Response_whole> response) {
                Log.d("response api", response.body().toString());
                List<WholeItem> data_split = response.body().getBarang();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterWhole adapter = new AdapterWhole(KacangWhole.this, data_split);
                    rvWhole.setAdapter(adapter);
                } else {
                    Toast.makeText(KacangWhole.this, "Kacang tidak ada", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Response_whole> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
