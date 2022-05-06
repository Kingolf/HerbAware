package com.example.herbadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AdminHomePageActivity extends AppCompatActivity {

    private CardView cardViewHome;
    private CardView cardViewAddPlant;
    private CardView cardViewLogout;
    private CardView cardViewAbout;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        /** requestWindowFeature(Window.FEATURE_ACTION_BAR);
         this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
         Objects.requireNonNull(getSupportActionBar()).hide();**/

        //homepage click action
        cardViewHome=(CardView) findViewById(R.id.homecv);
        cardViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlantspage(); }
        });

        //Add plant action
        cardViewAddPlant=(CardView) findViewById(R.id.addplantcv);
        cardViewAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddPlantPage(); }
        });

        //logout
        cardViewLogout=(CardView) findViewById(R.id.logoutcv);
        cardViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loginpage(); }
        });

        //about action
        cardViewAbout=(CardView) findViewById(R.id.aboutcv);
        cardViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutPage(); }
        });
    }

    //-------METHODS FOR CLICK ACTIONS


    //about method
    private void openAboutPage() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    //homepage method
    private void openPlantspage() {
        Intent intent = new Intent(this, PlantsActivity.class);
        startActivity(intent);
    }


    //add plant method
    private void openAddPlantPage() {
        Intent intent = new Intent(this, AddPlantActivity.class);
        startActivity(intent);
    }

    //logout method
    private void Loginpage() {
        if (cardViewLogout.getId()==R.id.logoutcv){
            mAuth= FirebaseAuth.getInstance();
            mAuth.signOut();
            finish();
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }

    }
}