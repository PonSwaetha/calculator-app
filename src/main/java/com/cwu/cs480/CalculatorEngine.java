package com.cwu.cs480;

import java.text.ParseException;
import java.util.*;

/**
 * Contains logic for mathematical expression parsing and evaluation.
 */
public class CalculatorEngine {

    private Stack<Token> opStack;
    private Queue<Token> outputQueue;
    private Stack<Token> RPNStack;


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


    /**
     * Default constructor.
     */
    CalculatorEngine() {

    }

    /**
     * Convert expression from infix to postfix (RPN) while performing a syntax check. If successful, tokenized
     * result is stored in RPNStack.
     * @param expression the expression being parsed.
     */
    protected void parseToRPN(String expression) {
        // stub
        expression.replace(" ", "");    // remove spaces

        //boolean firstToken_local = true;
        // tokenize string
        String strToken = "";
        Token lastToken = new Token("(", Token.Type.GROUPING_OPERATOR);    // for simplicity's sake
        while (true) {
            boolean firstLocalToken = (lastToken.getValue().compareTo("(") == 0);
            Token.Type tokenType = null;

            // parse token
            if (Token.isToken(Token.Type.GROUPING_OPERATOR, strToken)) {
                tokenType = Token.Type.GROUPING_OPERATOR;
            }
            else if (Token.isToken(Token.Type.BINARY_OPERATOR, strToken)) {
                if (strToken.equals("-") && (firstLocalToken || !lastToken.isToken(Token.Type.OPERAND))) {
                    strToken = "_";                         // unary negation symbol
                    tokenType = Token.Type.UNARY_OPERATOR;
                } else {
                    tokenType = Token.Type.BINARY_OPERATOR;
                }
            }
            else if (Token.isToken(Token.Type.UNARY_OPERATOR, strToken)) {
                tokenType = Token.Type.UNARY_OPERATOR;
            }
            else if (Token.isToken(Token.Type.OPERAND, strToken)) {
                tokenType = Token.Type.OPERAND;
            }
            else {
                // error message: unknown token: {strToken}
                // return
            }

            Token token = new Token(strToken, tokenType);

            switch (token.getType()) {
                case GROUPING_OPERATOR: {
                    //todo
                }
                case UNARY_OPERATOR: {      // includes functions
                    //todo
                    break;
                }
                case BINARY_OPERATOR: {
                    while (token.compareTo(opStack.peek()) < 0) {   // top of opStack has greater precedence

                    }
                    break;
                }
                case OPERAND: {
                    //todo
                }
            }

        }

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
    public Stack<Token> getRPNStack() {
        return RPNStack;
    }

    public Stack<Token> getOpStack() {
        return opStack;
    }

    public Queue<Token> getOutputQueue() {
        return outputQueue;
    }

} // CalculatorEngine
