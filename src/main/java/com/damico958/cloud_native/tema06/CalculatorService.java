package com.damico958.cloud_native.tema06;


import java.util.ArrayList;
import java.util.List;

public class CalculatorService {
    private List<Operation> calculatorOperationList = new ArrayList<Operation>();

    CalculatorService() {

    }

    public double execute(Operation operation, double firstNumber, double secondNumber) {
        double result = operation.calculate(firstNumber,secondNumber);
        calculatorOperationList.add(operation);
        return result;
    }

    public List<Operation> getCalculatorHistory() {
        return calculatorOperationList;
    }

    public void printHistory() {
        System.out.println("Calculator History: ");
        for (Operation operation : calculatorOperationList) {
            System.out.println(operation);
        }
    }
}