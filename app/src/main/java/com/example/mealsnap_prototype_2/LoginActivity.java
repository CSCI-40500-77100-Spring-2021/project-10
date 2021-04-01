package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton tmpBLogin;
    TextView tvLogin;
    EditText etLoginuser;
    EditText etLoginpass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tmpBLogin = findViewById(R.id.tmpBLogin);
        tvLogin = findViewById(R.id.tvLogin);
        etLoginuser = findViewById(R.id.etLoginuser);
        etLoginpass = findViewById(R.id.etLoginpass);
        btnLogin = findViewById(R.id.btnLogin);

        tmpBLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToOnboarding();
            }
        });

        tvLogin.setText("Welcome Back");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }

    private void goBackToOnboarding() {
        Intent i = new Intent(this, OnboardingActivity.class);
        startActivity(i);
        finish();
    }
    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}