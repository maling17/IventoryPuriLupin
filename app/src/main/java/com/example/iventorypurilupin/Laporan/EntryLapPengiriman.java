package com.example.iventorypurilupin.Laporan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
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
import com.example.iventorypurilupin.response.response_laporan_pengiriman.LaporanItem;
import com.example.iventorypurilupin.response.response_laporan_pengiriman.Response_lap_pengiriman;
import com.example.iventorypurilupin.response.response_tahun.Response_tahun;
import com.example.iventorypurilupin.response.response_tahun.TahunItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryLapPengiriman extends AppCompatActivity {


    private RecyclerView rvLp;
    private Spinner spBulan;
    private Spinner spTahun;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_lap_pengiriman);
        rvLp = findViewById(R.id.rv_lp);
        rvLp.setLayoutManager(new LinearLayoutManager(this));
        rvLp.setHasFixedSize(true);
        judul = (TextView) findViewById(R.id.tv_judul_event);


        judul.setText("Laporan Pengiriman");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spBulan = findViewById(R.id.sp_bulan);
        spTahun = findViewById(R.id.sp_tahun);
        Button btnCari = findViewById(R.id.btn_cari);


        final EntryLapPengiriman context = this;

        ApiServiceTahun api = InitRetrofit.getTahun();
        Call<Response_tahun> tahunCall = api.getTahun();
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
                CariLaporan();
            }
        });
    }

    private void CariLaporan() {
        String bulan = spBulan.getSelectedItem().toString();
        String tahun = spTahun.getSelectedItem().toString();
        ApiServiceLaporan api = InitRetrofit.getLaporan();
        Call<Response_lap_pengiriman> lapCall = api.getLaporan(bulan, tahun);
        lapCall.enqueue(new Callback<Response_lap_pengiriman>() {
            @Override
            public void onResponse(Call<Response_lap_pengiriman> call, Response<Response_lap_pengiriman> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<LaporanItem> data_split = response.body().getLaporan();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterLapPengiriman adapter = new AdapterLapPengiriman(EntryLapPengiriman.this, data_split);
                        rvLp.setAdapter(adapter);
                    } else {
                        Toast.makeText(EntryLapPengiriman.this, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<Response_lap_pengiriman> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }
}