package com.cwu.cs480;

import java.util.*;


/**
 * Contains logic for mathematical expression parsing and evaluation.
 */
public class CalculatorEngine {

    private String expression;
    private Stack<String> opStack;
    private Queue<String> outQueue;

    private int errorCode;
    private static final Map<Integer, String> ERROR_MESSAGES = createErrMsgMap();

    /**
     * Static initializer method map of error code & error message pairs.
     * @return a constant map of error messages.
     */
    private static Map<Integer, String> createErrMsgMap() {
        Map<Integer, String> errMsgMap = new HashMap<>();
        errMsgMap.put(0, "");       // No error
        errMsgMap.put(100, "Divide by zero.");
        // stub
        return Collections.unmodifiableMap(errMsgMap);
    }

    /**
     * Parameterized constructor.
     * @param expression
     */
    CalculatorEngine(String expression) {
        this.expression = expression;

    }

    /**
     *
     * @return
     */
    private boolean parse() {
        // stub
        return true;
    }

    /**
     *
     * @return
     */
    private double evaluate() {
        // stub
        return 0;
    }



}
