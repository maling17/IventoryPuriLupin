package com.example.iventorypurilupin.Gudang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.AdapterIdPermintaan;
import com.example.iventorypurilupin.Network.ApiServiceId;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_id_permintaan.IdPermintaanItem;
import com.example.iventorypurilupin.response.response_id_permintaan.Response_id_permintaan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.iventorypurilupin.R.layout.activity_form_permintaan;

public class FormPermintaan extends AppCompatActivity {

    public String id_mitra1;
    public String daerah_mitra2;
    public Spinner spTujuan;
    private TextView judul;
    private TextInputEditText etNmrPermintaan;
    private EditText etTglPermintaan;
    private TextInputEditText etSplit;
    private TextInputEditText etFlake;
    private Button btnSimpan;
    private ProgressDialog progress;
    private RecyclerView rvPermintaan;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_form_permintaan);

        rvPermintaan = findViewById(R.id.rv_permintaan);
        rvPermintaan.setLayoutManager(new LinearLayoutManager(this));
        rvPermintaan.setHasFixedSize(true);
        judul = (TextView) findViewById(R.id.tv_judul_event);

        judul.setText("Form Permintaan");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TampilPermintaan();
    }

    private void TampilPermintaan() {
        ApiServiceId api = InitRetrofit.getId2();
        Call<Response_id_permintaan> idCall = api.idPermintaan();
        idCall.enqueue(new Callback<Response_id_permintaan>() {
            @Override
            public void onResponse(Call<Response_id_permintaan> call, Response<Response_id_permintaan> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<IdPermintaanItem> data_split = response.body().getPermintaan();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterIdPermintaan adapter = new AdapterIdPermintaan(FormPermintaan.this, data_split);
                        rvPermintaan.setAdapter(adapter);
                    } else {
                        Toast.makeText(FormPermintaan.this, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_id_permintaan> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
