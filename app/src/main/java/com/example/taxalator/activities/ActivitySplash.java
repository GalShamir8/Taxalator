package com.example.taxalator.activities;

import static com.example.taxalator.common.Constants.NO_ADDS;
import static com.example.taxalator.common.Constants.SUBSCRIBE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taxalator.R;
import com.example.taxalator.common.SharedPreferenceHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ActivitySplash extends AppCompatActivity {
    private MaterialButton splash_BTN_getStarted;
    private ImageView splash_ING_ilus;
    private ImageView splash_IMG_icon;
    private ImageView bar_settings;
    private ImageView bar_back;
    private SharedPreferenceHelper sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = new SharedPreferenceHelper(this);
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
        // Create a ListView for the settings options
        ListView listView = new ListView(this);

        // Create an array of strings for the options
        String[] options = {"Privacy policy", "Terms of use", "Subscribe", "Buy no ads option"};

        // Create an ArrayAdapter to display the options in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        // Set a click listener on the ListView to respond to user selection
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showPolicy();
                        break;
                    case 1:
                        showTerms();
                        break;
                    case 2:
                        subscribe();
                        break;
                    case 3:
                        subscribeNoAds();
                        break;
                }
            }
        });

        // Create a new AlertDialog to display the ListView
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Settings")
                .setView(listView)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }

    private void subscribeNoAds() {
        sp.getBooleanKey(NO_ADDS, true);
    }

    private void subscribe() {
        sp.getBooleanKey(SUBSCRIBE, true);
    }

    private void showTerms() {
        openHtmlTextDialog("terms_of_use.html");
    }

    private void showPolicy() {
        openHtmlTextDialog("privacy_policy.html");
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


    private void openNewPage() {
        Intent baseActivity = new Intent(this, BaseActivity.class);
        Bundle data = new Bundle();
        boolean value = sp.getBooleanKey(NO_ADDS, false) || sp.getBooleanKey(SUBSCRIBE, false);
        data.putBoolean(NO_ADDS, value);
        baseActivity.putExtras(data);
        startActivity(baseActivity);
    }
}