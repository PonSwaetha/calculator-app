package com.cwu.cs480;

/**
 * Calculator application. Serves as an interface to CalculatorEngine. <br>
 * Manages input/output of mathematical expressions and their results.
 */
public class Calculator {


    private static final String EXIT_COMMAND = "EXIT";
    private CalculatorEngine engine;

    /**
     * Prompt user to enter a mathematical expression via console input.
     * @return user's input
     */
    private String getInput() {
        String input = "";
        // stub
        return input.replace(" ", "");  // remove spaces from input
    }

    /**
     * Execute the user's expression.
     * @param expression the expression being evaluated.
     * @return the result of the calculation.
     */
    public double calculate(String expression) {
        // stub
        engine = new CalculatorEngine(expression);
        return 0;
    }

    /**
     * Prints results of calculation (or error message if it failed).
     */
    public void printResult() {
        //stub
    }

    /**
     * Entry point for the Calculator application.
     * @param args Calculator command-line arguments.
     */
    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        for (String expression = calculator.getInput(); expression.compareToIgnoreCase(EXIT_COMMAND) != 0;
             expression = calculator.getInput()) {

            calculator.calculate(expression);
            calculator.printResult();

            expression = calculator.getInput();     //
        }
    }

}
