package com.example.taxalator.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.taxalator.R;
import com.example.taxalator.common.InputEntries;
import com.example.taxalator.fragments.FormFragment;
import com.example.taxalator.helpers.CalculationHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textview.MaterialTextView;

import java.util.Map;


public class BaseActivity extends AppCompatActivity {
    private AdView adView;
    private MaterialTextView base_LBL_result;
    private FormFragment formFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }

    private void initView() {
        base_LBL_result = findViewById(R.id.base_LBL_result);
        openForm();
        addAppAdds();
    }

    private void addAppAdds() {
        adView = new AdView(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void openForm() {
        formFragment = new FormFragment();
        formFragment.setOnFinish(this::calculationSubmittedAction);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.base_form, formFragment);
        transaction.commit();
    }

    private void calculationSubmittedAction(Object[] params) {
        Map<InputEntries, Double> data = (Map<InputEntries, Double>) params[0];
        double baseSalary = data.get(InputEntries.BASE_SALARY);
        double pension = data.get(InputEntries.PENSION);
        double creditPoints = data.get(InputEntries.CREDIT_POINTS);
        setCalculationResult(new CalculationHelper(baseSalary, pension, creditPoints).calculate());
    }

    private void setCalculationResult(double result){
        base_LBL_result.setText("" + result);
    }

}