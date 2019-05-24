package com.example.iventorypurilupin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.iventorypurilupin.Fragment.EntriFragment;
import com.example.iventorypurilupin.Fragment.GudangFragment;
import com.example.iventorypurilupin.Fragment.HomeFragment;
import com.example.iventorypurilupin.Fragment.LaporanFragment;
import com.example.iventorypurilupin.Fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bn_main);
        fragmentManager = getSupportFragmentManager();

        //Untuk inisialisasi fragment pertama kali
        fragmentManager.beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

        //Memberikan listener saat menu item di bottom navigation diklik
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.Home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.Entri:
                        fragment = new EntriFragment();
                        break;
                    case R.id.gudang:
                        fragment = new GudangFragment();
                        break;
                    case R.id.Laporan:
                        fragment = new LaporanFragment();
                        break;
                    case R.id.Profil:
                        fragment = new ProfileFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
    }
}