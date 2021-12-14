package com.damico958.cloud_native.tema06;

public class Division implements Operation {
    private String historyMessage;

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        if (secondNumber == 0) {
            throw new IllegalArgumentException("Fail! Can't divide a number by zero!");
        }
        double result = firstNumber / secondNumber;
        this.historyMessage = "Division action selected! " + firstNumber + " / " + secondNumber + " = " + result;
        return result;
    }

    @Override
    public String toString() {
        return historyMessage;
    }
}
