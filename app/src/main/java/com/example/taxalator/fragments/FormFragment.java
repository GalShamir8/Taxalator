package com.example.taxalator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taxalator.R;
import com.example.taxalator.common.Callable;
import com.example.taxalator.common.FormInputWatcher;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

public class FormFragment extends Fragment {
    private TextInputEditText form_EDT_pension;
    private TextInputEditText form_EDT_credit_points;
    private TextInputEditText form_EDT_base_salary;
    private MaterialButton splash_BTN_calc;
    private Callable onFinish;

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
        splash_BTN_calc = view.findViewById(R.id.splash_BTN_calc);
        setListeners();
    }

    private void setListeners() {
        splash_BTN_calc.setOnClickListener(e -> onFinish.call());
        form_EDT_base_salary.addTextChangedListener(new FormInputWatcher(form_EDT_base_salary));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_form, container, false);
        initViews(view);
        return view;
    }

    public void setOnFinish(Callable onFinish) {
        this.onFinish = onFinish;
    }
}