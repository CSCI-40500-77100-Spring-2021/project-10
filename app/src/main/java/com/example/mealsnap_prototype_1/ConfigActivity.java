package com.example.mealsnap_prototype_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import services.authhandler.Amplify;

public class ConfigActivity extends AppCompatActivity {

    //Config activity will config stuff we need to(passes or succeeds activity just redirects with intents to what we wanna check).
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        //Test Amplify Config
        Amplify testAmplify = new Amplify();
        testAmplify.configure(getApplicationContext());


        //Redirect
        goToSignupActivity();
    }

    //moves to Signup
    private void goToSignupActivity() {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
        finish();
    }
}