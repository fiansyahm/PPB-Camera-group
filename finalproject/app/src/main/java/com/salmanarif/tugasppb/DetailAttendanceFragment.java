package com.salmanarif.tugasppb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailAttendanceFragment extends Fragment {

    TextView nama,posisi,waktu,workfromhome,statusabsen;
    ImageView signature,photo;
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
        View view= inflater.inflate(R.layout.fragment_detail_attendance, container, false);
        idstr=extras.getString("id");
        namastr=extras.getString("nama");
        posisistr=extras.getString("posisi");
        nama=view.findViewById(R.id.fdetailnamastr);
        nama.setText(extras.getString("nama"));
        posisi=view.findViewById(R.id.fdetailjabatanstr);
        posisi.setText(extras.getString("posisi"));
        waktu=view.findViewById(R.id.fdetailwaktustr);
        waktu.setText(((NavigationActivity) getActivity()).waktu);
        workfromhome=view.findViewById(R.id.fdetailworkfromhomestr);
        workfromhome.setText(((NavigationActivity) getActivity()).workfromhome);
        statusabsen=view.findViewById(R.id.fdetailstatusabsensistr);
        statusabsen.setText(((NavigationActivity) getActivity()).statusabsen);
        photo=view.findViewById(R.id.fdetailpicturetv);
        signature=view.findViewById(R.id.fdetailsignaturetv);
        Picasso.get()
                .load("https://ostensible-berry.000webhostapp.com/file_php/photoAttendance/"+((NavigationActivity) getActivity()).fotoabsen+".jpg")
                .into(photo);
        Picasso.get()
                .load("https://ostensible-berry.000webhostapp.com/file_php/signatureAttendance/"+((NavigationActivity) getActivity()).signatureabsen+".jpg")
                .into(signature);
        return view;
    }
}