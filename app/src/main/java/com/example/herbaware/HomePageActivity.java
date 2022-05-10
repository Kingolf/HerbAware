package com.example.herbaware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.example.herbaware.databinding.ActivityHomePageActivityBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hiding ActionBar---------------------------------
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        getSupportActionBar().hide();
        //-------------------------------------------------
        setContentView(R.layout.activity_home_page_activity);

        //back button
        ImageButton back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBack();
            }
        });

    }

    //back button method
    private void gotoBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}