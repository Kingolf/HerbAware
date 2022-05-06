package com.example.herbadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    AppCompatButton btnStartLogin, btnStartSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /** requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        Objects.requireNonNull(getSupportActionBar()).hide();**/

        //Linking variables to xml buttons
        btnStartLogin = findViewById(R.id.btn_login);
        btnStartSignUp = findViewById(R.id.btn_signin);

        //Click Actions

        //Login Button
        btnStartLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage(); }
        });

        //SignUp Action
        btnStartSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpPage(); }
        });

    }

    //Methods For Actions

    //Login Method
    private void openLoginPage() {
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }

    //SignUp Method
    private void openSignUpPage() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}