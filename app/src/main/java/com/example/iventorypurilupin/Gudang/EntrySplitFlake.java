package com.example.iventorypurilupin.Gudang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceFlake;
import com.example.iventorypurilupin.Network.ApiServiceSplit;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.Value;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntrySplitFlake extends AppCompatActivity {

    public TextInputEditText splitEntri;
    public TextInputEditText flakeEntri;
    private ProgressDialog progress;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_split_flake);

        splitEntri = findViewById(R.id.et_split_entri);
        flakeEntri = findViewById(R.id.et_flake_entri);
        Button btnSimpanEntri = findViewById(R.id.btn_simpan_entri);

        TextView judul = findViewById(R.id.tv_judul_event);
        btnSimpanEntri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(EntrySplitFlake.this);
                progress.setCancelable(true);
                progress.setMessage("Loading ...");
                progress.show();
                Update();

            }
        });
        judul.setText("Entri Barang");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);
    }

    private void UpdateSplit() {
        String split_entri = splitEntri.getText().toString();

        ApiServiceSplit apiServiceSplit = InitRetrofit.getUpdateSplit();
        Call<Value> splitCall = apiServiceSplit.update_split_brg(split_entri);
        splitCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(EntrySplitFlake.this, message, Toast.LENGTH_LONG).show();

                    finish();
                } else {
                    Toast.makeText(EntrySplitFlake.this, message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void UpdateFlake() {
        String flake_entri = flakeEntri.getText().toString();

        ApiServiceFlake apiServiceFlake = InitRetrofit.getUpdateFlake();
        Call<Value> flakeCall = apiServiceFlake.update_flake_brg(flake_entri);
        flakeCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(@NonNull Call<Value> call, @NonNull Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")) {
                    Toast.makeText(EntrySplitFlake.this, message, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(EntrySplitFlake.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void Update() {
        if (splitEntri != null && flakeEntri != null) {
            UpdateSplit();
            UpdateFlake();
            progress.dismiss();
            finish();
            Toast.makeText(this, "Berhasil Mengupdate Stok", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "kedua nya tidak boleh kosong", Toast.LENGTH_LONG).show();

        }
    }

}


