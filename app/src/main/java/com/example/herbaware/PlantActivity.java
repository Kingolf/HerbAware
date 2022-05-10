package com.example.herbaware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.herbaware.databinding.ActivityPlantBinding;

public class PlantActivity extends AppCompatActivity {

    ActivityPlantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        //back button
        ImageButton back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBack();
            }
        });


        }

    private void gotoBack() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

}
