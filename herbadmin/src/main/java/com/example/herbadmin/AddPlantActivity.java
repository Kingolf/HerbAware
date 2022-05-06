package com.example.herbadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Objects;

public class AddPlantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        //Hiding ActionBar---------------------------------
        /** requestWindowFeature(Window.FEATURE_ACTION_BAR);
         this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
         Objects.requireNonNull(getSupportActionBar()).hide();**/
        //-------------------------------------------------


    }
}