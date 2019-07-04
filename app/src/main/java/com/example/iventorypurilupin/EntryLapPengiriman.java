package com.example.iventorypurilupin;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceSj;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.response.response_sj.LaporanItem;
import com.example.iventorypurilupin.response.response_sj.Response_sj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryLapPengiriman extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private RecyclerView rvLp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_lap_pengiriman);
        rvLp = findViewById(R.id.rv_lp);
        rvLp.setLayoutManager(new LinearLayoutManager(this));
        rvLp.setHasFixedSize(true);
        TampilSj();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Cari Nama Mahasiswa");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @SuppressWarnings("deprecation")
    private void TampilSj() {
        ApiServiceSj apiServiceSj = InitRetrofit.getSj();
        Call<Response_sj> getLaporan = apiServiceSj.getSj();
        getLaporan.enqueue(new Callback<Response_sj>() {
            @Override
            public void onResponse(Call<Response_sj> call, Response<Response_sj> response) {
                Log.d("response api", response.body().toString());
                List<LaporanItem> data_laporan = response.body().getLaporan();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterLp adapter = new AdapterLp(EntryLapPengiriman.this, data_laporan);
                    rvLp.setAdapter(adapter);
                } else {
                    Toast.makeText(EntryLapPengiriman.this, "Mitra tidak ada", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Response_sj> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}