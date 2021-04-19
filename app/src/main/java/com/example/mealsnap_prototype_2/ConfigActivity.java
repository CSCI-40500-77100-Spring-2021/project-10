package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.core.Amplify;

import java.io.IOException;

import okhttp3.ResponseBody;
import services.authhandler.APIRequest;
import services.authhandler.APIServiceResponseEvent;
import services.authhandler.AmplifyService;
import services.authhandler.AuthEvents;
import services.authhandler.AuthHandler;

public class ConfigActivity extends AppCompatActivity {

    private static final String TAG = "ConfigActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Log.i(TAG, "1st Passing on config");


        try {
            AmplifyService.configure(getApplicationContext());
            Log.i(TAG, "Initialized AmplifyService");
        } catch (AmplifyException error) {
            Log.e(TAG, "Could not initialize AmplifyService", error);
        }




        //TODO

        //Connect UI to sign up
        //Finish hitting rest of routes methods (post stuck i think need endpoint)
        //Retrieve post method


        goToOnboardingActivity();
    }

    private void goToOnboardingActivity() {
        Intent i = new Intent(this, OnboardingActivity.class);
        startActivity(i);
        finish();
    }
}