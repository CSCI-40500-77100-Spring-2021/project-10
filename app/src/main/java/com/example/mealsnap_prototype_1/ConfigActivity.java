package com.example.mealsnap_prototype_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import services.authhandler.APIRequest;
import services.authhandler.APIServiceResponseEvent;
import services.authhandler.Amplify;

public class ConfigActivity extends AppCompatActivity {

    public static final String TAG = "ConfigActivity";
    public static String ROOT_URL = "https://dbkw974ay1.execute-api.us-east-1.amazonaws.com/";

    private TextView debugin;

    //Config activity will config stuff we need to(passes or succeeds activity just redirects with intents to what we wanna check).
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        debugin = findViewById(R.id.debugin);

        //Test Amplify Config
        try {
            Amplify.configure(getApplicationContext());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //Test API Get Request
        APIRequest.get("echo", new APIServiceResponseEvent() {
            @Override
            public void onSuccess(ResponseBody requestBody) {

                    ConfigActivity.this.runOnUiThread(() -> {
                        try {
                            debugin.setText(requestBody.string());

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
            }

            @Override
            public void onFailure(IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Redirect
        loadMainAppActivity();
    }

    //moves to loadMainAppActivity
    private void loadMainAppActivity() {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
        finish();
    }
}