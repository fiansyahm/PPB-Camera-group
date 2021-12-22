package com.salmanarif.tugasppb.ui.attendance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.salmanarif.tugasppb.NavigationActivity;
import com.salmanarif.tugasppb.R;
import com.salmanarif.tugasppb.Response;
import com.salmanarif.tugasppb.RetrofitClient;
import com.salmanarif.tugasppb.databinding.FragmentAttendanceBinding;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;

public class AttendanceFragment extends Fragment {
//    start

    ActivityResultLauncher<Intent> launchSomeActivity;
    private SignaturePad mSignaturePad;

    //    Location
    private LocationManager mylocationManager;
    private LocationListener mylocationListener;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private String lokasiLatitude;
    private String lokasiLongitude;
    private Double lokasiLatitudeInt;
    private Double lokasiLongitudeInt;

    //    datayangdikirim
    private String dataterkirim;

    //    profil user
    private String idUser,namaUser,jabatanUser,workFromHome;

    //    kamera
    public byte[] b;
    private String imagestringcode;
    private Bitmap bitmap;
    private String photo,signature,currentTime,currentDate;

    //    TextView
    private TextView dateNow;
    private Spinner spinner;

    //    schedule
    private int count=0;
    private ArrayList<String> list = new ArrayList<String>();
    //    String list[]={"","Reguler","Bulan Puasa"};
    private TextView JamMasukAwal,JamMasuk,JamPulang,JamPulangAkhir;
    private String listScheduledet[];
    private String absensi;
    private int absensiEnable=0;
    private ImageView img;
//    end

//    absensi
    private CheckBox wfh;


    private AttendanceViewModel attendanceViewModel;
    private FragmentAttendanceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        attendanceViewModel =
                new ViewModelProvider(this).get(AttendanceViewModel.class);

        binding = FragmentAttendanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textAttendance;
        attendanceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                wfh=root.findViewById(R.id.fwfhCheckBox);
                img=root.findViewById(R.id.fimageAttandence);
                list.add("NONE");

                Locale locale = new Locale("id", "ID");
                currentTime = new SimpleDateFormat("HH:mm:ss", locale).format(new Date());
                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                dateNow=root.findViewById(R.id.fdate);
                dateNow.setText(currentDate);

