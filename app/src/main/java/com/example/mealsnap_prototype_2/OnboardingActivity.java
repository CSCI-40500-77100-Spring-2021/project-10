package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    TextView tvOnboard;
    Button btnToLogin;
    Button btnToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        tvOnboard = findViewById(R.id.tvOnboard);
        btnToLogin = findViewById(R.id.btnToLogin);
        btnToSignUp = findViewById(R.id.btnToSignUp);

        tvOnboard.setText("Meal      Snap");
        btnToLogin.setOnClickListener(v -> startActivity(new Intent(OnboardingActivity.this, LoginActivity.class)));
        btnToSignUp.setOnClickListener(v -> startActivity(new Intent(OnboardingActivity.this, SignupActivity.class)));
    }
}