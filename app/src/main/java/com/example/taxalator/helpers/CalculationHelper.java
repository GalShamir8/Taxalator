package com.example.taxalator.helpers;

public class CalculationHelper {
    private double baseSalary;
    private double pension;
    private double creditPoints;

    public CalculationHelper(double baseSalary, double pension, double creditPoints) {
        this.baseSalary = baseSalary * 12;
        if (baseSalary == 0){
            this.baseSalary = 1;
        }
        this.pension = pension;
        if (pension == 0){
            this.pension = 1;
        }
        this.creditPoints = creditPoints;
        if (creditPoints == 0){
            this.creditPoints = 1;
        }
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
        double pensionFactor = calculatePension();
        double creditPointsFactor = calculateCreditPoints();
        double tax = calculateTax();
        double oneMonthSalary = baseSalary / 12;
        return oneMonthSalary - (pensionFactor + creditPointsFactor + tax);
    }

    private double calculateTax() {
        double tax = 0;
        switch(taxLevel()){
            case 1:
                tax = 0.1;
                break;
            case 2:
                tax = 0.14;
                break;
            case 3:
                tax = 0.2;
                break;
            case 4:
                tax = 0.31;
                break;
            case 5:
                tax = 0.35;
                break;
            case 6:
                tax = 0.47;
                break;
            case 7:
                tax = 0.5;
                break;
        }
        return (baseSalary * tax) / 12;
    }

    private int taxLevel() {
        int firstLevel = 81_480;
        int secondLevel = 116_760;
        int thirdLevel = 187_440;
        int fourthLevel = 260_520;
        int fifthLevel = 542_160;
        int sixthLevel = 698_280;
        if (baseSalary <= firstLevel)
            return 1;
        if (baseSalary > firstLevel && baseSalary <= secondLevel)
            return 2;
        if (baseSalary > secondLevel && baseSalary <= thirdLevel)
            return 3;
        if (baseSalary > thirdLevel && baseSalary <= fourthLevel)
            return 4;
        if (baseSalary > fourthLevel && baseSalary <= fifthLevel)
            return 5;
        if (baseSalary > fifthLevel && baseSalary <= sixthLevel)
            return 6;
        if (baseSalary > sixthLevel)
            return 7;
        return 1;
    }

    private double calculateCreditPoints() {
        return -(creditPoints * 223);
    }

    private double calculatePension() {
        return ((pension / 100) * baseSalary) / 12;
    }
}

