package com.example.mealsnap_prototype_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.example.mealsnap_prototype_2.R;
import com.example.mealsnap_prototype_2.interfaces.ResultCallback;

import com.example.mealsnap_prototype_2.models.user.Auth;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = "SettingsActivity";

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnLogout = findViewById(R.id.btnLogout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogout.setOnClickListener(v -> handleLogout());
    }

    private void handleLogout() {
        Auth.signOut(new ResultCallback<Boolean, AuthException>() {
            @Override
            public void onSuccess(Boolean _) {
                Log.i(TAG, "Logged out successfully.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SettingsActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(AuthException authError) {
                Log.i(TAG, "Log out failed.");
                //TODO handle error
            }
        });
        goBackToLogin();
    }

    private void goBackToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}