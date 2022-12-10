package com.example.taxalator.common;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;

public class FormInputWatcher implements TextWatcher {
    private final TextInputEditText instance;

    public FormInputWatcher(TextInputEditText instance) {
        this.instance = instance;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() < 1)
            instance.setError("Can't be empty!");
        else
            instance.setError(null);
    }
}
