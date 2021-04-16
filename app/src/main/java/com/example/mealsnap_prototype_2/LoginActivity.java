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

        //TODO Create button states with greyed out if all fields are not filled in
        btnLogin.setOnClickListener(v -> handleSignin());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void handleSignin() {
        AuthHandler.signOut(new AuthEvents() {
            @Override
            public void onSuccess() {
                AuthHandler.signIn(etLoginuser.getText().toString(), etLoginpass.getText().toString(), new AuthEvents() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG, "Outter signout passed");
                        Log.i(TAG, "Signed in successfully");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Logged In!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        goToMainActivity();
                    }

                    @Override
                    public void onFailure(AuthException authError) {
                        Log.i(TAG, "Sign in failed");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Username or Password Didn't Match", Toast.LENGTH_SHORT).show();
                            }
                        });
                        //TODO Error Handling
                    }
                });
            }

            @Override
            public void onFailure(AuthException authError) {
                Log.i(TAG, "Outter signout failed");
                //TODO Error Handling
            }
        });
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}