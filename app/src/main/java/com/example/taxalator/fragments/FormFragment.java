package com.example.taxalator.fragments;

import static com.example.taxalator.common.InputEntries.BASE_SALARY;
import static com.example.taxalator.common.InputEntries.CREDIT_POINTS;
import static com.example.taxalator.common.InputEntries.PENSION;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taxalator.R;
import com.example.taxalator.common.Callable;
import com.example.taxalator.common.FormInputWatcher;
import com.example.taxalator.common.InputEntries;
import com.example.taxalator.helpers.BaseSalaryValidator;
import com.example.taxalator.helpers.CreditPointsValidator;
import com.example.taxalator.helpers.PensionValidator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FormFragment extends Fragment {
    private TextInputEditText form_EDT_pension;
    private TextInputEditText form_EDT_credit_points;
    private TextInputEditText form_EDT_base_salary;
    private MaterialTextView form_LBL_result;
    private MaterialButton splash_BTN_calc;
    private Callable onFinish;
    private Map<InputEntries, Boolean> validityMap;

    public FormFragment() { }

    public static FormFragment newInstance() {
        return new FormFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViews(@NotNull View view) {
        form_EDT_pension = view.findViewById(R.id.form_EDT_pension);
        form_EDT_credit_points = view.findViewById(R.id.form_EDT_credit_points);
        form_EDT_base_salary = view.findViewById(R.id.form_EDT_base_salary);
        form_LBL_result = view.findViewById(R.id.form_LBL_result);
        splash_BTN_calc = view.findViewById(R.id.splash_BTN_calc);
        setBTNMode(false);

        setListeners();
    }

    private void setBTNMode(boolean mode) {
        if (mode){
            splash_BTN_calc.setEnabled(true);
            splash_BTN_calc.setBackgroundColor(getContext().getColor(R.color.green_button));
        } else {
            splash_BTN_calc.setEnabled(false);
            splash_BTN_calc.setBackgroundColor(Color.GRAY);
        }
    }

    private void setListeners() {
        splash_BTN_calc.setOnClickListener(e -> sendDate());
        form_EDT_base_salary.addTextChangedListener(buildNewInputWatcher(BASE_SALARY));
        form_EDT_pension.addTextChangedListener(buildNewInputWatcher(PENSION));
        form_EDT_credit_points.addTextChangedListener(buildNewInputWatcher(CREDIT_POINTS));
    }

    private void sendDate() {
        Map<InputEntries, Double> data = new HashMap<>();
        data.put(BASE_SALARY, Double.parseDouble(form_EDT_base_salary.getText().toString()));
        data.put(PENSION, Double.parseDouble(form_EDT_pension.getText().toString()));
        data.put(CREDIT_POINTS, Double.parseDouble(form_EDT_credit_points.getText().toString()));
        onFinish.call(data);
    }

    private TextWatcher buildNewInputWatcher(InputEntries key) {
        FormInputWatcher watcher;
        switch (key){
            case PENSION:
                watcher = new FormInputWatcher(form_EDT_pension);
                watcher.setValidator(new PensionValidator());
                break;
            case CREDIT_POINTS:
                watcher = new FormInputWatcher(form_EDT_credit_points);
                watcher.setValidator(new CreditPointsValidator());
                break;
            default:
                watcher = new FormInputWatcher(form_EDT_base_salary);
                watcher.setValidator(new BaseSalaryValidator());
                break;
        }
        watcher.setUpdateValidInput(this::updateInputValidity);
        watcher.setKey(key);
        return watcher;
    }

    private void updateInputValidity(Object[] params) {
        validityMap.put((InputEntries) params[0], (Boolean) params[1]);
        setBTNMode(validateMap());
    }

    private boolean validateMap() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return validityMap.values().stream().allMatch(valid -> valid);
        }
        return false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_form, container, false);
        initViews(view);
        validityMap = initValidityMap();
        return view;
    }

    private Map<InputEntries, Boolean> initValidityMap() {
        Map<InputEntries, Boolean> map = new HashMap<>();
        map.put(PENSION, false);
        map.put(CREDIT_POINTS, false);
        map.put(BASE_SALARY, false);
        return map;
    }

    public void setOnFinish(Callable onFinish) {
        this.onFinish = onFinish;
    }

    public void setResult(String res){
        form_LBL_result.setText(res);
    }
}