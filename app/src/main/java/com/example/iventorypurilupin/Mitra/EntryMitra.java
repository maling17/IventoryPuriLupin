package com.example.iventorypurilupin.Mitra;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceMitra;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_mitra.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryMitra extends AppCompatActivity {

    public ProgressBar pbMitra;
    private TextInputEditText etNmDaerah;
    private TextView judul;
    private TextInputEditText etPic;
    private TextInputEditText etNoTelp;
    private TextInputEditText etAlamat;
    private TextInputEditText etidDaerah;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_mitra);

        etidDaerah = findViewById(R.id.et_id_mitra);
        etNmDaerah = findViewById(R.id.et_nm_daerah);
        etPic = findViewById(R.id.et_pic);
        etNoTelp = findViewById(R.id.et_noTelp);
        etAlamat = findViewById(R.id.et_alamat);
        pbMitra = findViewById(R.id.pb_mitra);
        Button btnSimpan = (Button) findViewById(R.id.btn_simpan_mitra);


        judul = (TextView) findViewById(R.id.tv_judul_event);

        judul.setText("Entri Mitra");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(EntryMitra.this);
                progress.setCancelable(false);
                progress.setMessage("Loading ...");
                progress.show();
                Tambah_mitra();
            }
        });

    }

    public void Tambah_mitra() {
        String id_mitra = etidDaerah.getText().toString();
        String daerah_mitra = etNmDaerah.getText().toString();
        String PIC = etPic.getText().toString();
        String noTelp = etNoTelp.getText().toString();
        String alamat = etAlamat.getText().toString();

        ApiServiceMitra api = InitRetrofit.getInstanceEntri();
        Call<Value> EntriCall = api.insert_mitra(id_mitra, daerah_mitra, PIC, noTelp, alamat);
        EntriCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(EntryMitra.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EntryMitra.this, message, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
