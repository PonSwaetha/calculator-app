package com.cwu.cs480;

import java.text.ParseException;
import java.util.*;


/**
 * Contains logic for mathematical expression parsing and evaluation.
 */
public class CalculatorEngine {

    //private String expression;
    private Stack<String> RPNStack;
    private Stack<String> opStack;
    private Queue<String> outputQueue;

    /* Supported operations and functions */
    private final String OPERATIONS = "+-*/^";
    private final String NEGATION_ALIAS = "~";  // alias for unary minus sign
    private final String[] FUNCTIONS = {
            "sin",
            "cos",
            "tan",
            "cot",
            "log",
            "ln"
    };
    //private int errorCode;
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

    //private static final String opcodes =

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
     * @param expression the expression being parsed.
     * @throws ParseException
     */
    protected void parseToRPN(String expression) throws ParseException {
        // stub
        expression.replace(" ", "")
        .replace("(-", "(0-");  // handle unary minus signs
        //return true;
    }

    /**
     * Evaluate the RPN expression and return the result.
     * @throws ParseException
     */
    protected void evaluate() throws ParseException {
        // stub
        //return true;
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
