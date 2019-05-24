package com.example.iventorypurilupin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class KacangWhole extends AppCompatActivity {

    private TextView judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacang_whole);

        judul = (TextView) findViewById(R.id.tv_judul_event);


        judul.setText("Whole Lupin");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);
    }
}
