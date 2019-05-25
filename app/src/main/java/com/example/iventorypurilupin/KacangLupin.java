package com.example.iventorypurilupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class KacangLupin extends AppCompatActivity {

    private TextView judul;
    private ImageButton ibWhole;
    private ImageButton ibSplit;
    private ImageButton ibFlake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacang_lupin);
        judul = (TextView) findViewById(R.id.tv_judul_event);
        ibWhole = findViewById(R.id.ib_whole);
        ibSplit = findViewById(R.id.ib_split);
        ibFlake = findViewById(R.id.ib_flake);
        judul.setText("Kacang Lupin");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.valueOf(judul));
        getSupportActionBar().setIcon(R.drawable.back);

       ibWhole.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(KacangLupin.this,KacangWhole.class);
               startActivity(intent);
           }
       });

       ibSplit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(KacangLupin.this,KacangSplit.class);
               startActivity(intent);
           }
       });
       ibFlake.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(KacangLupin.this,KacangFlake.class);
               startActivity(intent);
           }
       });
    }
}
