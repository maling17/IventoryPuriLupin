package com.example.iventorypurilupin;

import android.annotation.SuppressLint;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Network.ApiServiceDetailPengiriman;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.response.response_detail.LaporanDetailItem;
import com.example.iventorypurilupin.response.response_detail.Response_detail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanPengiriman extends AppCompatActivity {


    private TextView tvIdSj;
    private TextView tvTglSj;
    private TextView tvTanggal;
    private TextView tvTujuan;
    private TextView tvSplitSj;
    private TextView tvFlakeSj;
    private Bitmap bitmap;
    private LinearLayout pdf;
    private RecyclerView rvDetail;

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_pengiriman);

        tvIdSj = findViewById(R.id.tv_no_sj);
        tvTglSj = findViewById(R.id.tv_tgl_sj);
        pdf = findViewById(R.id.pdf);
        tvTanggal = findViewById(R.id.tv_tanggal);
//        rvDetail = findViewById(R.id.rv_detail);
//        rvDetail.setLayoutManager(new LinearLayoutManager(this));
//        rvDetail.setHasFixedSize(true);
        tvTujuan = findViewById(R.id.tv_tujuan_laporan_pengiriman);
        tvSplitSj = findViewById(R.id.tv_split_laporan_pengiriman);
        tvFlakeSj = findViewById(R.id.tv_flake_laporan_pengiriman);
        final Button btnCetak = findViewById(R.id.btn_cetak);


        btnCetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCetak.setVisibility(View.GONE);
                Log.d("size", " " + pdf.getWidth() + "  " + pdf.getWidth());
                bitmap = loadBitmapFromView(pdf, pdf.getWidth(), pdf.getHeight());
                createPdf();
            }
        });
        TampilLaporan();
    }


    private void TampilLaporan() {
        String idSj=getIntent().getStringExtra("id_sj");
        String tglSj = getIntent().getStringExtra("tgl_sj");
        String tujuan=getIntent().getStringExtra("tujuan");
        String splitSj=getIntent().getStringExtra("split_sj");
        String flake_sj=getIntent().getStringExtra("flake_sj");
        tvTanggal.setText(tglSj);
        tvIdSj.setText(idSj);
        tvTglSj.setText(tglSj);
        tvTujuan.setText(tujuan);
        tvSplitSj.setText(splitSj);
        tvFlakeSj.setText(flake_sj);
       /* String tanggal = tvTanggal.getText().toString();

        ApiServiceDetailPengiriman api = InitRetrofit.getDetail();
        Call<Response_detail> data_detail = api.getDetail(tglSj,tanggal);
        data_detail.enqueue(new Callback<Response_detail>() {
            @Override
            public void onResponse(Call<Response_detail> call, Response_login<Response_detail> response) {

                if (response.body() != null) {
                    Log.d("response api detail", response.body().toString());
                }
                List<LaporanDetailItem> data_mitra = null;
                if (response.body() != null) {
                    data_mitra = response.body().getLaporan();
                }
                boolean status = false;
                if (response.body() != null) {
                    status = response.body().isStatus();
                }
                if (status) {
                        AdapterDetailLapPengiriman adapter = new AdapterDetailLapPengiriman(LaporanPengiriman.this, data_mitra);
                        rvDetail.setAdapter(adapter);
                    } else {
                        Toast.makeText(LaporanPengiriman.this, "Laporan tidak ada tidak ada", Toast.LENGTH_LONG).show();
                    }
                }


            @Override
            public void onFailure(Call<Response_detail> call, Throwable t) {

            }
        });*/
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
        String targetPdf = "/sdcard/pdffromlayout.pdf";
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
        File file = new File("/sdcard/pdffromlayout.pdf");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(LaporanPengiriman.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }

}

