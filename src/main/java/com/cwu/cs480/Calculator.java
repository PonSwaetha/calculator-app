package com.cwu.cs480;

/**
 * Calculator application. Serves as an interface to CalculatorEngine. <br>
 * Manages input/output of mathematical expressions and their results.
 */
public class Calculator {

    private static final String WELCOME_MSG = "stub";
    private static final String EXIT_CMD = "EXIT";
    private CalculatorEngine engine;

    /**
     * Execute the user's expression.
     * @param expression the expression being evaluated.
     * @return the result of the calculation as a String.
     */
    public String calculate(String expression) {
        // stub
        engine.parseToRPN(expression);
        return "";
    }

    /**
     * Prompt user to enter a mathematical expression via console input.
     * @return user's input
     */
    private String getInput() {
        String input = "";
        // stub
        return input;
    }


    /**
     * Entry point for the Calculator application.
     * @param args Calculator command-line arguments.
     */
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        System.out.println(WELCOME_MSG);

        // loop until user enters the exit command
        for (String expression = calculator.getInput(); expression.compareToIgnoreCase(EXIT_CMD) != 0;
             expression = calculator.getInput()) {

            System.out.println(calculator.calculate(expression));
        }
    }

} // Calculator
