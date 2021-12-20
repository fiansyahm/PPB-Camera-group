package com.salmanarif.tugasppb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailmedcheckFragment extends Fragment {

    TextView nama,posisi,waktu,workfromhome,statusabsen,signature,photo;
    String idstr,namastr,posisistr;
    Bundle extras;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getActivity().getIntent().getExtras();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detailmedcheck, container, false);

        idstr=extras.getString("id");
        namastr=extras.getString("nama");
        posisistr=extras.getString("posisi");

        nama=view.findViewById(R.id.fmedcheckdetailnamastr);
        nama.setText(extras.getString("nama"));
        posisi=view.findViewById(R.id.fmedcheckdetailjabatanstr);
        posisi.setText(extras.getString("posisi"));
        waktu=view.findViewById(R.id.fmedcheckdetailwaktustr);
        waktu.setText(((NavigationActivity) getActivity()).waktu);
        workfromhome=view.findViewById(R.id.fmedcheckdetailworkfromhomestr);
        workfromhome.setText(((NavigationActivity) getActivity()).workfromhome);
        statusabsen=view.findViewById(R.id.fmedcheckdetailstatusabsensistr);
        statusabsen.setText(((NavigationActivity) getActivity()).statusabsen);
        photo=view.findViewById(R.id.fmedcheckdetailpicturetv);
        photo.setText(((NavigationActivity) getActivity()).fotoabsen);
        signature=view.findViewById(R.id.fmedcheckdetailsignaturetv);
        signature.setText(((NavigationActivity) getActivity()).signatureabsen);
        return view;
    }
}