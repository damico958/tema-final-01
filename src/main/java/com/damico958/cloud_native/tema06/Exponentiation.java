package com.damico958.cloud_native.tema06;

import static java.lang.Math.pow;

public class Exponentiation implements Operation {
    private String historyMessage;

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        double result = pow(firstNumber,secondNumber);
        this.historyMessage = "Exponentiation action selected! " + firstNumber + " ^ " + secondNumber + " = " + result;
        return result;
    }

    @Override
    public String toString() {
        return historyMessage;
    }
}
