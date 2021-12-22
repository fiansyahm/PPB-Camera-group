package com.salmanarif.tugasppb.ui.absent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salmanarif.tugasppb.NavigationActivity;
import com.salmanarif.tugasppb.R;
import com.squareup.picasso.Picasso;


public class DetailAbsentFragment extends Fragment {
    TextView nama,posisi,waktu,workfromhome,statusabsen,signature,photo;
    String idstr,namastr,posisistr;
    ImageView bukti;
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
        View view= inflater.inflate(R.layout.fragment_detail_absent, container, false);
        idstr=extras.getString("id");
        namastr=extras.getString("nama");
        posisistr=extras.getString("posisi");
        nama=view.findViewById(R.id.fdetailabsentnamastr);
        nama.setText(extras.getString("nama"));
        posisi=view.findViewById(R.id.fdetailabsentjabatanstr);
        posisi.setText(extras.getString("posisi"));
        waktu=view.findViewById(R.id.fdetailabsentwaktustr);
        waktu.setText(((NavigationActivity) getActivity()).waktu);
        workfromhome=view.findViewById(R.id.fdetailabsentworkfromhomestr);
        workfromhome.setText(((NavigationActivity) getActivity()).workfromhome);
        statusabsen=view.findViewById(R.id.fdetailabsentstatusabsensistr);
        statusabsen.setText(((NavigationActivity) getActivity()).statusabsen);
        photo=view.findViewById(R.id.fdetailabsentfototv);
        photo.setText(((NavigationActivity) getActivity()).fotoabsen);
        signature=view.findViewById(R.id.fdetailabsentttdtv);
        signature.setText(((NavigationActivity) getActivity()).signatureabsen);
        bukti=view.findViewById(R.id.fdetailabsentbukti);
        Picasso.get()
                .load("https://ostensible-berry.000webhostapp.com/file_php/absentAttendance/"+((NavigationActivity) getActivity()).buktiijin+".jpg")
                .into(bukti);
        return view;
    }
}