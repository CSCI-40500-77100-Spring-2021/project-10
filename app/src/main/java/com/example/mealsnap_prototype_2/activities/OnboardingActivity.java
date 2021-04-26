package com.example.mealsnap_prototype_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthSession;
import com.example.mealsnap_prototype_2.R;
import com.example.mealsnap_prototype_2.interfaces.ResultCallback;

import com.example.mealsnap_prototype_2.models.user.Auth;

public class OnboardingActivity extends AppCompatActivity {

    private static final String TAG = "OnboardingActivity";
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

        tvOnboard.setText("Meal Snap");
        btnToLogin.setOnClickListener(v -> startActivity(new Intent(OnboardingActivity.this, LoginActivity.class)));
        btnToSignUp.setOnClickListener(v -> startActivity(new Intent(OnboardingActivity.this, SignupActivity.class)));

        Auth.restoreUser(new ResultCallback<AuthSession, AuthException>() {
            @Override
            public void onSuccess(AuthSession result) {
                Log.i(TAG, "auto logged in moving to main activity");
                goToMainActivity();
            }

            @Override
            public void onError(AuthException authError) {
                Log.i(TAG, "auto login failed");
            }
        });
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}