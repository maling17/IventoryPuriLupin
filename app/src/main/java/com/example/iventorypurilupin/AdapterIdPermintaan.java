package com.example.iventorypurilupin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.Gudang.FormPermintaan;
import com.example.iventorypurilupin.Network.ApiServiceDetailPermintaan;
import com.example.iventorypurilupin.Network.ApiServiceDetailPermintaanFlake;
import com.example.iventorypurilupin.Network.ApiServiceGudang;
import com.example.iventorypurilupin.Network.ApiServiceTujuan;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.response.response_detail_split.Response_detail_split;
import com.example.iventorypurilupin.response.response_id_permintaan.IdPermintaanItem;
import com.example.iventorypurilupin.response.response_mitra.Value;
import com.example.iventorypurilupin.response.response_tujuan.MitraItem;
import com.example.iventorypurilupin.response.response_tujuan.Response;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class AdapterIdPermintaan extends RecyclerView.Adapter<AdapterIdPermintaan.MyViewHolder> {

    private Context context;
    private List<IdPermintaanItem> idPermintaanItems;
    private FormPermintaan formPermintaan;


    public AdapterIdPermintaan(Context context, List<IdPermintaanItem> idPermintaanItems) {
        this.context = context;
        this.idPermintaanItems = idPermintaanItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.id_permintaan_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
       int nmr= idPermintaanItems.get(position).getIdPermintaan()+1;
        holder.etNmr.setText(String.valueOf(nmr));

        tampil_tujuan(holder);
        holder.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.progress.setCancelable(false);
                holder.progress.setMessage("Loading ...");
                holder.progress.show();
               tambah_permintaan(holder);
                tambahDetailSplit(holder);
                tambahFlake(holder);
            }
        });
        holder.btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                        holder.etTgl.setText(dateFormater.format(date.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return idPermintaanItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public String id_mitra1;
        public String daerah_mitra2;
        private final TextInputEditText etNmr;
        private final TextInputEditText etSplit;
        private final TextInputEditText etFlake;
        private final EditText etTgl;
        private final Spinner spTujuan;
        private final Button btnSimpan;
        private final TextView judul;
        private final ProgressDialog progress;
        private final Button btnTanggal;
        public EditText etTglPermintaan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            progress = new ProgressDialog(context);
            judul = itemView.findViewById(R.id.tv_judul_event);
            etNmr = itemView.findViewById(R.id.et_nmr_permintaan);
            etSplit = itemView.findViewById(R.id.et_split);
            etFlake = itemView.findViewById(R.id.et_flake);
            etTgl = itemView.findViewById(R.id.et_tanggal);
            spTujuan = itemView.findViewById(R.id.sp_tujuan);
            btnSimpan = itemView.findViewById(R.id.btn_simpan_permintaan);
            btnTanggal = itemView.findViewById(R.id.btn_tanggal);

        }
    }
        private void tampil_tujuan(final MyViewHolder myViewHolder) {
            final ApiServiceTujuan apiServiceTujuan = InitRetrofit.getTujuan();
            Call<Response> tampilCall = apiServiceTujuan.getTujuan();
            tampilCall.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<com.example.iventorypurilupin.response.response_tujuan.Response> call, retrofit2.Response<Response> response) {
                    if (response.isSuccessful()) {
                        final List<MitraItem> tujuanItems = response.body().getMitra();
                        List<String> listTujuan = new ArrayList<String>();
                        for (int i = 0; i < tujuanItems.size(); i++) {
                            listTujuan.add(tujuanItems.get(i).getDaerahMitra());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listTujuan);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        myViewHolder.spTujuan.setAdapter(adapter);

                        myViewHolder.spTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                myViewHolder.id_mitra1 = tujuanItems.get(position).getId_mitra();
                                myViewHolder.daerah_mitra2 = tujuanItems.get(position).getDaerahMitra();

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
                public void onFailure(Call<com.example.iventorypurilupin.response.response_tujuan.Response> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

        private void tambah_permintaan(final MyViewHolder myViewHolder) {

            String idPermintaan = myViewHolder.etNmr.getText().toString();
            String TglPermintaan = myViewHolder.etTgl.getText().toString();
            String tujuanPermintaan = myViewHolder.daerah_mitra2;
            String id_mitra = myViewHolder.id_mitra1;

            ApiServiceGudang api = InitRetrofit.getInstanceGudang();

            Call<Value> EntriCall = api.tambah_permintaan(idPermintaan, TglPermintaan, tujuanPermintaan, id_mitra);
            EntriCall.enqueue(new Callback<Value>() {
                @Override
                public void onResponse(Call<Value> call, retrofit2.Response<Value> response) {
                    assert response.body() != null;
                    String value = response.body().getValue();
                    String message = response.body().getMessage();
                    myViewHolder.progress.dismiss();
                    if (value.equals("1")) {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<Value> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }

        private void tambahDetailSplit(final MyViewHolder myViewHolder) {
            String id_permintaan = myViewHolder.etNmr.getText().toString();
            String split = myViewHolder.etSplit.getText().toString();
            int id_brg = 2;

            ApiServiceDetailPermintaan api = InitRetrofit.getDetailPermintaan();
            Call<Response_detail_split> detail_splitCall = api.tambah_detail_split(id_permintaan, id_brg, split);
            detail_splitCall.enqueue(new Callback<Response_detail_split>() {
                @Override
                public void onResponse(Call<Response_detail_split> call, retrofit2.Response<Response_detail_split> response) {
                    assert response.body() != null;
                    String value = String.valueOf(response.body().getValue());
                    String message = response.body().getMessage();
                    myViewHolder.progress.dismiss();
                    if (value.equals("1")) {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_detail_split> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

        private void tambahFlake(final MyViewHolder myViewHolder) {
            String id_permintaan = myViewHolder.etNmr.getText().toString();
            String flake = myViewHolder.etFlake.getText().toString();
            int id_brg = 3;

            ApiServiceDetailPermintaanFlake api = InitRetrofit.getDetailFlake();
            Call<Response_detail_split> detail_splitCall = api.tambah_detail_flake(id_permintaan, id_brg, flake);
            detail_splitCall.enqueue(new Callback<Response_detail_split>() {
                @Override
                public void onResponse(Call<Response_detail_split> call, retrofit2.Response<Response_detail_split> response) {
                    assert response.body() != null;
                    String value = String.valueOf(response.body().getValue());
                    String message = response.body().getMessage();
                    myViewHolder.progress.dismiss();
                    if (value.equals("1")) {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_detail_split> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }


    }

