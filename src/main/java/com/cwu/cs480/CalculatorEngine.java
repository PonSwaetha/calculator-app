package com.cwu.cs480;

// from exp4j library
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.operator.Operators;
import net.objecthunter.exp4j.shuntingyard.ShuntingYard;
import net.objecthunter.exp4j.tokenizer.FunctionToken;
import net.objecthunter.exp4j.tokenizer.NumberToken;
import net.objecthunter.exp4j.tokenizer.OperatorToken;
import net.objecthunter.exp4j.tokenizer.Token;

import java.text.ParseException;
import java.util.*;

import static com.cwu.cs480.MyToken.*;

/**
 * Contains logic for mathematical expression parsing and evaluation.
 */
public class CalculatorEngine {

    private Stack<MyToken> opStack;
    private Queue<MyToken> outputQueue;
    private Token[] RPN;

    private static final Map<Integer, String> ERROR_MESSAGES = Map.of(
            0, "",
            100, "Division by zero",
            101, "Missing operator",
            102, "Unrecognized token",
            103, "Missing parenthesis",

            400, "Invalid argument",
            900, "Internal overflow",
            901, "Internal underflow"
    );

    private static final String REGEX_TOKENIZE_KEEP_DELIMS =
        "((?<=[+\\-*/()]|(sin\\[)|(cos\\[)|(tan\\[)|(cot\\[)|(ln\\[)|(log\\[))|(?=[+\\-*/()]|(sin\\[)|(cos\\[)|(tan\\[)|(cot\\[)|(ln\\[)|(log\\[)))";

    /**
     * Default constructor.
     */
    CalculatorEngine() {
        opStack = new Stack<>();
        outputQueue = new LinkedList<>();
        RPN = null;
    }

    /**
     * Convert expression from infix to postfix (RPN) while performing a syntax check. If successful, tokenized
     * result is stored in RPNStack.
     * @param expression the expression being parsed.
     * @return {@code true} if expression was parsed successfully.
     */
    protected boolean parseToRPN(String expression) {

        // validate the expression before converting to postfix
        if (!validateExpression(expression)) {
            return false;
        }

        // make a few adjustments for the RPN converter
        expression = expression.replace(" ", "")
                .replace("log(", "log10(")
                .replace("ln(", "log(");
        if (expression.charAt(expression.length() - 1) == '=') {
            expression = expression.substring(0, expression.length() - 1);  // remove equal sign if necessary
        }

        // Using a third-party shunting-yard algorithm because mine became a huge mess
        Map<String, Function> functions = new HashMap<>(6);
        functions.put("sin", Functions.getBuiltinFunction("sin"));
        functions.put("cos", Functions.getBuiltinFunction("cos"));
        functions.put("tan", Functions.getBuiltinFunction("tan"));
        functions.put("cot", Functions.getBuiltinFunction("cot"));
        functions.put("log", Functions.getBuiltinFunction("log"));
        functions.put("log10", Functions.getBuiltinFunction("log10"));

        Map<String, Operator> operators = new HashMap<>(6);
        operators.put("+", Operators.getBuiltinOperator('+', 2));
        operators.put("-", Operators.getBuiltinOperator('-', 2));
        operators.put("*", Operators.getBuiltinOperator('*', 2));
        operators.put("/", Operators.getBuiltinOperator('/', 2));
        operators.put("-", Operators.getBuiltinOperator('-', 1));
        operators.put("^", Operators.getBuiltinOperator('^', 2));

        RPN = ShuntingYard.convertToRPN(expression, functions, operators, new HashSet<String>(), true);
        
        return true;
    }

    /**
     * Validate that an expression contains no invalid characters and all parentheses are matched.
     * @param expr expression being validated.
     * @return {@code true} if the expression is valid, false otherwise.
     */
    protected boolean validateExpression(String expr) {
        String expression = expr.replace(" ", "")         // remove spaces
                .replace("sin(", "sin[")        // use alternate braces to verify differentiate
                .replace("cos(", "cos[")        // between regular grouping operators and function calls
                .replace("tan(", "tan[")
                .replace("cot(", "cot[")
                .replace("log(", "log[")
                .replace("ln(", "ln[");
        if (expression.charAt(expression.length() - 1) == '=') {
            expression = expression.substring(0, expression.length() - 1);  // remove equal sign if necessary
        }

        // tokenize string
        String[] strTokens =  expression.split(REGEX_TOKENIZE_KEEP_DELIMS);

        int parenthesisBalance = 0;

        for (String strToken : strTokens) {
            // validate string token
            Type parsedType = parseToken(strToken);

            if (parsedType == null) {       // handle invalid tokens here
                System.out.println("[Error] Unrecognized token: " + strToken);
                return false;
            } else if (parsedType == Type.GROUPING_OPERATOR) {      // parenthesis
                if (strToken.equals( "(" )) {
                    parenthesisBalance++;
                } else {
                    parenthesisBalance--;       // note: this includes function closing parentheses
                }
            } else if (parsedType == Type.UNARY_OPERATOR && !strToken.equals("-")) {    // functions
                parenthesisBalance++;
            }
        }

        if (parenthesisBalance != 0) {
            System.out.println("[Error] Mismatched parenthesis.");
            return false;
        }
        return true;
    }

    /**
     * Evaluate the RPN expression and return the result.
     */
    protected String evaluate() {

        for (Token t : RPN) {



        }


        return null;
    }

    /* GETTERS */
    public Token[] getRPN() {
        return RPN;
    }

    public Stack<MyToken> getOpStack() {
        return opStack;
    }

    public Queue<MyToken> getOutputQueue() {
        return outputQueue;
    }

} // CalculatorEngine
