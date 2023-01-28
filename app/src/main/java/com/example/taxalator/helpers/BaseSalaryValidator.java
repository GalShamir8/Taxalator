package com.example.taxalator.helpers;

public class BaseSalaryValidator implements InputValidator{

    public BaseSalaryValidator() { }

    @Override
    public boolean validate(double value) {
        return value > 0;
    }
}
