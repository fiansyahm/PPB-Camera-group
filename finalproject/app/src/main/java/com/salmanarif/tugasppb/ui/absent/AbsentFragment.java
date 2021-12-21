package com.salmanarif.tugasppb.ui.absent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salmanarif.tugasppb.R;



import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.salmanarif.tugasppb.DetailAttendanceFragment;
import com.salmanarif.tugasppb.NavigationActivity;
import com.salmanarif.tugasppb.R;
import com.salmanarif.tugasppb.Response;
import com.salmanarif.tugasppb.RetrofitClient;
import com.salmanarif.tugasppb.databinding.FragmentAbsentBinding;
import com.salmanarif.tugasppb.list;
import com.salmanarif.tugasppb.listAdapter;
import com.salmanarif.tugasppb.ui.absent.AbsentViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class AbsentFragment extends Fragment {

    private AbsentViewModel absentViewModel;
    private FragmentAbsentBinding binding;

    //    list
    listAdapter Kontakadapter;
    ArrayList<list> listKontak = new ArrayList<>();
    String currentdatetime[];
    String workfromhome[];
    String statusabsen[];
    String photo[];
    String signature[];
    ImageButton buttonRefresh,buttonRefresh1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        absentViewModel =
                new ViewModelProvider(this).get(AbsentViewModel.class);

        binding = FragmentAbsentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSbsent;
        absentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
                ListView lv = root.findViewById(R.id.flistView);
                Kontakadapter= new listAdapter(getContext(),R.layout.item_user,listKontak);
                lv.setAdapter(Kontakadapter);
                buttonRefresh=root.findViewById(R.id.btnRefresh);
                buttonRefresh.setOnClickListener(operasi);
                buttonRefresh1=root.findViewById(R.id.btnAbsent);
                buttonRefresh1.setOnClickListener(operasi);

//                isi
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @SuppressLint("Range")
                    public void onItemClick(AdapterView<?> parent, View item, int position, long rowID) {
                        buttonRefresh.setVisibility(View.GONE);
                        ((NavigationActivity) getActivity()).Rid=R.id.btnRefresh;
                        ((NavigationActivity) getActivity()).waktu=currentdatetime[position];
                        ((NavigationActivity) getActivity()).workfromhome=workfromhome[position];
                        ((NavigationActivity) getActivity()).statusabsen=statusabsen[position];
                        ((NavigationActivity) getActivity()).fotoabsen=photo[position];
                        ((NavigationActivity) getActivity()).signatureabsen=signature[position];
                        Kontakadapter.clear();
                        DetailAttendanceFragment dt=new DetailAttendanceFragment();
                        ((NavigationActivity) getActivity()).newFragment(dt);
                    }
                });
//
                ambildata();

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
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnRefresh:Kontakadapter.clear();ambildata();buttonRefresh1.setVisibility(View.VISIBLE);;break;
                case R.id.btnAbsent:startAbsen();break;

            }
        }
    };

    public void toast(String result){
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
    }

    public void startAbsen(){
        buttonRefresh.setVisibility(View.GONE);
       buttonRefresh1.setVisibility(View.GONE);
       ((NavigationActivity) getActivity()).Rid=R.id.btnRefresh;
       Kontakadapter.clear();
        AbsentformFragment dt=new AbsentformFragment();
       ((NavigationActivity) getActivity()).newFragment(dt);
    }

    private void insertKontak(String nm, String hp){
        list newKontak = new list(nm,hp);
        Kontakadapter.add(newKontak);
    }

    @SuppressLint("Range")
    private void ambildata(){

        Bundle extras = getActivity().getIntent().getExtras();
        Call<Response> call= RetrofitClient.getInstance().getApi().attendance(extras.getString("id"));
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if(response.body().isStatus()){
                    currentdatetime=response.body().getCurrentdatetime();
                    workfromhome=response.body().getWorkfromhome();
                    statusabsen=response.body().getAttendancestatus();
                    photo=response.body().getPhoto();
                    signature=response.body().getSignature();
                    for(int x=0;x<response.body().getTotalrow();x++)
                        insertKontak(currentdatetime[x], statusabsen[x]);
                    toast("Sukses Load Data");
                }else{

                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                toast("Gagal Load Data");
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void operasi (View v){

        change_data(v);
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void change_data(View v){
        listKontak.clear();
        ambildata();
        LayoutInflater li = LayoutInflater.from(getContext());
        View inputDialog = li.inflate(R.layout.input_dialog,null);

        AlertDialog.Builder buat = new AlertDialog.Builder(getContext());
        buat.setView(inputDialog);

        final EditText nama = inputDialog.findViewById(R.id.nama);
        final EditText noHp = inputDialog.findViewById(R.id.noHp);

        buat
                .setCancelable(false)
                .setPositiveButton("YES", (dialog, which) -> {
//                  if (v.getId() == R.id.btnSearch) {
//                        search_item(nama.getText().toString(), noHp.getText().toString());
//                    }
//                    dialog.dismiss();
                })
                .setNegativeButton("NO", (dialog, which) -> dialog.cancel());
        buat.show();
    }



}