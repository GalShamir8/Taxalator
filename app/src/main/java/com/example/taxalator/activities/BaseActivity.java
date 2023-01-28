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
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Map;


public class BaseActivity extends AppCompatActivity {
    private AdView adView;
    private FormFragment formFragment;


    // full screen
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }

    private void initView() {
        openForm();
        addAppAdds();
        appBannerAd();
        appFullscreenAd();
    }

    private void addAppAdds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });
    }

    private void appBannerAd() {
        adView = new AdView(this);
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void appFullscreenAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }

    private void openForm() {
        formFragment = new FormFragment();
        formFragment.setOnFinish(this::calculationSubmittedAction);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.base_form, formFragment);
        transaction.commit();
    }

    private void calculationSubmittedAction(Object[] params) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        }
        Map<InputEntries, Double> data = (Map<InputEntries, Double>) params[0];
        double baseSalary = data.get(InputEntries.BASE_SALARY);
        double pension = data.get(InputEntries.PENSION);
        double creditPoints = data.get(InputEntries.CREDIT_POINTS);
        setCalculationResult(new CalculationHelper(baseSalary, pension, creditPoints).calculate());
    }

    private void setCalculationResult(double result){
        formFragment.setResult("" + result);
    }

}