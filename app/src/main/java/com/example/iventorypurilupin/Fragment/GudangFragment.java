package com.example.iventorypurilupin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.iventorypurilupin.Gudang.EntriOlahWhole;
import com.example.iventorypurilupin.Gudang.EntriWhole;
import com.example.iventorypurilupin.Gudang.FormPermintaan;
import com.example.iventorypurilupin.R;
import com.example.iventorypurilupin.Gudang.TampilPermintaan;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GudangFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GudangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GudangFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GudangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GudangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GudangFragment newInstance(String param1, String param2) {
        GudangFragment fragment = new GudangFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView judul = getActivity().findViewById(R.id.tv_judul_event);
        judul.setText("Gudang");


        View v = inflater.inflate(R.layout.fragment_gudang, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(String.valueOf(judul));

        ImageButton btnFormPermintaan = v.findViewById(R.id.ib_form_permintaan);
        ImageButton btnSj = v.findViewById(R.id.ib_sj);
//        ImageButton btnEntri = v.findViewById(R.id.ib_entri_barang_sf);
        ImageButton btnWholeolah = v.findViewById(R.id.ib_entri_olah);
        ImageButton btnWholeTambah=v.findViewById(R.id.ib_entri_barang_whole);

        btnFormPermintaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FormPermintaan.class);
                startActivity(intent);
            }
        });

        btnSj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TampilPermintaan.class);
                startActivity(intent);
            }
        });

       /* btnEntri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EntrySplitFlake.class);
                startActivity(intent);
            }
        });*/


        btnWholeolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EntriOlahWhole.class);
                startActivity(intent);
            }
        });

        btnWholeTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), EntriWhole.class);
                startActivity(intent);
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
