package com.example.iventorypurilupin;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.iventorypurilupin.response.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMitra extends AppCompatActivity {
    private TextInputEditText etNmDaerah;
    private TextView judul;
    private TextInputEditText etPic;
    private TextInputEditText etNoTelp;
    private TextInputEditText etAlamat;
    private TextInputEditText etidDaerah;
    public ProgressBar pbMitra;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mitra);


        etidDaerah = findViewById(R.id.et_update_id_mitra);
        etNmDaerah = findViewById(R.id.et_nm_daerah);
        etPic = findViewById(R.id.et_pic);
        etNoTelp = findViewById(R.id.et_noTelp);
        etAlamat = findViewById(R.id.et_alamat);
        pbMitra = findViewById(R.id.pb_mitra);
        Button btnUbah = (Button) findViewById(R.id.btn_ubah_mitra);
        judul = (TextView) findViewById(R.id.tv_judul_event);

        judul.setText("Entri Mitra");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(UpdateMitra.this);
                progress.setCancelable(false);
                progress.setMessage("Loading ...");
                progress.show();
                ubah_mitra();

            }
        });


        Intent intent = getIntent();
        String id_mitra = intent.getStringExtra("id_daerah");
        String daerah_mitra = intent.getStringExtra("daerah_mitra");
        String PIC = intent.getStringExtra("PIC");
        String noTelp = intent.getStringExtra("noTelp");
        String alamat = intent.getStringExtra("alamat");

        etidDaerah.setText(id_mitra);
        etNmDaerah.setText(daerah_mitra);
        etPic.setText(PIC);
        etNoTelp.setText(noTelp);
        etAlamat.setText(alamat);
    }

    public void ubah_mitra() {

        String id_mitra = etidDaerah.getText().toString();
        String daerah_mitra = etNmDaerah.getText().toString();
        String PIC = etPic.getText().toString();
        String noTelp = etNoTelp.getText().toString();
        String alamat = etAlamat.getText().toString();

        ApiServiceMitra api = InitRetrofit.getInstanceEntri();
        Call<Value> UbahCall = api.ubah_mitra(id_mitra, daerah_mitra, PIC, noTelp, alamat);

        UbahCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(UpdateMitra.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateMitra.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(UpdateMitra.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
