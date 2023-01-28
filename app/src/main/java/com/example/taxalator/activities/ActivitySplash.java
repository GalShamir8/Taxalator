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
    private ImageView bar_settings;
    private ImageView bar_back;

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
        bar_settings = findViewById(R.id.bar_settings);
        bar_back = findViewById(R.id.bar_back);
        setListeners();
        setResources();
    }

    private void setResources() {
        splash_ING_ilus.setImageResource(R.drawable.app_ilustration);
        splash_IMG_icon.setImageResource(R.drawable.taxalator_icon);
        bar_settings.setImageResource(R.drawable.ic_setting);
        bar_back.setImageResource(R.drawable.ic_undo);
    }

    private void setListeners() {
        splash_BTN_getStarted.setOnClickListener(e -> openNewPage());
        bar_settings.setOnClickListener(e -> settingsAction());
        bar_back.setOnClickListener(e -> backAction());
    }

    private void backAction() {
        onBackPressed();
    }

    private void settingsAction() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    private void openNewPage() {
        startActivity(new Intent(this, BaseActivity.class));
    }
}