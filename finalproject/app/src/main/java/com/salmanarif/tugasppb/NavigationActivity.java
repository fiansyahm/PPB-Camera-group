package com.salmanarif.tugasppb;

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
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.salmanarif.tugasppb.databinding.ActivityNavigationBinding;
import com.salmanarif.tugasppb.ui.attendance.AttendanceFragment;
import com.salmanarif.tugasppb.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class NavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;
    public String photo=null;
    public Bitmap bitmap=null;
    private ActivityResultLauncher<Intent> launchSomeActivity;

    //    Location
    private LocationManager mylocationManager;
    private LocationListener mylocationListener;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public String lokasiLatitude;
    public String lokasiLongitude;
    public Double lokasiLatitudeInt;
    public Double lokasiLongitudeInt;

//    String info profil
    public String id,nama,posisi;
    public View hview;
    public ImageView profileHeader;
    public int profileFlag=0;

//    attendance list
    public String waktu,workfromhome,statusabsen,fotoabsen,signatureabsen;


//    r.id
    public int Rid=0;
//    profil
public String photoName=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentManager fm = getSupportFragmentManager();

//if you added fragment via layout xml


        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigation.toolbar);
        binding.appBarNavigation.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_attendance,R.id.nav_absent,R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        launchSomeActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK&&result.getData()!=null) {
                            Bundle bundle=result.getData().getExtras();
                            bitmap=(Bitmap)bundle.get("data");
                            photo=BitMapToString(bitmap);
//                            if(profileFlag==1)updateProfile();
                        }
                    }
                });

        mylocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mylocationListener = new NavigationActivity.lokasiListener();
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
        Bundle extras = getIntent().getExtras();
        if(extras.getString("id")!=null) {
            id = extras.getString("id");
            nama = extras.getString("nama");
            posisi = extras.getString("posisi");
        }
        else{
            id = "1";
            nama = "Muhammad Arif";
            posisi = "posisi";
        }
        hview=navigationView.getHeaderView(0);
        TextView namaHeader=hview.findViewById(R.id.textViewHeaderNama);
        namaHeader.setText( nama);
        TextView posisiHeader=hview.findViewById(R.id.textViewHeaderPosisi);
        posisiHeader.setText( posisi);
        profileHeader=hview.findViewById(R.id.imageViewHeaderProfile);
        profileHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
                profileFlag=1;
            }
        });

        updateProfile();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    public void updateProfile(){
        if(profileFlag==1) {
            Call<Response> call = RetrofitClient.getInstance().getApi().updateprofile(id + ";" + photo);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.body().isStatus()) {
                        photoName = response.body().getNama();
                        if(photoName!=null){
                            Picasso.get()
                                    .load("https://ostensible-berry.000webhostapp.com/file_php/profileimages/" + photoName + ".jpg")
                                    .into(profileHeader);
                        }

                    } else {
                        Toast.makeText(NavigationActivity.this, response.body().getRemark(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(NavigationActivity.this, "Upload Gagal", Toast.LENGTH_SHORT).show();
                }
            });
            profileFlag = 0;
        }
        else{
            if(getIntent().getExtras().getString("myimage").length()>2){
                Picasso.get()
                        .load("https://ostensible-berry.000webhostapp.com/file_php/profileimages/" + getIntent().getExtras().getString("myimage") + ".jpg")
                        .into(profileHeader);
            }
        }
    }

    FragmentManager fragmentManager;
    public void newFragment(Fragment fm){
        Fragment fragment = fm;
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_navigation, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed(){
        Fragment fragment = new BlankFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_navigation, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        ImageButton btn1=findViewById(Rid);
        btn1.setVisibility(View.VISIBLE);
    }

    public void takePicture(){
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        launchSomeActivity.launch(it);
    }


    public String BitMapToString(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
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




}