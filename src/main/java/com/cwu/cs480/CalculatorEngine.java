package com.cwu.cs480;

import java.util.*;


/**
 * Contains logic for mathematical expression parsing and evaluation.
 */
public class CalculatorEngine {

    //private String expression;
    private Stack<String> RPNStack;
    private Stack<String> opStack;
    private Queue<String> outputQueue;

    private int errorCode;
    private static final Map<Integer, String> ERROR_MESSAGES = Map.of(
            0, "",
            100, "Division by zero.",
            101, "Missing operator",
            102, "Unrecognized token.",
            103, "Missing parenthesis",

            400, "Invalid argument",
            900, "Internal overflow",
            901, "Internal underflow"
    );

    /**
     * Parameterized constructor.
     //* @param expression
     */
    CalculatorEngine() {
        //this.expression = expression;

    }

    /**
     * Convert expression from infix to postfix (RPN) while performing a syntax check. If successful, tokenized
     * result is stored in RPNStack.
     * @return true if successful, false if error occurred.
     */
    protected boolean parseToRPN(String expression) {
        // stub
        expression.replace(" ", "")
        .replace("(-", "(0-");  // handle unary minus signs
        return true;
    }

    /**
     * Evaluate the RPN expression and return the result.
     * @return true if successful, false if error occurred.
     */
    protected boolean evaluate() {
        // stub
        return true;
    }

    /* GETTERS */

    public Stack<String> getRPNStack() {
        return RPNStack;
    }

    public Stack<String> getOpStack() {
        return opStack;
    }

    public Queue<String> getOutputQueue() {
        return outputQueue;
    }

} // CalculatorEngine
