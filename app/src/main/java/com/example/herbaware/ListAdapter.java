package com.example.herbaware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.herbaware.Plant;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Plant> {


    public ListAdapter(Context context, ArrayList<Plant> userArrayList){

        super(context,R.layout.listhome_item,userArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Plant plant = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listhome_item,parent,false);

        }

        ImageView imageView = convertView.findViewById(R.id.liplantimage);
        TextView plant_name = convertView.findViewById(R.id.liplantname);
        TextView botanical_name = convertView.findViewById(R.id.libotname);
        TextView localname = convertView.findViewById(R.id.lilocname);

        imageView.setImageResource(plant.imageId);
        plant_name.setText(plant.plant_name);
        botanical_name.setText(plant.botanical_name);
        localname.setText(plant.localName);


        return convertView;
    }
}
