package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.example.mealsnap_prototype_2.fragments.ExploreFragment;
import com.example.mealsnap_prototype_2.fragments.GalleryFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import services.authhandler.AuthEvents;
import services.authhandler.AuthHandler;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";

    TextView tvLogin;
    EditText etLoginuser;
    EditText etLoginpass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvLogin = findViewById(R.id.tvLogin);
        etLoginuser = findViewById(R.id.etLoginuser);
        etLoginpass = findViewById(R.id.etLoginpass);
        btnLogin = findViewById(R.id.btnLogin);

        tvLogin.setText("Welcome Back");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
                //TODO add amplify function

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}