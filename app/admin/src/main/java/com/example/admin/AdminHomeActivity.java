package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminHomeActivity extends AppCompatActivity {

    CardView plant_lst, add_plant, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        plant_lst = findViewById(R.id.plants_lst);
        add_plant = findViewById(R.id.add_plant);
        about = findViewById(R.id.aboutcv);

        add_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPlant();
            }
        });

        plant_lst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlantList();
            }
        });
    }

    private void openPlantList() {
        Intent intent = new Intent(this, PlantListActivity.class);
        startActivity(intent);
    }

    private void openAddPlant() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}