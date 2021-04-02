package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}