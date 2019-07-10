package com.example.iventorypurilupin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
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
        SharedPreferences sharedPreferences = getSharedPreferences(Login.my_shared_preferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Login.session_status, false);
        if (Login.session_status == "false"){

            Intent intent=new Intent(this,Login.class);
            startActivity(intent);


        }
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