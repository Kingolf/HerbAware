package com.example.herbaware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.herbaware.databinding.ActivityPlantBinding;

public class PlantActivity extends AppCompatActivity {

    ActivityPlantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

       /** if (intent != null){

            String name = intent.getStringExtra("name");
            String botName = intent.getStringExtra("botName");
            String about = intent.getStringExtra("about");
            String prepare = intent.getStringExtra("prepare");
            String hbenefits = intent.getStringExtra("health_benefits");
            int imageid = intent.getIntExtra("imageid",R.drawable.leaf_img);

            binding.plantProfile.setText(name);
            binding.botanicProfile.setText(botName);
            binding.aboutProfile.setText(about);
            binding.healthbenProfile.setText(hbenefits);
            binding.prepareProfile.setText(prepare);
            binding.plantImage.setImageResource(imageid);**/


        }

    }
