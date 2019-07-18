package com.example.iventorypurilupin.Laporan;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.iventorypurilupin.Network.ApiServiceLapRekap;
import com.example.iventorypurilupin.Network.ApiServiceTotalRekap;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_lap_rekap.LaporanRekapItem;
import com.example.iventorypurilupin.response.response_lap_rekap.Response_lap_rekap;
import com.example.iventorypurilupin.response.response_lap_rekap_flake.LaporanRekapFlakeItem;
import com.example.iventorypurilupin.response.response_lap_rekap_flake.Response_lap_rekap_flake;
import com.example.iventorypurilupin.response.response_total_rekap_flake.LaporanTotalFlakeItem;
import com.example.iventorypurilupin.response.response_total_rekap_flake.Response_total_flake;
import com.example.iventorypurilupin.response.response_total_rekap_split.LaporanTotalSplitItem;
import com.example.iventorypurilupin.response.response_total_rekap_split.Response_total_split;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailLapRekap extends AppCompatActivity {

    public final static String TAG_NAMA = "nm_user";
    SharedPreferences sharedPreferences;
    private RecyclerView rvSplit;
    private RecyclerView rvFlake;
    private TextView tvTanggal;
    private LinearLayout pdf;
    private Bitmap bitmap;
    private RecyclerView rvTotalSplit;
    private RecyclerView rvTotalFlake;
    private TextView tvOlehRekap;
    private Handler handler=new Handler();
    private TextView tvTanggalCetak;


    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lap_rekap);

        rvSplit = findViewById(R.id.rv_rekap_split);
        rvSplit.setLayoutManager(new LinearLayoutManager(this));
        rvSplit.setHasFixedSize(true);

        tvTanggalCetak = findViewById(R.id.tv_Tanggal_cetak);
        tvOlehRekap = findViewById(R.id.tv_profil_rekap);
        rvFlake = findViewById(R.id.rv_rekap_flake);
        rvFlake.setHasFixedSize(true);
        rvFlake.setLayoutManager(new LinearLayoutManager(this));

        final Button btnCetak = findViewById(R.id.btn_cetak_rekap);
        tvTanggal = findViewById(R.id.tv_tanggal_detail_rekap);
        pdf = findViewById(R.id.pdf3);

        String pic=getIntent().getStringExtra("text");
        tvOlehRekap.setText(pic);
        rvTotalSplit = findViewById(R.id.rv_rekap_total_split);
        rvTotalSplit.setLayoutManager(new LinearLayoutManager(this));
        rvTotalSplit.setHasFixedSize(true);

        rvTotalFlake = findViewById(R.id.rv_rekap_total_flake);
        rvTotalFlake.setLayoutManager(new LinearLayoutManager(this));
        rvTotalFlake.setHasFixedSize(true);


        cetakSplit();
        cetakFlake();
        cetakTotalFlake();
        cetakTotalSplit();
        btnCetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCetak.setVisibility(View.GONE);
                Log.d("size", " " + pdf.getWidth() + "  " + pdf.getWidth());
                bitmap = loadBitmapFromView(pdf, pdf.getWidth(), pdf.getHeight());
                createPdf();
            }
        });
        handler.postDelayed(runnable,1000);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yyyy");
            String strdate = sdf1.format(c1.getTime());
            tvTanggalCetak.setText(strdate);
        }
    };


    private void cetakSplit() {
        String tgl = getIntent().getStringExtra("tanggal");
        tvTanggal.setText(tgl);
        String tgl_permintaan = tvTanggal.getText().toString();

        ApiServiceLapRekap api = InitRetrofit.getRekap();
        Call<Response_lap_rekap> rekapCall = api.getRekapSplit(tgl_permintaan);
        rekapCall.enqueue(new Callback<Response_lap_rekap>() {
            @Override
            public void onResponse(Call<Response_lap_rekap> call, Response<Response_lap_rekap> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<LaporanRekapItem> data_whole = response.body().getLaporan();

                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterSplitRekap adapter = new AdapterSplitRekap(DetailLapRekap.this, data_whole);
                        rvSplit.setAdapter(adapter);
                    } else {
                        Toast.makeText(DetailLapRekap.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_lap_rekap> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void cetakTotalSplit() {
        String tgl = getIntent().getStringExtra("tanggal");
        tvTanggal.setText(tgl);
        String tgl_permintaan = tvTanggal.getText().toString();

        ApiServiceTotalRekap api = InitRetrofit.getTotalRekap();
        Call<Response_total_split> getTotalSplit = api.getTotalRekapSplit(tgl_permintaan);
        getTotalSplit.enqueue(new Callback<Response_total_split>() {
            @Override
            public void onResponse(Call<Response_total_split> call, Response<Response_total_split> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<LaporanTotalSplitItem> data_whole = response.body().getLaporan();

                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterTotalRekapSplit adapter = new AdapterTotalRekapSplit(DetailLapRekap.this, data_whole);
                        rvTotalSplit.setAdapter(adapter);
                    } else {
                        Toast.makeText(DetailLapRekap.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_total_split> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void cetakFlake() {
        String tgl = getIntent().getStringExtra("tanggal");
        tvTanggal.setText(tgl);
        String tgl_permintaan = tvTanggal.getText().toString();

        ApiServiceLapRekap api = InitRetrofit.getRekap();
        Call<Response_lap_rekap_flake> rekapCall = api.getRekapFlake(tgl_permintaan);
        rekapCall.enqueue(new Callback<Response_lap_rekap_flake>() {
            @Override
            public void onResponse(Call<Response_lap_rekap_flake> call, Response<Response_lap_rekap_flake> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<LaporanRekapFlakeItem> data_whole = response.body().getLaporan();

                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterFlakeRekap adapter = new AdapterFlakeRekap(DetailLapRekap.this, data_whole);
                        rvFlake.setAdapter(adapter);
                    } else {
                        Toast.makeText(DetailLapRekap.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_lap_rekap_flake> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void cetakTotalFlake() {
        String tgl = getIntent().getStringExtra("tanggal");
        tvTanggal.setText(tgl);
        String tgl_permintaan = tvTanggal.getText().toString();

        ApiServiceTotalRekap api = InitRetrofit.getTotalRekap();
        Call<Response_total_flake> getTotalSplit = api.getTotalRekapFlake(tgl_permintaan);
        getTotalSplit.enqueue(new Callback<Response_total_flake>() {
            @Override
            public void onResponse(Call<Response_total_flake> call, Response<Response_total_flake> response) {
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());
                    List<LaporanTotalFlakeItem> data_whole = response.body().getLaporan();

                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterTotalRekapFlake adapter = new AdapterTotalRekapFlake(DetailLapRekap.this, data_whole);
                        rvTotalFlake.setAdapter(adapter);
                    } else {
                        Toast.makeText(DetailLapRekap.this, "Laporan tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_total_flake> call, Throwable t) {
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
        String targetPdf = "/sdcard/laporan_rekap.pdf";
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
        File file = new File("/sdcard/laporan_rekap.pdf");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(DetailLapRekap.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
}
