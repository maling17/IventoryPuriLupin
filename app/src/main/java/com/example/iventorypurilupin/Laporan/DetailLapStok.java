package com.example.iventorypurilupin.Laporan;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceDetailLaporanStok;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_detail_lap_stok.DetailWholeItem;
import com.example.iventorypurilupin.response.response_detail_lap_stok.Response_detail_lap_stok_whole;
import com.example.iventorypurilupin.response.response_detail_lap_stok_flake.DetailFlakeItem;
import com.example.iventorypurilupin.response.response_detail_lap_stok_flake.Response_detail_lap_stok_flake;
import com.example.iventorypurilupin.response.response_detail_lap_stok_split.DetailSplitItem;
import com.example.iventorypurilupin.response.response_detail_lap_stok_split.Response_detail_stok_split;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.iventorypurilupin.response.Response_detail_stok_split.DetailSplitItem;

public class DetailLapStok extends AppCompatActivity {


    private TextView tvTanggal;
    private RecyclerView rvWhole;
    private RecyclerView rvSplit;
    private RecyclerView rvFlake;
    private Bitmap bitmap;
    private LinearLayout pdf;

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lap_stok);
        rvWhole = findViewById(R.id.rv_detail_whole);
        rvWhole.setLayoutManager(new LinearLayoutManager(this));
        rvWhole.setHasFixedSize(true);
        pdf = findViewById(R.id.pdf2);
        rvSplit = findViewById(R.id.rv_detail_split);
        rvSplit.setLayoutManager(new LinearLayoutManager(this));
        rvSplit.setHasFixedSize(true);

        rvFlake = findViewById(R.id.rv_detail_flake);
        rvFlake.setLayoutManager(new LinearLayoutManager(this));
        rvFlake.setHasFixedSize(true);

        tvTanggal = findViewById(R.id.tv_tanggal_stok);
        final Button btnCari = findViewById(R.id.btn_cetak_stok);
        tampilLaporan();
        tampilSplit();
        tampilFlake();
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCari.setVisibility(View.GONE);
                Log.d("size", " " + pdf.getWidth() + "  " + pdf.getWidth());
                bitmap = loadBitmapFromView(pdf, pdf.getWidth(), pdf.getHeight());
                createPdf();
            }
        });

    }

    private void createPdf() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/laporan_stok.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF() {
        File file = new File("/sdcard/laporan_stok.pdf");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(DetailLapStok.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void tampilLaporan() {
        String Tanggal = getIntent().getStringExtra("tgl_pengolahan");
        tvTanggal.setText(Tanggal);
        String tgl_pengolahan = tvTanggal.getText().toString();

        ApiServiceDetailLaporanStok api = InitRetrofit.getDetailStok();
        Call<Response_detail_lap_stok_whole> detailCall = api.getWhole(tgl_pengolahan);
        detailCall.enqueue(new Callback<Response_detail_lap_stok_whole>() {
            @Override
            public void onResponse(Call<Response_detail_lap_stok_whole> call, Response<Response_detail_lap_stok_whole> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<DetailWholeItem> data_whole = response.body().getLaporan();

                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterDetailStokWhole adapter = new AdapterDetailStokWhole(DetailLapStok.this, data_whole);
                        rvWhole.setAdapter(adapter);
                    } else {
                        Toast.makeText(DetailLapStok.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_detail_lap_stok_whole> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private void tampilSplit() {
        String Tanggal = getIntent().getStringExtra("tgl_pengolahan");
        tvTanggal.setText(Tanggal);
        String tgl_pengolahan = tvTanggal.getText().toString();
        ApiServiceDetailLaporanStok api2 = InitRetrofit.getDetailStok();
        Call<Response_detail_stok_split> splitCall = api2.getSplit(tgl_pengolahan);
        splitCall.enqueue(new Callback<Response_detail_stok_split>() {
            @Override
            public void onResponse(Call<Response_detail_stok_split> call, Response<Response_detail_stok_split> response) {
                if (response.isSuccessful()) {
                    Log.d("response api split", response.body().toString());
                    List<DetailSplitItem> data_whole = response.body().getLaporan();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterDetailStokSplit adapter2 = new AdapterDetailStokSplit(DetailLapStok.this, data_whole);
                        rvSplit.setAdapter(adapter2);
                    } else {
                        Toast.makeText(DetailLapStok.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_detail_stok_split> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void tampilFlake() {
        String Tanggal = getIntent().getStringExtra("tgl_pengolahan");
        tvTanggal.setText(Tanggal);
        String tgl_pengolahan = tvTanggal.getText().toString();
        ApiServiceDetailLaporanStok api3 = InitRetrofit.getDetailStok();
        Call<Response_detail_lap_stok_flake> flakeCall = api3.getFlake(tgl_pengolahan);
        flakeCall.enqueue(new Callback<Response_detail_lap_stok_flake>() {


            @Override
            public void onResponse(Call<Response_detail_lap_stok_flake> call, Response<Response_detail_lap_stok_flake> response) {
                if (response.isSuccessful()) {
                    Log.d("response api flake", response.body().toString());
                    List<DetailFlakeItem> data_flake = response.body().getLaporan();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterDetailStokFlake adapter3 = new AdapterDetailStokFlake(DetailLapStok.this, data_flake);
                        rvFlake.setAdapter(adapter3);
                    } else {
                        Toast.makeText(DetailLapStok.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_detail_lap_stok_flake> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
