package com.example.taxalator.helpers;

public class CalculationHelper {
    private double baseSalary;
    private double pension;
    private double creditPoints;

    public CalculationHelper(double baseSalary, double pension, double creditPoints) {
        this.baseSalary = baseSalary;
        this.pension = pension;
        this.creditPoints = creditPoints;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getPension() {
        return pension;
    }

    public void setPension(double pension) {
        this.pension = pension;
    }

    public double getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(double creditPoints) {
        this.creditPoints = creditPoints;
    }

    public double calculate(){
        return 0;
    }
}
