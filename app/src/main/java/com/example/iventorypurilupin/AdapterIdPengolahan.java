package com.example.iventorypurilupin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.Gudang.AdapterStokAwal;
import com.example.iventorypurilupin.Network.ApiServiceDetailPengolahanFlake;
import com.example.iventorypurilupin.Network.ApiServiceDetailPengolahanSplit;
import com.example.iventorypurilupin.Network.ApiServiceDetailPengolahanWhole;
import com.example.iventorypurilupin.Network.ApiServiceOlah;
import com.example.iventorypurilupin.Network.ApiServiceStokAwal;
import com.example.iventorypurilupin.Network.ApiServiceUpdateOlah;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.response.response_id_pengolahan.IdPengolahanItem;
import com.example.iventorypurilupin.response.response_mitra.Value;
import com.example.iventorypurilupin.response.response_stok_awal.Response_stok_awal;
import com.example.iventorypurilupin.response.response_stok_awal.Stok_awal_Item;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterIdPengolahan extends RecyclerView.Adapter<AdapterIdPengolahan.MyViewHolder> {

    private Context context;
    private List<IdPengolahanItem> idPengolahanItems;

    public AdapterIdPengolahan(Context context, List<IdPengolahanItem> idPengolahanItems) {
        this.context = context;
        this.idPengolahanItems = idPengolahanItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.id_pengolahan_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        int nmr = idPengolahanItems.get(position).getIdPengolahan() + 1;
        holder.etId.setText(String.valueOf(nmr));

        tampilStokAwal(holder);
        holder.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.progress.setCancelable(false);
                holder.progress.setMessage("Loading ...");
                holder.progress.show();
                TambahOlah(holder);
                tambahDetailWhole(holder);
                tambahDetailSplit(holder);
                tambahDetailFlake(holder);
                UpdateOlah(holder);
                updateSplit(holder);
                UpdateFlake(holder);
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
        return idPengolahanItems.size();
    }

    private void TambahOlah(final MyViewHolder myViewHolder) {

        String id_pengolahan = myViewHolder.etId.getText().toString();
        String tgl_pengolahan = myViewHolder.etTgl.getText().toString();
        String w_awal = myViewHolder.etWholeAwal.getText().toString();
        String s_awal = myViewHolder.etSplitAwal.getText().toString();
        String f_awal = myViewHolder.etFlakeAwal.getText().toString();
        ApiServiceOlah apiServiceOlah = InitRetrofit.getTambahOlah();
        Call<Value> olahCall = apiServiceOlah.tambah_pengolahan(id_pengolahan, tgl_pengolahan, w_awal, s_awal, f_awal);
        olahCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                myViewHolder.progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(context,MainActivity.class);
                    context.startActivity(intent);
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

    private void UpdateOlah(final MyViewHolder myViewHolder) {

        String whole = myViewHolder.etWhole.getText().toString();


        ApiServiceUpdateOlah apiServiceUpdateOlah = InitRetrofit.getUpdateolah();
        Call<Value> updateCall = apiServiceUpdateOlah.update_whole(whole);
        updateCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
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

    private void UpdateFlake(final MyViewHolder myViewHolder) {
        String flake = myViewHolder.etFlake.getText().toString();
        ApiServiceUpdateOlah api2 = InitRetrofit.getUpdateolah();
        Call<Value> updateFlake = api2.update_flake(flake);
        updateFlake.enqueue(new Callback<Value>() {

            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
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

    private void updateSplit(final MyViewHolder myViewHolder) {
        String split = myViewHolder.etSplit.getText().toString();
        ApiServiceUpdateOlah api = InitRetrofit.getUpdateolah();
        Call<Value> updateSplit = api.update_split(split);
        updateSplit.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
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

    private void tambahDetailWhole(final MyViewHolder myViewHolder) {

        String id_pengolahan = myViewHolder.etId.getText().toString();
        int id_brg = 1;
        String whole = myViewHolder.etWhole.getText().toString();
        String tgl_pengolahan = myViewHolder.etTgl.getText().toString();

        ApiServiceDetailPengolahanWhole api = InitRetrofit.getDetailPengolahanWhole();
        Call<Value> WholeDetail = api.getWhole(id_brg, id_pengolahan, whole, tgl_pengolahan);
        WholeDetail.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
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

        String id_pengolahan = myViewHolder.etId.getText().toString();
        int id_brg = 2;
        String split = myViewHolder.etSplit.getText().toString();
        String tgl_pengolahan = myViewHolder.etTgl.getText().toString();

        ApiServiceDetailPengolahanSplit api = InitRetrofit.getDetailPengolahanSplit();
        Call<Value> SplitDetail = api.getSplit(id_brg, id_pengolahan, split, tgl_pengolahan);
        SplitDetail.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
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

    private void tambahDetailFlake(final MyViewHolder myViewHolder) {

        String id_pengolahan = myViewHolder.etId.getText().toString();
        int id_brg = 3;
        String flake = myViewHolder.etFlake.getText().toString();
        String tgl_pengolahan = myViewHolder.etTgl.getText().toString();
        ApiServiceDetailPengolahanFlake api = InitRetrofit.getDetailPengolahanFlake();
        Call<Value> FlakeDetail = api.getFlake(id_brg, id_pengolahan, flake, tgl_pengolahan);
        FlakeDetail.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
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

    private void tampilStokAwal(final MyViewHolder myViewHolder) {
        ApiServiceStokAwal api = InitRetrofit.getStokAwal();
        Call<Response_stok_awal> stokawal = api.stok_awal();
        stokawal.enqueue(new Callback<Response_stok_awal>() {
            @Override
            public void onResponse(Call<Response_stok_awal> call, Response<Response_stok_awal> response) {
                Log.d("response api", response.body().toString());
                List<Stok_awal_Item> data_sj = response.body().getBarang();
                boolean status = response.body().isStatus();
                if (status) {
                    AdapterStokAwal adapter = new AdapterStokAwal(context, data_sj);
                    myViewHolder.rvStok.setAdapter(adapter);
                } else {
                    Toast.makeText(context, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Response_stok_awal> call, Throwable t) {

            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView rvStok;
        private final TextInputEditText etId;
        private final EditText etTgl;
        private final Button btnTanggal;
        private final TextInputEditText etWhole;
        private final TextInputEditText etSplit;
        private final TextInputEditText etFlake;
        private final TextInputEditText etWholeAwal;
        private final TextInputEditText etSplitAwal;
        private final TextInputEditText etFlakeAwal;
        private final ProgressDialog progress;
        private final Button btnSimpan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rvStok = itemView.findViewById(R.id.rv_stok);
            etId = itemView.findViewById(R.id.et_id_pengolahan);
            etId.setEnabled(false);
            etTgl = itemView.findViewById(R.id.et_tanggal_olah);
            btnTanggal = itemView.findViewById(R.id.btn_tanggal);
            etWhole = itemView.findViewById(R.id.etWhole);
            etSplit = itemView.findViewById(R.id.et_split);
            etFlake = itemView.findViewById(R.id.et_flake);
            etWholeAwal = itemView.findViewById(R.id.etWhole_awal);
            etSplitAwal = itemView.findViewById(R.id.et_split_awal);
            etFlakeAwal = itemView.findViewById(R.id.et_flake_awal);
            btnSimpan = itemView.findViewById(R.id.btn_simpan_olah);
            progress = new ProgressDialog(context);
            rvStok.setHasFixedSize(true);
            rvStok.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
