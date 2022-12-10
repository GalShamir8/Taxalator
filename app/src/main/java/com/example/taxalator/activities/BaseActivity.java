package com.example.taxalator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.taxalator.R;
import com.example.taxalator.fragments.FormFragment;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }

    private void initView() {
        FormFragment formFragment = new FormFragment();
        formFragment.setOnFinish(e -> calculationSubmittedAction());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.base_form, formFragment);
        transaction.commit();
    }

    private void calculationSubmittedAction() {
    }

}