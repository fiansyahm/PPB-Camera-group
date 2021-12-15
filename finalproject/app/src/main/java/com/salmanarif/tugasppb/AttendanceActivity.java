package com.salmanarif.tugasppb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class AttendanceActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launchSomeActivity;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        list.add("NONE");

        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        dateNow=findViewById(R.id.date);
        dateNow.setText(currentDate);

        TextView datetxt=findViewById(R.id.hariTextView);
        datetxt.setText("Today: "+new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date()));


        if (count==0){
            count=1;
            Call<Response> call=RetrofitClient.getInstance().getApi().schedule("h;h");
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
                    Toast.makeText(AttendanceActivity.this, "Form Gagal Terkirim", Toast.LENGTH_SHORT).show();
                }
            });

        }



        spinner = (Spinner) findViewById(R.id.spinnerWorkCode);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        AdapterList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(AdapterList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(0== position){
                    JamMasukAwal=findViewById(R.id.jamMasukAwalTxt);
                    JamMasukAwal.setText("Jam Masuk Awal       :");
                    JamMasuk=findViewById(R.id.jamMasukTxt);
                    JamMasuk.setText("Jam Masuk       :");
                    JamPulang=findViewById(R.id.jamKeluarTxt);
                    JamPulang.setText("Jam Pulang       :");
                    JamPulangAkhir=findViewById(R.id.jamKeluarAkhirTxt);
                    JamPulangAkhir.setText("Jam Pulang Akhir       :");
                }
                else
                {
                    Call<Response> call=RetrofitClient.getInstance().getApi().scheduledetail(""+position);
                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                            if(response.body().isStatus()){
                                Toast.makeText(AttendanceActivity.this, "Sukses MENDAPATKAN WAKTU", Toast.LENGTH_SHORT).show();
                                listScheduledet=response.body().getSchedule();

                                JamMasukAwal=findViewById(R.id.jamMasukAwalTxt);
                                JamMasukAwal.setText("Jam Masuk Awal       "+listScheduledet[0]);
                                JamMasuk=findViewById(R.id.jamMasukTxt);
                                JamMasuk.setText("Jam Masuk       "+listScheduledet[1]);
                                JamPulang=findViewById(R.id.jamKeluarTxt);
                                JamPulang.setText("Jam Pulang       "+listScheduledet[2]);
                                JamPulangAkhir=findViewById(R.id.jamKeluarAkhirTxt);
                                JamPulangAkhir.setText("Jam Pulang Akhir       "+listScheduledet[3]);
                            }else{

                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                            Toast.makeText(AttendanceActivity.this, "GAGAL MENDAPATKAN WAKTU", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


//camera
        launchSomeActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK&&result.getData()!=null) {
                            Bundle bundle=result.getData().getExtras();
                            bitmap=(Bitmap)bundle.get("data");
                            ImageView iv=findViewById(R.id.imageAttandence);
                            iv.setImageBitmap(bitmap);
                            photo=BitMapToString(bitmap);
                        }
                    }
                });
//signature
        Button mClearButton=findViewById(R.id.deleteSignature);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad_attandence);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(AttendanceActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
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


//        lokasi
        mylocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mylocationListener = new lokasiListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mylocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 200, mylocationListener);

    }


    public void operasi(View v) {
        switch (v.getId()){
            case R.id.buttonUploadPhotoAttandence:uploadFoto();break;
            case R.id.deleteSignature:mSignaturePad.clear();break;
            case R.id.datangbtnAttandence:absensi(v);break;
            case R.id.pulangbtnAttandence:absensi(v);break;
        }
    }

    void uploadFoto(){
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        launchSomeActivity.launch(it);
    }

    void absensi(View v){

        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date0,date1,date2 = null;
        try {
//            date0 = (Date)formatter.parse(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
            date0 = (Date)formatter.parse("12:00:00");
            date1 = (Date)formatter.parse(listScheduledet[0]);
            date2 = (Date)formatter.parse(listScheduledet[3]);

            if (date0.after(date1) && date0.before(date2)) {
                absensiEnable=1;

                if (v.getId()==R.id.datangbtnAttandence){
                    absensi="Datang Terlambat";
                }
                else absensi="Pulang Awal";

            }
            else{
                absensiEnable=0;
                if (v.getId()==R.id.datangbtnAttandence){
                    absensi="Datang Tepat Waktu";
                }
                else absensi="Pulang Tepat Waktu";
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }



        if(absensiEnable==0){
            Toast.makeText(AttendanceActivity.this,"halo:"+lokasiLatitudeInt, Toast.LENGTH_SHORT).show();
        }
        else{

            dataterkirim="";
//        sudah                                     belum
//        id+nama+jabatan+waktu+tanggal+wfo/wfh+    telat/tepatWaktu+foto+tandatangan

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                idUser=extras.getString("id");
                namaUser=extras.getString("nama");
                jabatanUser=extras.getString("posisi");
            }


        CheckBox wfh=findViewById(R.id.wfhCheckBox);


        if(wfh.isChecked()){
            workFromHome="Ya";
        }else if(!wfh.isChecked()){
            workFromHome="Tidak";
        }




//        tandatangan
            signature=BitMapToString(mSignaturePad.getSignatureBitmap());


        dataterkirim=idUser+";"+namaUser+";"+jabatanUser+";"+currentTime+";"+currentDate+";"+workFromHome+";"+absensi+";"+photo+";"+signature;

            Call<Response> call=RetrofitClient.getInstance().getApi().uploadAttendance(dataterkirim);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                    Toast.makeText(AttendanceActivity.this, "Form Sukses Terkirim", Toast.LENGTH_SHORT).show();

//                    startActivity(new Intent(getBaseContext(), MainActivity.class));
//                if(response.body().isStatus()){
//
//                }else{
//
//                }
                }

                @Override
                public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                    Toast.makeText(AttendanceActivity.this, "Form Gagal Terkirim", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    //    Lokasi
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // location-related task you need to do.
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    //Request location updates:
                    mylocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 200, mylocationListener);
                }

            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.

            }
            return;
        }
    }

    private class lokasiListener implements LocationListener {



        @Override
        public void onLocationChanged(@NonNull Location location) {
            lokasiLatitude= String.valueOf(location.getLatitude());
            lokasiLongitude= String.valueOf(location.getLongitude());
            lokasiLatitudeInt=location.getLatitude();
            lokasiLongitudeInt=location.getLongitude();

        }

        @Override
        public void onLocationChanged(@NonNull List<Location> locations) {

        }

        @Override
        public void onFlushComplete(int requestCode) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }


//    kamera
    public String BitMapToString(Bitmap userImage1) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    userImage1.compress(Bitmap.CompressFormat.PNG, 90, baos);
    b = baos.toByteArray();
    imagestringcode = Base64.encodeToString(b, Base64.DEFAULT);
    return imagestringcode;
}

}