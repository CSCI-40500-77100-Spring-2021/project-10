package com.example.mealsnap_prototype_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mealsnap_prototype_2.R;
import com.example.mealsnap_prototype_2.interfaces.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import com.example.mealsnap_prototype_2.services.appapi.APIRequest;

public class ComposerActivity extends AppCompatActivity {

    private static final String TAG = "ComposerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        JSONObject json = new JSONObject();
        try {
            json.put("name", "student");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Test API Post Request
        APIRequest.post("echo", json, new ResultCallback<ResponseBody, IOException>() {
            @Override
            public void onSuccess(ResponseBody requestBody) {
                try {
                    Log.i(TAG, requestBody.string());
                } catch (IOException ioException) {
                    Log.i(TAG, "Api get req failed");
                    ioException.printStackTrace();
                }
            }

            @Override
            public void onError(IOException ioException) {
                Log.i(TAG, "Api get req failed");
                ioException.printStackTrace();
            }
        });


    }
}