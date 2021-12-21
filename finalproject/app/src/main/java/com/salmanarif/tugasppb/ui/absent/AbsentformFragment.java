package com.salmanarif.tugasppb.ui.absent;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.salmanarif.tugasppb.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class AbsentformFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private String tipeijinlist[]={"Ijin Penuh","Ijin Tidak Absen Pulang,Ijin Tidak Absen Masuk"};
    private String jenisijinlist[]={"Sakit","Lupa Absen","Lupa Absen Masuk+Pulang","Tidak Masuk Kerja Dengan Keterangan"};
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private View view;
    private TextView mulai,selesai;
    private String mulaistr,selesaistr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_absentform, container, false);

        Locale locale = new Locale("id", "ID");
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", locale);

        Spinner spinnertipeijin = (Spinner) view.findViewById(R.id.formabsentspinnertipeijin);
        ArrayAdapter<String> AdapterListtipeijin = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,tipeijinlist);
        AdapterListtipeijin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnertipeijin.setAdapter(AdapterListtipeijin);

        Spinner spinnerjenisijin = (Spinner) view.findViewById(R.id.formabsentspinnerjenisijin);
        ArrayAdapter<String> AdapterListjenisijin = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,jenisijinlist);
        AdapterListjenisijin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerjenisijin.setAdapter(AdapterListjenisijin);

        Button buttonRefresh=view.findViewById(R.id.formabsenttglmulaiBtn);
        buttonRefresh.setOnClickListener(operasi);
        Button buttonRefresh1=view.findViewById(R.id.formabsenttglselesaiBtn);
        buttonRefresh1.setOnClickListener(operasi);
        mulai=view.findViewById(R.id.formabsenttglmulaiET);
        selesai=view.findViewById(R.id.formabsenttglselesaiET);

        return view;
    }

    View.OnClickListener operasi = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.formabsenttglmulaiBtn:showDateDialog(v);break;
                case R.id.formabsenttglselesaiBtn:showDateDialog(v);break;
            }
        }
    };

    private void showDateDialog(View v){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */

                String tanggal=dateFormatter.format(newDate.getTime());
                if(v.getId()==R.id.formabsenttglmulaiBtn){
                    mulai.setText(tanggal);
                    mulaistr=tanggal;
                }
                else{
                    selesai.setText(tanggal);
                    selesaistr=tanggal;
                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }


}