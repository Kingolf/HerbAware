package com.example.herbaware;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private CardView cardViewHome;
    private CardView cardViewCategory;
    private CardView cardViewChat;
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
        cardViewHome=(CardView) findViewById(R.id.homecv);
        cardViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomepage(); }
        });

        //category action
        cardViewCategory=(CardView) findViewById(R.id.catcv);
        cardViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategoryPage(); }
        });

        //logout
        logout=(AppCompatButton) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loginpage(); }
        });

        //chat action
        cardViewChat=(CardView) findViewById(R.id.chatcv);
        cardViewChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChatPage(); }
        });

        //ImageScan action
        cardViewImgScan=(CardView) findViewById(R.id.imgscancv);
        cardViewImgScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImgScanPage(); }
        });

        //About action
        cardViewAbout=(CardView) findViewById(R.id.aboutcv);
        cardViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutPage(); }
        });


    }

    //-------METHODS FOR CLICK ACTIONS

    //homepage method
    private void openHomepage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }


    //categories method
    private void openCategoryPage() {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    //chat method
    private void openChatPage() {
        Intent intent = new Intent(this, ChatActivity.class);
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
