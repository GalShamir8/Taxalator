package com.example.taxalator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.taxalator.R;

public class AppBar extends AppCompatActivity {
    private ImageView bar_settings;
    private ImageView bar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);
        initViews();

    }

    private void initViews() {
        bar_settings = findViewById(R.id.bar_settings);
        bar_back = findViewById(R.id.bar_back);
        setResources();
        setListeners();
    }

    private void setResources() {
        bar_settings.setImageResource(R.drawable.ic_setting);
        bar_back.setImageResource(R.drawable.ic_undo);
    }

    private void setListeners() {
        bar_settings.setOnClickListener(e -> settingsAction());
        bar_back.setOnClickListener(e -> backAction());
    }

    private void backAction() {
    }

    private void settingsAction() {
    }
}