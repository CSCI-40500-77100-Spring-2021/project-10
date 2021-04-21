package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthSession;

import services.authhandler.AuthEvents;
import services.authhandler.AuthHandler;
import services.authhandler.RestoreUser;

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

        AuthHandler.restoreUser(new RestoreUser() {
            @Override
            public void onSuccess(AuthSession result) {
                Log.i(TAG, "auto logged in moving to main activity");
                goToMainActivity();
            }

            @Override
            public void onFailure(AuthException authError) {
                Log.i(TAG, "autologin failed");
            }
        });
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}