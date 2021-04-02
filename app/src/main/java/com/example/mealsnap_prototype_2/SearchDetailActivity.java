package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}