package com.example.herbaware;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private CardView cardViewHome;
    private CardView cardViewCategory;
    private CardView cardViewImgScan;
    private CardView cardViewAbout;
    AppCompatButton logout;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //CLICK ACTIONS

        //homepage click action
        cardViewHome= findViewById(R.id.homecv);
        cardViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomepage(); }
        });

        //category action
        cardViewCategory= findViewById(R.id.catcv);
        cardViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategoryPage(); }
        });

        //logout
        logout= findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loginpage(); }
        });



        //ImageScan action
        cardViewImgScan= findViewById(R.id.imgscancv);
        cardViewImgScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImgScanPage(); }
        });

        //About action
        cardViewAbout= findViewById(R.id.aboutcv);
        cardViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutPage(); }
        });


    }

    //-------METHODS FOR CLICK ACTIONS

    //homepage method
    private void openHomepage() {
        Intent intent = new Intent(this, PlantListActivity.class);
        startActivity(intent);
    }


    //categories method
    private void openCategoryPage() {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }


    //ImageScan method
    private void openImgScanPage() {
        Intent intent = new Intent(this, ImageScanActivity.class);
        startActivity(intent);
    }

    //About method
    //ImageScan method
    private void openAboutPage() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    //Logout method
    private void Loginpage() {

        if (logout.getId()==R.id.btn_logout){
            mAuth= FirebaseAuth.getInstance();
            mAuth.signOut();
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }



}
