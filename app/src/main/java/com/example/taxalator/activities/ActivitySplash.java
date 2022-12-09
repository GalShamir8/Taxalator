package com.example.taxalator.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;

import com.example.taxalator.R;
import com.google.android.material.button.MaterialButton;

public class ActivitySplash extends AppCompatActivity {
    private MaterialButton splash_BTN_getStarted;
    private ImageView splash_ING_ilus;
    private ImageView splash_IMG_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
    }

    private void initViews() {
        splash_BTN_getStarted = findViewById(R.id.splash_BTN_getStarted);
        splash_ING_ilus =  findViewById(R.id.splash_ING_ilus);
        splash_IMG_icon =  findViewById(R.id.splash_IMG_icon);
        setListeners();
        setResources();
    }

    private void setResources() {
        splash_ING_ilus.setImageResource(R.drawable.app_ilustration);
        splash_IMG_icon.setImageResource(R.drawable.taxalator_icon);
    }

    private void setListeners() {
        splash_BTN_getStarted.setOnClickListener(e -> openNewPage());
    }

    private void openNewPage() {
        startActivity(new Intent(this, BaseActivity.class));
    }
}