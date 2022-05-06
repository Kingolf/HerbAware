package com.example.herbadmin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class AdminLoginActivity extends AppCompatActivity {

    TextView noAccount;
    AppCompatButton login_btn;
    EditText edtEmail, edtPassword;
    RelativeLayout progressBar;

    String txt_email,txt_password;

    FirebaseAuth mAuth;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** requestWindowFeature(Window.FEATURE_ACTION_BAR);
         this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
         Objects.requireNonNull(getSupportActionBar()).hide();**/

        setContentView(R.layout.activity_admin_login);

        //Linking variables to xml
        login_btn = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        noAccount = findViewById(R.id.signup_page);
        progressBar = findViewById(R.id.progess_bar);


        mAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_email = edtEmail.getText().toString();
                txt_password = edtPassword.getText().toString();


                //Check if input field is empty
                if(TextUtils.isEmpty(txt_email)){
                    edtEmail.setError("Required");
                }
                else if(TextUtils.isEmpty(txt_password)){
                    edtPassword.setError("Required");
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    LoginAdminIn(txt_email,txt_password);
                    edtEmail.setText("");
                    edtPassword.setText("");
                }

            }
        });

        //SignUp Action
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLoginActivity.this, SignUpActivity.class));
            }
        });


    }


    private void LoginAdminIn(String email, String password) {
        //Log the admin in if email and password is correct
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent intent = new Intent(AdminLoginActivity.this, AdminHomePageActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(),"Welcome Admin",Toast.LENGTH_SHORT).show();

                }
                else{
                    try
                    {
                        throw task.getException();
                    }
                    // if user enters wrong email.
                    catch (FirebaseAuthInvalidUserException invalidEmail)
                    {
                        Log.d(TAG, "onComplete: invalid_email");
                        Toast.makeText(getApplicationContext(),"Email does not exist",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        edtEmail.setText("");
                        edtPassword.setText("");
                    }
                    // if user enters wrong password.
                    catch (FirebaseAuthInvalidCredentialsException wrongPassword)
                    {
                        Log.d(TAG, "onComplete: wrong_password");
                        progressBar.setVisibility(View.GONE);
                        edtEmail.setText("");
                        edtPassword.setText("");

                        Toast.makeText(getApplicationContext(),"Please enter correct password",Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Log.d(TAG, "onComplete: " + e.getMessage());
                    }
                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            startActivity(new Intent(AdminLoginActivity.this, AdminHomePageActivity.class));
        }

    }
}