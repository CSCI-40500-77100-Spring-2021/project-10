package com.example.mealsnap_prototype_2.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mealsnap_prototype_2.R;
import com.example.mealsnap_prototype_2.interfaces.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import com.example.mealsnap_prototype_2.services.appapi.APIRequest;

public class ComposerActivity extends AppCompatActivity {

    private static final String TAG = "ComposerActivity";
    private static final String FILE_NAME = "photo.jpg";
    private File photoFile;
    private static final int REQUEST_CODE = 42;

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

        btnCaptureImage.setOnClickListener(v -> {
            try {
                launchCamera();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        btnComposeSubmit.setOnClickListener(v -> submitPost());

        //TODO error handle missing input fields

    }


    private void submitPost() {
        //TODO make gallery photo object with given fields
        //TODO call api to post gallery photo

    }

    //TODO define this
    private void launchCamera() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = getPhotoFile(FILE_NAME);

        //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile);
        Uri fileProvider = FileProvider.getUriForFile(this, "com.example.mealsnap_prototype_2.fileprovider", photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, REQUEST_CODE);
        } else{
            Toast.makeText(this, "cant open cam", Toast.LENGTH_SHORT).show();
        }
    }

    private File getPhotoFile(String fileName) throws IOException {
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(fileName, ".jpg", storageDirectory);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            //Bitmap takenImage = (Bitmap) data.getExtras().get("data");
            Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
            ivPreviewPostImage.setImageBitmap(takenImage);
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}