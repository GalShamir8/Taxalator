package com.example.taxalator.helpers;

public class CreditPointsValidator implements InputValidator{

    public CreditPointsValidator() { }

    @Override
    public boolean validate(double value) {
        return value >= 0 && value < 5;
    }
}
