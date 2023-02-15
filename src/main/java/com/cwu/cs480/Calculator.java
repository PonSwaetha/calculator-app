package com.cwu.cs480;

import java.util.Scanner;
/**
 * Calculator application. Serves as an interface to CalculatorEngine. <br>
 * Manages input/output of mathematical expressions and their results.
 */
public class Calculator {

    private static final String WELCOME_MSG =
            "Welcome to Eli's Text-based Calculator v1.0\n" +
                    "Supported operations: +, -, *, /, ^, sin(), cos(), tan(), cot(), ln(), log().\n" +
                    "Type \"EXIT\" to exit the app.";

    private static final String EXIT_CMD = "EXIT";
    private CalculatorEngine engine;

    Calculator() {
        System.out.println("hello");
        engine = new CalculatorEngine();
    }

    /**
     * Execute the user's expression.
     * @param expression the expression being evaluated.
     */
    public void calculate(String expression) {

        if (!engine.parseToRPN(expression)) {
            return;
        }

        String result = engine.evaluate();
        if (result != null) {
            System.out.println(result);
        }
    }

    /**
     * Prompt user to enter a mathematical expression via console input.
     * @return user's input
     */
    private String getInput() {
        String input = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.print(">> ");
        input = keyboard.nextLine();
        return input.stripLeading().stripTrailing();    // remove leading/trailing whitespace
    }


    /**
     * Entry point for the Calculator application.
     * @param args Calculator command-line arguments.
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // if user entered expression via command-line argument
        if (args.length == 1) {
            calculator.calculate(args[0].replace(" ", ""));
            return;
        } else if (args.length > 1) {
            System.out.println("[Error] Too many command-line arguments. Exiting...");
            return;
        }

        System.out.println(WELCOME_MSG);

        // loop until user enters the exit command
        for (String expression = calculator.getInput(); expression.compareToIgnoreCase(EXIT_CMD) != 0;
             expression = calculator.getInput()) {

            calculator.calculate(expression.replace(" ", ""));
        }
    }

} // Calculator
