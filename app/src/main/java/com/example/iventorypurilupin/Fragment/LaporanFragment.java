package com.example.iventorypurilupin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.iventorypurilupin.Laporan.EntryLapPengiriman;
import com.example.iventorypurilupin.Laporan.LaporanRekap;
import com.example.iventorypurilupin.Laporan.Laporan_Stok;
import com.example.iventorypurilupin.Login;
import com.example.iventorypurilupin.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LaporanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LaporanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LaporanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String KEY_ACTIVITY = "msg_activity";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences sharedPreferences;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ImageButton ibLapStok;
    private ImageButton ibLapPengiriman;
    private ImageButton ibLapRekap;
    private TextView tvProfil;

    public LaporanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LaporanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LaporanFragment newInstance(String param1, String param2) {
        LaporanFragment fragment = new LaporanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView judul = getActivity().findViewById(R.id.tv_judul_event);
        judul.setText("Laporan");

        sharedPreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        View v = inflater.inflate(R.layout.fragment_laporan, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(String.valueOf(judul));


        tvProfil = v.findViewById(R.id.tv_pic_laporan);
        ibLapStok = v.findViewById(R.id.ib_lap_stok);
        ibLapPengiriman = v.findViewById(R.id.ib_lap_pengiriman);
        ibLapRekap = v.findViewById(R.id.ib_lap_rekap);


        try {
            String nama = null;
            if (getArguments() != null) {
                nama = getArguments().getString(KEY_ACTIVITY);

            }
            tvProfil.setText(nama);



        } catch (Exception ex) {
            ex.printStackTrace();
        }


        ibLapPengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=tvProfil.getText().toString();
                Intent intent = new Intent(getActivity(), EntryLapPengiriman.class);
                intent.putExtra("text",text);
                startActivity(intent);
            }
        });
        ibLapStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=tvProfil.getText().toString();
                Intent i = new Intent(getActivity(), Laporan_Stok.class);
                i.putExtra("text",text);
                startActivity(i);
            }
        });
        ibLapRekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=tvProfil.getText().toString();
                Intent intent2 = new Intent(getActivity(), LaporanRekap.class);
                intent2.putExtra("text",text);
                startActivity(intent2);
            }
        });

        return v;
    }

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
