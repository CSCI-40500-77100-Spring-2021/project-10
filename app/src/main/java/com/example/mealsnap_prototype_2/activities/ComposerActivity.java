package com.example.mealsnap_prototype_2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mealsnap_prototype_2.R;
import com.example.mealsnap_prototype_2.interfaces.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import com.example.mealsnap_prototype_2.services.appapi.APIRequest;

public class ComposerActivity extends AppCompatActivity {

    private static final String TAG = "ComposerActivity";

    private EditText etComposeTitle;
    private EditText etComposeDescription;
    private Button btnCaptureImage;
    private ImageView ivPreviewPostImage;
    private Button btnComposeSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etComposeTitle = findViewById(R.id.etComposeTitle);
        etComposeDescription = findViewById(R.id.etComposeDescription);
        btnCaptureImage = findViewById(R.id.btnCaptureImage);
        ivPreviewPostImage = findViewById(R.id.ivPreviewPostImage);
        btnComposeSubmit = findViewById(R.id.btnComposeSubmit);

        btnCaptureImage.setOnClickListener(v -> launchCamera());
        btnComposeSubmit.setOnClickListener(v -> submitPost());

        //TODO error handle missing input fields

    }


    private void submitPost() {
        //TODO make gallery photo object with given fields
        //TODO call api to post gallery photo

    }

    //TODO define this
    private void launchCamera() {
        //TODO a bunch of photo handling idk where to start rn
    }
}