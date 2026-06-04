package com.example.molecularmass;

public class CalculationResult {
    private boolean success;
    private String message;
    private double mass;

    public CalculationResult(boolean success, String message, double mass) {
        this.success = success;
        this.message = message;
        this.mass = mass;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public double getMass() {
        return mass;
    }
}