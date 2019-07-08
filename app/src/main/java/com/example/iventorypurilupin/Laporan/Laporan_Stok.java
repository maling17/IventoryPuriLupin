package com.example.iventorypurilupin.Laporan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceLaporan;
import com.example.iventorypurilupin.Network.ApiServiceTahun;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_lap_stok.LaporanStokItem;
import com.example.iventorypurilupin.response.response_lap_stok.Response_lap_stok;
import com.example.iventorypurilupin.response.response_tahun.Response_tahun;
import com.example.iventorypurilupin.response.response_tahun.TahunItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Laporan_Stok extends AppCompatActivity {

    private Spinner spBulan;
    private Spinner spTahun;
    private RecyclerView rvStok;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan__stok);

        spBulan = findViewById(R.id.sp_bulan_stok);
        spTahun = findViewById(R.id.sp_tahun_stok);
        rvStok = findViewById(R.id.rv_lap_stok);
        rvStok.setLayoutManager(new LinearLayoutManager(this));
        rvStok.setHasFixedSize(true);
        judul = (TextView) findViewById(R.id.tv_judul_event);


        judul.setText("Laporan Stok");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btnCari = findViewById(R.id.btn_cari);

        final Laporan_Stok context = this;

        ApiServiceTahun api = InitRetrofit.getTahun();
        Call<Response_tahun> tahunCall = api.getTahunOlah();
        tahunCall.enqueue(new Callback<Response_tahun>() {
            @Override
            public void onResponse(Call<Response_tahun> call, Response<Response_tahun> response) {

                if (response.isSuccessful()) {
                    final List<TahunItem> tahunItems = response.body().getTahun();
                    List<String> listTujuan = new ArrayList<String>();
                    for (int i = 0; i < tahunItems.size(); i++) {
                        listTujuan.add(tahunItems.get(i).getTahun());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listTujuan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spTahun.setAdapter(adapter);

                    spTahun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String tahun = tahunItems.get(position).getTahun();


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    Toast.makeText(context, "Gagal mengambil data tujuan", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Response_tahun> call, Throwable t) {
                t.printStackTrace();
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CariStok();
            }
        });
    }

    private void CariStok() {
        String bulan = spBulan.getSelectedItem().toString();
        String tahun = spTahun.getSelectedItem().toString();
        ApiServiceLaporan api = InitRetrofit.getLaporan();
        Call<Response_lap_stok> lapCall = api.getLaporanStok(bulan, tahun);
        lapCall.enqueue(new Callback<Response_lap_stok>() {
            @Override
            public void onResponse(Call<Response_lap_stok> call, Response<Response_lap_stok> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<LaporanStokItem> data_split = response.body().getLaporan();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterLaporanStok adapter = new AdapterLaporanStok(Laporan_Stok.this, data_split);
                        rvStok.setAdapter(adapter);
                    } else {
                        Toast.makeText(Laporan_Stok.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<Response_lap_stok> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }
}
