package com.example.taxalator.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.taxalator.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;
import java.io.InputStream;

public class SettingsActivity extends AppCompatActivity {
    private MaterialTextView settings_LBL_policy;
    private MaterialTextView settings_LBL_terms;
    private MaterialTextView settings_LBL_subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        setListeners();
    }

    private void setListeners() {
        settings_LBL_policy.setOnClickListener(e -> showPolicy());
        settings_LBL_terms.setOnClickListener(e -> showTerms());
        settings_LBL_subscription.setOnClickListener(e -> subscribeUser());
    }

    private void subscribeUser() {
    }

    private void showTerms() {
        openHtmlTextDialog("terms_of_use.html");
    }

    private void showPolicy() {
        openHtmlTextDialog("privacy_policy.html");
    }

    private void initViews() {
        settings_LBL_policy = findViewById(R.id.settings_LBL_policy);
        settings_LBL_terms = findViewById(R.id.settings_LBL_terms);
        settings_LBL_subscription = findViewById(R.id.settings_LBL_subscription);
    }

    public void openHtmlTextDialog(String fileNameInAssets) {
        String str = "";
        InputStream is = null;

        try {
            is = getAssets().open(fileNameInAssets);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            str = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setPositiveButton("Close", null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            materialAlertDialogBuilder.setMessage(Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY));
        } else {
            materialAlertDialogBuilder.setMessage(Html.fromHtml(str));
        }

        AlertDialog al = materialAlertDialogBuilder.show();
        TextView alertTextView = al.findViewById(android.R.id.message);
        alertTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}