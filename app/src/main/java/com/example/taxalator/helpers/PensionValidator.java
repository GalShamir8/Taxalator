package com.example.taxalator.helpers;

public class PensionValidator implements InputValidator{

    public PensionValidator() { }

    @Override
    public boolean validate(double value) {
        return value > 0 && value < 100;
    }
}
