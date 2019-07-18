package com.example.iventorypurilupin.Laporan;

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
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iventorypurilupin.Network.ApiServiceDetailPengiriman;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_detail.LaporanItem;
import com.example.iventorypurilupin.response.response_detail.Response_lap2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private TextView tvPic;
    private Handler handler = new Handler();
    private TextView tvTanggalCetak;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yyyy");
            String strdate = sdf1.format(c1.getTime());
            tvTanggalCetak.setText(strdate);
        }
    };

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


        pdf = findViewById(R.id.pdf);

        tvPic = findViewById(R.id.tv_profil_pengiriman);
        tvTanggal = findViewById(R.id.tv_tanggal);
        rvDetail = findViewById(R.id.rv_detail);
        rvDetail.setLayoutManager(new LinearLayoutManager(this));
        rvDetail.setHasFixedSize(true);

        tvTanggalCetak = findViewById(R.id.tv_Tanggal_cetak_lp);

        final Button btnCetak = findViewById(R.id.btn_cetak);


        String pic = getIntent().getStringExtra("text");
        tvPic.setText(pic);


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
        handler.postDelayed(runnable, 1000);

    }

    private void TampilLaporan() {

        String tglSj2 = getIntent().getStringExtra("tanggal");

        tvTanggal.setText(tglSj2);

        String tgl_sj = tvTanggal.getText().toString();

        ApiServiceDetailPengiriman api = InitRetrofit.getDetail();
        Call<Response_lap2> data_detail = api.getDetail(tgl_sj);
        data_detail.enqueue(new Callback<Response_lap2>() {
            @Override
            public void onResponse(Call<Response_lap2> call, Response<Response_lap2> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<LaporanItem> data_split = response.body().getLaporan();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterDetailLapPengiriman adapter = new AdapterDetailLapPengiriman(LaporanPengiriman.this, data_split);
                        rvDetail.setAdapter(adapter);
                    } else {
                        Toast.makeText(LaporanPengiriman.this, "Permintaan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_lap2> call, Throwable t) {
                t.printStackTrace();
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

