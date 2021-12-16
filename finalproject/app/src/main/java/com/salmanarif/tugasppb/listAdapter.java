package com.salmanarif.tugasppb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class listAdapter extends ArrayAdapter<list> {
    // View lookup cache
    private static class ViewHolder {
        TextView nama;
        TextView nohp;
    }
    public listAdapter(Context context, int resource, List<list> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View ConvertView, ViewGroup parent){
        list dtkontak= getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewKontak; // view lookup cache stored in tag
        if(ConvertView==null){
            // If there's no view to re-use, inflate a brand new view for row
            viewKontak = new ViewHolder();
            ConvertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user,parent,false);
            viewKontak.nama = (TextView) ConvertView.findViewById(R.id.tNama);
            viewKontak.nohp = (TextView) ConvertView.findViewById(R.id.tnoHp);
            ImageView img = (ImageView) ConvertView.findViewById(R.id.img);
            // Cache the viewHolder object inside the fresh view
            ConvertView.setTag(viewKontak);
        }
        else{ viewKontak = (ViewHolder) ConvertView.getTag();}
        viewKontak.nama.setText(dtkontak.getNama());
        viewKontak.nohp.setText(dtkontak.getNoHp());
        return ConvertView;
    }
}
