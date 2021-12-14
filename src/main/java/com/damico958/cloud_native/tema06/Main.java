package com.damico958.cloud_native.tema06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_RED_BOLD = "\033[1;31m";
    public static final String ANSI_GREEN_BOLD = "\033[1;32m";
    public static final String ANSI_YELLOW_BOLD = "\033[1;33m";
    public static final String ANSI_BLUE_BOLD = "\033[1;34m";
    public static final String ANSI_PURPLE_BOLD = "\033[1;35m";
    public static final String ANSI_CYAN_BOLD = "\033[1;36m";


    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CalculatorConfig.class);

        Operation addition = (Operation) applicationContext.getBean("addition");
        Operation subtraction = (Operation) applicationContext.getBean("subtraction");
        Operation multiplication = (Operation) applicationContext.getBean("multiplication");
        Operation division = (Operation) applicationContext.getBean("division");
        Operation exponentiation = (Operation) applicationContext.getBean("exponentiation");

        CalculatorService calculatorService = (CalculatorService) applicationContext.getBean("calculatorService");

        double firstArgument = 6;
        double secondArgument = 3;

        double addedResult = calculatorService.execute(addition, firstArgument, secondArgument);
        System.out.println(ANSI_BLUE_BOLD + "Operation Result: " + addedResult);
        calculatorService.printHistory();

        double subtractedResult = calculatorService.execute(subtraction, firstArgument, secondArgument);
        System.out.println(ANSI_PURPLE_BOLD + "Operation Result: " + subtractedResult);
        calculatorService.printHistory();

        double multipliedResult = calculatorService.execute(multiplication, firstArgument, secondArgument);
        System.out.println(ANSI_GREEN_BOLD + "Operation Result: " + multipliedResult);
        calculatorService.printHistory();

        double dividedResult = calculatorService.execute(division, firstArgument, secondArgument);
        System.out.println(ANSI_YELLOW_BOLD + "Operation Result: " + dividedResult);
        calculatorService.printHistory();

        double poweredResult = calculatorService.execute(exponentiation, firstArgument, secondArgument);
        System.out.println(ANSI_CYAN_BOLD + "Operation Result: " + poweredResult);
        calculatorService.printHistory();

        System.out.println(ANSI_RESET);

    }
}