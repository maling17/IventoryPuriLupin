package com.example.iventorypurilupin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iventorypurilupin.Home.AdapterBarang;
import com.example.iventorypurilupin.Home.AntrianBarang;
import com.example.iventorypurilupin.Home.KartuStok;
import com.example.iventorypurilupin.Login;
import com.example.iventorypurilupin.Network.ApiService;
import com.example.iventorypurilupin.Network.InitRetrofit;
import com.example.iventorypurilupin.Notifikasi;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.response.response_barang.BarangItem;
import com.example.iventorypurilupin.response.response_barang.Responsebarang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.iventorypurilupin.Login.TAG_ID;
import static com.example.iventorypurilupin.Login.TAG_NAMA;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView rvBarang;
    private SwipeRefreshLayout srlBarang;
    private SharedPreferences sharedPreferences;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_actionbar, menu);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rvBarang = v.findViewById(R.id.rv_barang);
        rvBarang.setHasFixedSize(true);
        rvBarang.setLayoutManager(new LinearLayoutManager(getContext()));
        srlBarang = v.findViewById(R.id.srl_barang);
        TextView judul = getActivity().findViewById(R.id.tv_judul_event);
        Button btnAntrian = v.findViewById(R.id.btn_antrian_barang);
        Button btnPesan = v.findViewById(R.id.btn_pesan);
        Button btnKartuStok=v.findViewById(R.id.btn_kartu_stok);
        sharedPreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);



        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        judul.setText("Home");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(String.valueOf(judul));


//        Button Antrian
        btnAntrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AntrianBarang.class);
                startActivity(intent);
            }
        });

        /*Button Pesan */
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pesan = "Permisi bu ingin menginformasikan bahwa stok barang whole sudah mulai sedikit. Tolong untuk memesankan kacang lagi terimakasih mohon maaf mengganggu waktu ibu";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, Pesan);
                intent.putExtra("jid", "6285710733222" + "@s.whatsapp.net");
                intent.setPackage("com.whatsapp");
                startActivity(intent);

            }
        });

        /*Button Kartu Stok*/
        btnKartuStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), KartuStok.class);
                startActivity(intent);
            }
        });

        /*Refresh*/
        srlBarang.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TampilBarang();
                        // Berhenti berputar/refreshing
                        srlBarang.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        TampilBarang();
        return v;
    }

    /*Refresh*/

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        switch (getId()) {
            case R.id.btn_antrian_barang:
                Intent intent = new Intent(getActivity(), AntrianBarang.class);
                startActivity(intent);
                break;
        }
    }

    private void TampilBarang() {
        ApiService apiService = InitRetrofit.getInstance();
        Call<Responsebarang> barangCall = apiService.request_all_barang();
        barangCall.enqueue(new Callback<Responsebarang>() {
            @Override
            public void onResponse(Call<Responsebarang> call, Response<Responsebarang> response) {
                if (response.isSuccessful()) {
                    Log.d("response Api", response.body().toString());
                    List<BarangItem> data_barang = response.body().getBarang();
                    boolean status = response.body().isStatus();
                    if (status) {
                        AdapterBarang adapterBarang = new AdapterBarang(getContext(), data_barang);
                        rvBarang.setAdapter(adapterBarang);

                    } else {
                        Toast.makeText(getContext(), "Barang tidak ada", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Responsebarang> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
