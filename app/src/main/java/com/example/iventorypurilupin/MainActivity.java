package com.example.iventorypurilupin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.iventorypurilupin.Fragment.EntriFragment;
import com.example.iventorypurilupin.Fragment.GudangFragment;
import com.example.iventorypurilupin.Fragment.HomeFragment;
import com.example.iventorypurilupin.Fragment.LaporanFragment;
import com.example.iventorypurilupin.Fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_NAMA = "nm_user";
    private static final String TAG_JABATAN = "jabatan";
    private static final String TAG_TLP = "tlp_user";
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private TextView tvnama;
    private TextView tvJabatan;
    private TextView tvTlp;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_actionbar,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.notifikasi:
                Intent intent = new Intent(this, Notifikasi.class);

                startActivity(intent);

                return true;

        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvnama = findViewById(R.id.tv_nm);
        tvJabatan = findViewById(R.id.tv_jbtn);
        tvTlp = findViewById(R.id.tv_no_tlp);

        String nama = getIntent().getStringExtra(TAG_NAMA);
        String jabatan = getIntent().getStringExtra(TAG_JABATAN);
        String tlp = getIntent().getStringExtra(TAG_TLP);

        tvJabatan.setText(jabatan);
        tvnama.setText(nama);
        tvTlp.setText(tlp);


        //set Fragmentclass Arguments
        ProfileFragment profileFragment = new ProfileFragment();


        bottomNavigation = (BottomNavigationView) findViewById(R.id.bn_main);
        fragmentManager = getSupportFragmentManager();

        //Untuk inisialisasi fragment pertama kali
        fragmentManager.beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
        clearStack();
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
                    case R.id.Profil:// in this line i try to passing the data
                        String nama = tvnama.getText().toString();
                        String jabatan = tvJabatan.getText().toString();
                        String tlp = tvTlp.getText().toString();
                        Bundle data = new Bundle();
                        data.putString(ProfileFragment.KEY_ACTIVITY, nama);
                        data.putString(ProfileFragment.KEY_ACTIVITY2, jabatan);
                        data.putString(ProfileFragment.KEY_ACTIVITY3, tlp);
                        fragment = new ProfileFragment();
                        fragment.setArguments(data);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_container, fragment)
                                .commit();

                        break;
                }
                if (fragment != null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_container, fragment).commit();
                }

                return true;
            }
        });
    }

    private void clearStack() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }
        }

        if (fragmentManager.getFragments().size() > 0) {
            for (int i = 0; i < fragmentManager.getFragments().size(); i++) {
                Fragment mFragment = fragmentManager.getFragments().get(i);
                if (mFragment != null) {
                    fragmentManager.beginTransaction().remove(mFragment).commit();
                }
            }
        }
    }
}