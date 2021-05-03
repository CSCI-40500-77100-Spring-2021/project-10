package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        tvSignup.setText("Meal      Snap");

        btnSignUp.setOnClickListener(v -> goToMainActivity());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}