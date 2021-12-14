package com.damico958.cloud_native.tema06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.damico958.cloud_native.tema06")
public class CalculatorConfig {

    @Bean
    public CalculatorService calculatorService() {
        return new CalculatorService();
    }

    @Bean
    public Operation addition() {
        return new Addition();
    }

    @Bean
    public Operation subtraction() {
        return new Subtraction();
    }

    @Bean
    public Operation multiplication() {
        return new Multiplication();
    }

    @Bean
    public Operation division() {
        return new Division();
    }

    @Bean Operation exponentiation() {
        return new Exponentiation();
    }
}