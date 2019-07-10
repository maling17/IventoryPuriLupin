package com.example.iventorypurilupin.Gudang;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.AdapterIdPengolahan;
import com.example.iventorypurilupin.Network.ApiServiceId;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_id_pengolahan.IdPengolahanItem;
import com.example.iventorypurilupin.response.response_id_pengolahan.Response_id_pengolahan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntriOlahWhole extends AppCompatActivity {

    private TextInputEditText etIdPengolahan;
    private ProgressDialog progress;
    private TextView judul;
    private RecyclerView rvPengolahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entri_olah_whole);

        rvPengolahan = findViewById(R.id.rv_pengolahan);
        rvPengolahan.setLayoutManager(new LinearLayoutManager(this));
        rvPengolahan.setHasFixedSize(true);
        judul = (TextView) findViewById(R.id.tv_judul_event);
        judul.setText("Entri Olah");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TampilPengolahan();

    }

    private void TampilPengolahan() {
        ApiServiceId api = InitRetrofit.getId2();
        Call<Response_id_pengolahan> pengolahanCall = api.idPengolahan();
        pengolahanCall.enqueue(new Callback<Response_id_pengolahan>() {
            @Override
            public void onResponse(Call<Response_id_pengolahan> call, Response<Response_id_pengolahan> response) {
                Log.d("response api", response.body().toString());
                List<IdPengolahanItem> data_split = response.body().getPengolahan();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterIdPengolahan adapter = new AdapterIdPengolahan(EntriOlahWhole.this, data_split);
                    rvPengolahan.setAdapter(adapter);
                } else {
                    Toast.makeText(EntriOlahWhole.this, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Response_id_pengolahan> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}

