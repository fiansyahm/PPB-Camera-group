package com.salmanarif.tugasppb.ui.gallery;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.salmanarif.tugasppb.DetailAttendanceFragment;
import com.salmanarif.tugasppb.DetailmedcheckFragment;
import com.salmanarif.tugasppb.NavigationActivity;
import com.salmanarif.tugasppb.R;
import com.salmanarif.tugasppb.Response;
import com.salmanarif.tugasppb.RetrofitClient;
import com.salmanarif.tugasppb.databinding.FragmentGalleryBinding;
import com.salmanarif.tugasppb.list;
import com.salmanarif.tugasppb.listAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    listAdapter Kontakadapter;
    ArrayList<list> listKontak = new ArrayList<>();
    String currentdate[];
    String kelayakan[];
    String kondisitubuh[];
    String kontakodp[];
    String demam[];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
                ListView lv = root.findViewById(R.id.flistViewMedcheck);
                Kontakadapter= new listAdapter(getContext(),R.layout.item_user,listKontak);
                lv.setAdapter(Kontakadapter);
                ImageButton buttonRefresh=root.findViewById(R.id.btnRefreshMedcheck);
                buttonRefresh.setOnClickListener(operasi);

//                isi
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @SuppressLint("Range")
                    public void onItemClick(AdapterView<?> parent, View item, int position, long rowID) {
                        buttonRefresh.setVisibility(View.GONE);
                        ((NavigationActivity) getActivity()).Rid=R.id.btnRefreshMedcheck;
                        ((NavigationActivity) getActivity()).waktu=currentdate[position];
                        ((NavigationActivity) getActivity()).workfromhome=kelayakan[position];
                        ((NavigationActivity) getActivity()).statusabsen=kondisitubuh[position];
                        ((NavigationActivity) getActivity()).fotoabsen=kontakodp[position];
                        ((NavigationActivity) getActivity()).signatureabsen=demam[position];
                        Kontakadapter.clear();
                        DetailmedcheckFragment dt=new DetailmedcheckFragment();
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
                case R.id.btnRefreshMedcheck:Kontakadapter.clear();ambildata();break;

            }
        }
    };

    public void toast(String result){
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
    }

    private void insertKontak(String nm, String hp){
        list newKontak = new list(nm,hp);
        Kontakadapter.add(newKontak);
    }

    @SuppressLint("Range")
    private void ambildata(){

        Bundle extras = getActivity().getIntent().getExtras();
        Call<Response> call= RetrofitClient.getInstance().getApi().listmedcheck(extras.getString("id"));
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if(response.body().isStatus()){
                    currentdate=response.body().getCurrentdatetime();
                    kelayakan=response.body().getWorkfromhome();
                    kondisitubuh=response.body().getAttendancestatus();
                    kontakodp=response.body().getPhoto();
                    demam=response.body().getSignature();
                    for(int x=0;x<response.body().getTotalrow();x++)
                        insertKontak(currentdate[x], kondisitubuh[x]);
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