                TextView datetxt=root.findViewById(R.id.fhariTextView);
                datetxt.setText("Today: "+new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date()));

                if (count==0){
                    count=1;
                    Call<Response> call= RetrofitClient.getInstance().getApi().schedule("h;h");
                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                            if(response.body().isStatus()){
                                String listSchedule[]=response.body().getSchedule();
                                for(int x=0;x<response.body().getTotalrow();x++)
                                    list.add(listSchedule[x]);
//                    Toast.makeText(AttendanceActivity.this,listSchedule[2], Toast.LENGTH_SHORT).show();
                            }else{

                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                            Toast.makeText(getContext(), "Form Gagal Terkirim", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                spinner = (Spinner) root.findViewById(R.id.fspinnerWorkCode);
                ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,list);
                AdapterList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(AdapterList);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if(0== position){

                            JamMasukAwal=root.findViewById(R.id.fjamMasukAwalTxt);
                            JamMasukAwal.setText("Jam Masuk Awal       :");
                            JamMasuk=root.findViewById(R.id.fjamMasukTxt);
                            JamMasuk.setText("Jam Masuk       :");
                            JamPulang=root.findViewById(R.id.fjamKeluarTxt);
                            JamPulang.setText("Jam Pulang       :");
                            JamPulangAkhir=root.findViewById(R.id.fjamKeluarAkhirTxt);
                            JamPulangAkhir.setText("Jam Pulang Akhir       :");
                        }
                        else
                        {

                            Call<Response> call=RetrofitClient.getInstance().getApi().scheduledetail(""+position);
                            call.enqueue(new Callback<Response>() {
                                @Override
                                public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                                    if(response.body().isStatus()){
                                        Toast.makeText(getContext(), "Sukses MENDAPATKAN WAKTU", Toast.LENGTH_SHORT).show();
                                        listScheduledet=response.body().getSchedule();

                                        JamMasukAwal=root.findViewById(R.id.fjamMasukAwalTxt);
                                        JamMasukAwal.setText("Jam Masuk Awal       "+listScheduledet[0]);
                                        JamMasuk=root.findViewById(R.id.fjamMasukTxt);
                                        JamMasuk.setText("Jam Masuk       "+listScheduledet[1]);
                                        JamPulang=root.findViewById(R.id.fjamKeluarTxt);
                                        JamPulang.setText("Jam Pulang       "+listScheduledet[2]);
                                        JamPulangAkhir=root.findViewById(R.id.fjamKeluarAkhirTxt);
                                        JamPulangAkhir.setText("Jam Pulang Akhir       "+listScheduledet[3]);
                                    }else{

                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                                    Toast.makeText(getContext(), "GAGAL MENDAPATKAN WAKTU", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });

//signature
                Button mClearButton=root.findViewById(R.id.fdeleteSignature);
                mClearButton.setOnClickListener(operasi);
                mSignaturePad = (SignaturePad) root.findViewById(R.id.fsignature_pad_attandence);
                mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
                    @Override
                    public void onStartSigning() {
                        if(((NavigationActivity) getActivity()).bitmap!=null) img.setImageBitmap(((NavigationActivity) getActivity()).bitmap);
                        toast("onStartSigning");
                    }

                    @Override
                    public void onSigned() {
                        mClearButton.setEnabled(true);
                    }

                    @Override
                    public void onClear() {
                        mClearButton.setEnabled(false);
                    }
                });
                Button buttonPhoto=root.findViewById(R.id.fbuttonUploadPhotoAttandence);
                buttonPhoto.setOnClickListener(operasi);
                Button buttonDatang=root.findViewById(R.id.fdatangbtnAttandence);
                buttonDatang.setOnClickListener(operasi);
                Button buttonPulang=root.findViewById(R.id.fpulangbtnAttandence);
                buttonPulang.setOnClickListener(operasi);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    View.OnClickListener operasi = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fdeleteSignature:deleteSignature();break;
                case R.id.fbuttonUploadPhotoAttandence:((NavigationActivity) getActivity()).takePicture();break;
                case R.id.fdatangbtnAttandence:absensi(v);break;
                case R.id.fpulangbtnAttandence:absensi(v);break;
            }
        }
    };

    public void toast(String result){

        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
    }

    public void deleteSignature(){
        mSignaturePad.clear();

    }

    //    kamera
    public String BitMapToString(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.PNG, 90, baos);
        b = baos.toByteArray();
        imagestringcode = Base64.encodeToString(b, Base64.DEFAULT);
        return imagestringcode;
    }

    Double getLokasiLatitudeInt(){
        return ((NavigationActivity) getActivity()).lokasiLatitudeInt;
    }

    Double getLokasiLongitudeInt(){
        return ((NavigationActivity) getActivity()).lokasiLongitudeInt;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void absensi(View v){
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalTime time = LocalTime.parse(currentTime);
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalTime time0 = LocalTime.parse(listScheduledet[0]);
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalTime time1 = LocalTime.parse(listScheduledet[1]);
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalTime time2 = LocalTime.parse(listScheduledet[2]);
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalTime time3 = LocalTime.parse(listScheduledet[3]);
        Date date0,date1,date2 = null;
        try {

//            date0 = (Date)formatter.parse("12:00:00");

            date0 = (Date)formatter.parse(currentTime);
            date1 = (Date)formatter.parse(listScheduledet[0]);
            date2 = (Date)formatter.parse(listScheduledet[3]);

            if (time.isAfter(time0)==true && time0.isBefore(time1)==true) {


                if (v.getId()==R.id.fdatangbtnAttandence){
                    absensi="Datang Tepat Waktu";
                    absensiEnable=1;
                }
                else absensi="Pulang Awal";absensiEnable=0;

            }
            else if (time.isAfter(time1)==true && time0.isBefore(time2)==true) {
                absensiEnable=1;

                if (v.getId()==R.id.fdatangbtnAttandence){
                    absensi="Datang Terlambat";
                }
                else absensi="Pulang Awal";

            }
            else if (time.isAfter(time2)==true && time0.isBefore(time3)==true) {


                if (v.getId()==R.id.fdatangbtnAttandence){
                    absensi="Datang Terlambat"; absensiEnable=0;
                }
                else absensi="Pulang Tepat Waktu"; absensiEnable=1;

            }
            else{
                absensiEnable=0;
                if (v.getId()==R.id.fdatangbtnAttandence){
                    absensi="Tidak diizinkan";
                }
                else absensi="Tidak diizinkan";
            }

            toast(absensi);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(wfh.isChecked()){
            workFromHome="Ya";
        }else if(!wfh.isChecked()){
            workFromHome="Tidak";
        }

        if(absensiEnable==0&&getLokasiLatitudeInt()>=38&&getLokasiLatitudeInt()<=36&&getLokasiLongitudeInt()>=-121&&getLokasiLongitudeInt()<=-123){
            toast("Gagal Absen");
        }
        else {
            dataterkirim="";
            Bundle extras = getActivity().getIntent().getExtras();
            if (extras != null) {
                idUser=extras.getString("id");
                namaUser=extras.getString("nama");
                jabatanUser=extras.getString("posisi");
            }
        }
        photo=((NavigationActivity) getActivity()).photo;
        signature=BitMapToString(mSignaturePad.getSignatureBitmap());
        dataterkirim=idUser+";"+namaUser+";"+jabatanUser+";"+workFromHome+";"+absensi+";"+photo+";"+signature;

//        start
        Call<Response> call=RetrofitClient.getInstance().getApi().uploadAttendance(dataterkirim);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                    Toast.makeText(AttendanceActivity.this, response.body().getRemark(), Toast.LENGTH_SHORT).show();

//                        startActivity(new Intent(getBaseContext(), AttendanceActivity.class));
//                    Intent i = new Intent(AttendanceActivity.this, AttendanceActivity.class);
//                    i.putExtra("id",extras.getString("id"));
//                    i.putExtra("nama",extras.getString("nama"));
//                    i.putExtra("posisi",extras.getString("posisi"));
//                    startActivity(i);
                if(response.body().isStatus()){
                    toast("Berhasil");
                }else{
                    toast("Sudah Mengisi form kehadiran sebelumnya");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                toast("gagal kirim");
            }
        });

//        end

    }
}