package com.damico958.cloud_native.tema06;

public class Subtraction implements Operation {
    private String historyMessage;

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        double result = firstNumber - secondNumber;
        this.historyMessage = "Subtraction action selected! " + firstNumber + " - " + secondNumber + " = " + result;
        return result;
    }

    @Override
    public String toString() {
        return historyMessage;
    }
}
