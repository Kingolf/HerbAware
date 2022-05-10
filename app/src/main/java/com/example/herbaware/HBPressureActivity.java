package com.example.herbaware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class HBPressureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hbpressure);

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
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }
}