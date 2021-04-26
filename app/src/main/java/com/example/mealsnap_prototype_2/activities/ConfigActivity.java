package com.example.mealsnap_prototype_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;

import com.example.mealsnap_prototype_2.R;
import com.example.mealsnap_prototype_2.services.Amplify;

public class ConfigActivity extends AppCompatActivity {

    private static final String TAG = "ConfigActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Log.i(TAG, "1st Passing on config");


        try {
            Amplify.configure(getApplicationContext());
            Log.i(TAG, "Initialized AmplifyService");
        } catch (AmplifyException error) {
            Log.e(TAG, "Could not initialize AmplifyService", error);
        }

        goToOnboardingActivity();
    }

    private void goToOnboardingActivity() {
        Intent i = new Intent(this, OnboardingActivity.class);
        startActivity(i);
        finish();
    }
}