package com.example.herbaware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class CategoriesActivity extends AppCompatActivity {

    private CardView cardViewImmuneB;
    private CardView cardViewDiabetes;
    private CardView cardViewFever;
    private CardView cardViewHBP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** requestWindowFeature(Window.FEATURE_ACTION_BAR);
         this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
         Objects.requireNonNull(getSupportActionBar()).hide();**/

        setContentView(R.layout.activity_categories);

        //linking to ids
        cardViewImmuneB= findViewById(R.id.catcvimmune);
        cardViewDiabetes= findViewById(R.id.catcvdiabetes);
        cardViewFever= findViewById(R.id.catcvfever);
        cardViewHBP= findViewById(R.id.catcvhbp);

        //Click Actions

        //Immune Action
        cardViewImmuneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImmunePage(); }
        });

        //Diabetes Action
        cardViewDiabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDiabetesPage(); }
        });

        //Fever Action
        cardViewFever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeverPage(); }
        });

        //HBP Action
        cardViewHBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHBPPage(); }
        });
    }

    //Methods For Actions

    //Immune Boosters Method
    private void openImmunePage() {
        Intent intent = new Intent(this, ImmuneBoostersActivity.class);
        startActivity(intent);
    }

    //Diabetes Method
    private void openDiabetesPage() {
        Intent intent = new Intent(this, DiabetesActivity.class);
        startActivity(intent);
    }

    //Fever Method
    private void openFeverPage() {
        Intent intent = new Intent(this, FeverActivity.class);
        startActivity(intent);
    }

    //HBP Method
    private void openHBPPage() {
        Intent intent = new Intent(this, HBPressureActivity.class);
        startActivity(intent);
    }
}