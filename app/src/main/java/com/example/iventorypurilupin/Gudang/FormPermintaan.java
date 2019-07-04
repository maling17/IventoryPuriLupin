package com.example.iventorypurilupin.Gudang;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceDetailPermintaan;
import com.example.iventorypurilupin.Network.ApiServiceDetailPermintaanFlake;
import com.example.iventorypurilupin.Network.ApiServiceGudang;
import com.example.iventorypurilupin.Network.ApiServiceTujuan;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_detail_split.Response_detail_split;
import com.example.iventorypurilupin.response.response_mitra.Value;
import com.example.iventorypurilupin.response.response_tujuan.MitraItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.iventorypurilupin.R.layout.activity_form_permintaan;

public class FormPermintaan extends AppCompatActivity {

    String id_mitra1;
    String daerah_mitra2;
    private TextView judul;
    private TextInputEditText etNmrPermintaan;
    private EditText etTglPermintaan;
    private Spinner spTujuan;
    private TextInputEditText etSplit;
    private TextInputEditText etFlake;
    private Button btnSimpan;
    private ProgressDialog progress;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_form_permintaan);


        etNmrPermintaan = findViewById(R.id.et_nmr_permintaan);
        etTglPermintaan = findViewById(R.id.et_tanggal);
        spTujuan = findViewById(R.id.sp_tujuan);
        etSplit = findViewById(R.id.et_split);
        etFlake = findViewById(R.id.et_flake);
        btnSimpan = findViewById(R.id.btn_simpan_permintaan);
        Button btnTanggal = findViewById(R.id.btn_tanggal);
        progress = new ProgressDialog(FormPermintaan.this);
        judul = (TextView) findViewById(R.id.tv_judul_event);

        judul.setText("Form Permintaan");

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormPermintaan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                        etTglPermintaan.setText(dateFormater.format(date.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setCancelable(false);
                progress.setMessage("Loading ...");
                progress.show();
                tambah_permintaan();
                tambahDetailSplit();
                tambahFlake();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final FormPermintaan mContext = this;

        final ApiServiceTujuan apiServiceTujuan = InitRetrofit.getTujuan();
        Call<com.example.iventorypurilupin.response.response_tujuan.Response> tampilCall = apiServiceTujuan.getTujuan();
        tampilCall.enqueue(new Callback<com.example.iventorypurilupin.response.response_tujuan.Response>() {
            @Override
            public void onResponse(Call<com.example.iventorypurilupin.response.response_tujuan.Response> call, Response<com.example.iventorypurilupin.response.response_tujuan.Response> response) {
                if (response.isSuccessful()) {
                    final List<MitraItem> tujuanItems = response.body().getMitra();
                    List<String> listTujuan = new ArrayList<String>();
                    for (int i = 0; i < tujuanItems.size(); i++) {
                        listTujuan.add(tujuanItems.get(i).getDaerahMitra());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, listTujuan);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spTujuan.setAdapter(adapter);

                    spTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            id_mitra1 = tujuanItems.get(position).getId_mitra();
                            daerah_mitra2 = tujuanItems.get(position).getDaerahMitra();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data tujuan", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<com.example.iventorypurilupin.response.response_tujuan.Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void tambah_permintaan() {

        String idPermintaan = etNmrPermintaan.getText().toString();
        String TglPermintaan = etTglPermintaan.getText().toString();
        String tujuanPermintaan = daerah_mitra2;
        String id_mitra = id_mitra1;

        ApiServiceGudang api = InitRetrofit.getInstanceGudang();

        Call<Value> EntriCall = api.tambah_permintaan(idPermintaan, TglPermintaan, tujuanPermintaan, id_mitra);
        EntriCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(FormPermintaan.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(FormPermintaan.this, message, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void tambahDetailSplit() {
        String id_permintaan = etNmrPermintaan.getText().toString();
        String split = etSplit.getText().toString();
        int id_brg = 2;

        ApiServiceDetailPermintaan api = InitRetrofit.getDetailPermintaan();
        Call<Response_detail_split> detail_splitCall = api.tambah_detail_split(id_permintaan, id_brg, split);
        detail_splitCall.enqueue(new Callback<Response_detail_split>() {
            @Override
            public void onResponse(Call<Response_detail_split> call, Response<Response_detail_split> response) {
                assert response.body() != null;
                String value = String.valueOf(response.body().getValue());
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(FormPermintaan.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(FormPermintaan.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response_detail_split> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void tambahFlake() {
        String id_permintaan = etNmrPermintaan.getText().toString();
        String flake = etFlake.getText().toString();
        int id_brg = 3;

        ApiServiceDetailPermintaanFlake api = InitRetrofit.getDetailFlake();
        Call<Response_detail_split> detail_splitCall = api.tambah_detail_flake(id_permintaan, id_brg, flake);
        detail_splitCall.enqueue(new Callback<Response_detail_split>() {
            @Override
            public void onResponse(Call<Response_detail_split> call, Response<Response_detail_split> response) {
                assert response.body() != null;
                String value = String.valueOf(response.body().getValue());
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(FormPermintaan.this, message, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(FormPermintaan.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response_detail_split> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
