package com.example.mealsnap_prototype_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    TextView tvConfirm;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        tvConfirm = findViewById(R.id.tvConfirm);
        btnConfirm = findViewById(R.id.btnConfirm);

        tvConfirm.setText("Please check your email for a confirmation link");

        btnConfirm.setOnClickListener(v -> goToLoginActivityi());
    }

    private void goToLoginActivityi() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}