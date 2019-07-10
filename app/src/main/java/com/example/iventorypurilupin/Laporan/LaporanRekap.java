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
import com.example.iventorypurilupin.response.response_lap_rekap2.LaporanRekap2Item;
import com.example.iventorypurilupin.response.response_lap_rekap2.Response_rekap;
import com.example.iventorypurilupin.response.response_tahun.Response_tahun;
import com.example.iventorypurilupin.response.response_tahun.TahunItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanRekap extends AppCompatActivity {

    private Spinner spBulan;
    private Spinner spTahun;
    private RecyclerView rvRekap;
    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_rekap);

        spBulan = findViewById(R.id.sp_bulan_rekap);
        spTahun = findViewById(R.id.sp_tahun_rekap);
        rvRekap = findViewById(R.id.rv_rekap);
        rvRekap.setLayoutManager(new LinearLayoutManager(this));
        rvRekap.setHasFixedSize(true);
        Button btnCari=findViewById(R.id.btn_cari_rekap);
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CariLaporan();
            }
        });
        judul = (TextView) findViewById(R.id.tv_judul_event);


        judul.setText("Laporan Rekap Mitra");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final LaporanRekap context = this;

        ApiServiceTahun api = InitRetrofit.getTahun();
        Call<Response_tahun> tahunCall = api.getTahunRekap();
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
    }


        private void CariLaporan () {
            String bulan = spBulan.getSelectedItem().toString();
            String tahun = spTahun.getSelectedItem().toString();
            ApiServiceLaporan api = InitRetrofit.getLaporan();
            final Call<Response_rekap> lapCall = api.getLaporanRekap(bulan, tahun);
            lapCall.enqueue(new Callback<Response_rekap>() {
                @Override
                public void onResponse(Call<Response_rekap> call, Response<Response_rekap> response) {
                    if (response.isSuccessful()) {
                        Log.d("response api", response.body().toString());
                        List<LaporanRekap2Item> data_split = response.body().getLaporan();
                        boolean status = response.body().isStatus();
                        if (status) {
                            AdapterLapRekap adapter = new AdapterLapRekap(LaporanRekap.this, data_split);
                            rvRekap.setAdapter(adapter);
                        } else {
                            Toast.makeText(LaporanRekap.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                        }
                    }


                }

                @Override
                public void onFailure(Call<Response_rekap> call, Throwable t) {
                    t.printStackTrace();
                }

            });
        }
    }

