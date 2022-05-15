package com.example.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    Context context;
    ArrayList<Plants> list;

    public PlantAdapter(Context context, ArrayList<Plants> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listhome_item,parent,false);
        return new PlantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {

        Plants plants = list.get(position);
        holder.plantName.setText(plants.getPlantName());
        holder.botName.setText(plants.getBotName());
        /**Picasso.with(context)
                .load(plants.getImageUrl())
                .placeholder(R.drawable.leaf_avatar)
                .fit()
                .centerCrop()
                .into(holder.plantImage);**/
        Glide.with(context).load(list.get(position).getImageUrl()).into(holder.plantImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(ArrayList<Plants> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }


    public static class PlantViewHolder extends RecyclerView.ViewHolder{

        TextView plantName, botName;
        CircleImageView plantImage;

        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);

            plantName = itemView.findViewById(R.id.liplantname);
            botName = itemView.findViewById(R.id.lipbotname);
            plantImage = itemView.findViewById(R.id.liplantimage);

        }
    }
}
