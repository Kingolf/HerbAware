package com.example.herbaware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder>{

    Context context;
    ArrayList<Plants> list;
    private onItemClickListener onItemClickListener;


    public PlantAdapter(Context context, ArrayList<Plants> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PlantAdapter.PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listhome_item,parent,false);
        return new PlantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.PlantViewHolder holder, int position) {

        Plants plants = list.get(position);
        holder.plantName.setText(plants.getPlantName());
        holder.botName.setText(plants.getBotName());
        Picasso.with(context)
                .load(plants.getImageUrl())
                .placeholder(R.drawable.leaf_avatar)
                .fit()
                .centerCrop()
                .into(holder.plantImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(ArrayList<Plants> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }


    public class PlantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView plantName, botName;
        public CardView parent_layout;
        CircleImageView plantImage;

        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);

            plantName = itemView.findViewById(R.id.liplantname);
            botName = itemView.findViewById(R.id.lipbotname);
            plantImage = itemView.findViewById(R.id.liplantimage);
            parent_layout = itemView.findViewById(R.id.item_cardview);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                int position =getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    onItemClickListener.onItemClick(position);
                }
            }

        }
    }
    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        onItemClickListener = listener;

    }

}
