package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import services.authhandler.AuthEvents;
import services.authhandler.AuthHandler;

public class SignupActivity extends AppCompatActivity {

    TextView tvSignup;
    EditText etSignupuser;
    EditText etSignupemail;
    EditText etSignuppass;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tvSignup = findViewById(R.id.tvSignup);
        etSignupuser = findViewById(R.id.etSignupuser);
        etSignupemail = findViewById(R.id.etSignupemail);
        etSignuppass = findViewById(R.id.etSignuppass);
        btnSignUp = findViewById(R.id.btnSignUp);

        tvSignup.setText("Get Started!");

        //TODO Create button states with greyed out if all fields are not filled in
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Call Auth Sign In after all fields have been entered
                //If grey out method is not used a bug exists where once fields are entered properly after being changed it still fails to sign up
                goToConfirmActivity();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void goToConfirmActivity() {
        Intent i = new Intent(this, ConfirmActivity.class);
        startActivity(i);
        finish();
    }
}