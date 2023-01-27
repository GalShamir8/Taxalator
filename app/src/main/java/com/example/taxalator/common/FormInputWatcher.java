package com.example.taxalator.common;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;

public class FormInputWatcher implements TextWatcher {
    private final TextInputEditText instance;
    private InputEntries key;
    private Callable updateValidInput;

    public FormInputWatcher(TextInputEditText instance) {
        this.instance = instance;
    }

    public TextInputEditText getInstance() {
        return instance;
    }

    public InputEntries getKey() {
        return key;
    }

    public void setKey(InputEntries key) {
        this.key = key;
    }

    public Callable getUpdateValidInput() {
        return updateValidInput;
    }

    public void setUpdateValidInput(Callable updateValidInput) {
        this.updateValidInput = updateValidInput;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() < 1) {
            instance.setError("Can't be empty!");
            updateValidInput.call(key, true);
        } else {
            instance.setError(null);
            updateValidInput.call(key, false);
        }

    }

}
