package com.example.iventorypurilupin.Mitra;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceMitra;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.MitraItem;
import com.example.iventorypurilupin.response.Value;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class AdapterMitra extends RecyclerView.Adapter<AdapterMitra.MyViewHolder> {
    private Context context;
    private List<MitraItem> mitra;

    AdapterMitra(Context context, List<MitraItem> mitra) {
        this.context = context;
        this.mitra = mitra;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mitra, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterMitra.MyViewHolder holder, int position) {
        holder.tvIdDaerah.setText(mitra.get(position).getIdMitra());
        holder.tvDaerah.setText(mitra.get(position).getDaerahMitra());
        holder.tvPIC.setText(mitra.get(position).getPicMitra());
        holder.tvTelp.setText(mitra.get(position).getTlpMitra());
        holder.tvAlamat.setText(mitra.get(position).getAlamatMitra());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(holder);
            }
        });

    }

    private void showDialog(final AdapterMitra.MyViewHolder holder) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        final String id_daerah = holder.tvIdDaerah.getText().toString();
        final String daerah_mitra = holder.tvDaerah.getText().toString();
        final String PIC = holder.tvPIC.getText().toString();
        final String noTelp = holder.tvTelp.getText().toString();
        final String alamat = holder.tvAlamat.getText().toString();

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Pilih Aksi")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(true)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, UpdateMitra.class);
                        intent.putExtra("id_daerah", id_daerah);
                        intent.putExtra("daerah_mitra", daerah_mitra);
                        intent.putExtra("PIC", PIC);
                        intent.putExtra("noTelp", noTelp);
                        intent.putExtra("alamat", alamat);

                        context.startActivity(intent);

                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        alertDialogBuilder.setMessage("apakah anda yakin ingin menghapus?")
                                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        hapus_mitra(holder);
                                        showDialog(holder);
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        // membuat alert dialog dari builder
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // menampilkan alert dialog
                        alertDialog.show();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }

    @Override
    public int getItemCount() {
        return mitra.size();
    }

    private void hapus_mitra(MyViewHolder holder) {
        String id_mitra = holder.tvIdDaerah.getText().toString();

        ApiServiceMitra api = InitRetrofit.getInstanceEntri();
        Call<Value> hapusCall = api.hapus(id_mitra);
        hapusCall.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(@NonNull Call<Value> call, @NonNull Response<Value> response) {

                String value = null;
                if (response.body() != null) {
                    value = response.body().getValue();
                }
                String message = null;
                if (response.body() != null) {
                    message = response.body().getMessage();
                }
                if (value != null) {
                    if (value.equals("1")) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Value> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Jaringan Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDaerah;
        private final TextView tvPIC;
        private final TextView tvTelp;
        private final TextView tvAlamat;
        private final TextView tvIdDaerah;

        MyViewHolder(View view) {
            super(view);
            tvIdDaerah = view.findViewById(R.id.tv_idmitra);
            tvDaerah = view.findViewById(R.id.tv_daerah);
            tvPIC = view.findViewById(R.id.tv_pic);
            tvTelp = view.findViewById(R.id.tv_telp);
            tvAlamat = view.findViewById(R.id.tv_alamat);


        }
    }
}
