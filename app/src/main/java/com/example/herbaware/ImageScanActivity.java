package com.example.herbaware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class ImageScanActivity extends AppCompatActivity {
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_image_scan);

        //Linking variable to button
        imageButton = findViewById(R.id.btnimg);

        //CLICK ACTIONS

        //back button
        ImageButton back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBack();
            }
        });

        //homepage click action

        //Open Camera
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                openTakePhoto();
            }
        });
    }

    //METHODS

        //Open Camera
        private void openTakePhoto(){
            Intent intent = new Intent(this, TakePhotoActivity.class);
            startActivity(intent);
        }

    //back button method
    private void gotoBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    }
