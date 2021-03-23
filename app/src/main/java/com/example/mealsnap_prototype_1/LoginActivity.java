package com.example.mealsnap_prototype_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {

    //Tag for debugging
    public static final String TAG = "LoginActivity";

    //Declared xml stuff
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ////PARSE STUFF TO BE REMOVED
        /*
        if(ParseUser.getCurrentUser() != null){
            Log.i(TAG, "Previously logged in");
            goToFeedActivity();
        }
        //*/
        ////PARSE STUFF TO BE REMOVED

        //Link to xml stuff
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        //on click move to next activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Sign Up Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);  ///PARSE STUFF TO BE REMOVED
                //goToFeedActivity();
            }
        });
    }

    ////PARSE STUFF TO BE REMOVED
    private void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Login failed", e);
                    Toast.makeText(LoginActivity.this, "Username or password do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }
                goToFeedActivity();
                Toast.makeText(LoginActivity.this, "Logged in!, Moving to feed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    ////PARSE STUFF TO BE REMOVED

    //moves to FeedActivity
    private void goToFeedActivity() {
        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);
        finish();
    }

    private void goToSignupActivity() {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
        finish();
    }
}