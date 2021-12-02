package com.example.cameraapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AttandenceActivity extends AppCompatActivity {

    private GoogleMap mMap;
    ActivityResultLauncher<Intent> launchSomeActivity;
    private SignaturePad mSignaturePad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attandence);
        String list[]={"Reguler","Bulan Puasa"};
        Spinner spinner = (Spinner) findViewById(R.id.spinnerWorkCode);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);

        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        TextView datetxt=findViewById(R.id.date);
        datetxt.setText(fDate);


        launchSomeActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK&&result.getData()!=null) {
                            Bundle bundle=result.getData().getExtras();
                            Bitmap bitmap=(Bitmap)bundle.get("data");
                            ImageView iv=findViewById(R.id.imageAttandence);
                            iv.setImageBitmap(bitmap);

                        }
                    }
                });

        Button mSaveButton=findViewById(R.id.saveSignature);
        Button mClearButton=findViewById(R.id.deleteSignature);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad_attandence);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(AttandenceActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

    }


    public void operasi(View v) {
        switch (v.getId()){
            case R.id.buttonUploadPhotoAttandence:uploadFoto();break;
            case R.id.saveSignature:saveSignature();break;
            case R.id.deleteSignature:mSignaturePad.clear();break;
//                case R.id.btnBukaFragment:bukaFragment();break;
//                case R.id.btnGPS:bukaGPS();break;
//                case R.id.btnGmapView:bukaGmapView();break;
//                case R.id.btnMap:bukaGMAP();break;
        }
    }

    void uploadFoto(){
            Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launchSomeActivity.launch(it);
    }

    void saveSignature(){

    }
}