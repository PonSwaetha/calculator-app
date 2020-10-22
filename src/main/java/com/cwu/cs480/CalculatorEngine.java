package com.cwu.cs480;

// from exp4j library
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.operator.Operators;
import net.objecthunter.exp4j.shuntingyard.ShuntingYard;
import net.objecthunter.exp4j.tokenizer.*;

import java.text.DecimalFormat;
import java.util.*;

import static com.cwu.cs480.MyToken.*;

/**
 * Contains logic for mathematical expression parsing and evaluation.
 */
public class CalculatorEngine {

    private Stack<Double> outputStack;
    private Token[] RPN;

    private static final String REGEX_TOKENIZE_KEEP_DELIMS =
        "((?<=[+\\-*/^()]|(sin\\[)|(cos\\[)|(tan\\[)|(cot\\[)|(ln\\[)|(log\\[))|(?=[+\\-*/^()]|(sin\\[)|(cos\\[)|(tan\\[)|(cot\\[)|(ln\\[)|(log\\[)))";

    /**
     * Default constructor.
     */
    CalculatorEngine() {
        outputStack = new Stack<>();
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
        expression = expression.replace("log(", "log10(")
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

        // using empty map is another way to set the default operators in exp4j
        Map<String, Operator> defaultOperators = new HashMap<>();
        /*
        operators.put("+", Operators.getBuiltinOperator('+', 2));
        operators.put("-", Operators.getBuiltinOperator('-', 2));
        operators.put("*", Operators.getBuiltinOperator('*', 2));
        operators.put("/", Operators.getBuiltinOperator('/', 2));
        operators.put("-", Operators.getBuiltinOperator('-', 1));
        operators.put("^", Operators.getBuiltinOperator('^', 2));
         */

        RPN = ShuntingYard.convertToRPN(expression, functions, defaultOperators, new HashSet<String>(), false);

        return true;
    }

    /**
     * Validate that an expression contains no invalid characters and all parentheses are matched.
     * @param expr expression being validated.
     * @return {@code true} if the expression is valid, false otherwise.
     */
    protected boolean validateExpression(String expr) {

        if (expr.isEmpty()) {
            return false;
        }

        String expression = expr.replace("sin(", "sin[")        // use alternate braces to differentiate
                .replace("cos(", "cos[")        // between regular grouping operators and function calls
                .replace("tan(", "tan[")
                .replace("cot(", "cot[")
                .replace("log(", "log[")
                .replace("ln(", "ln[");
        if (expression.charAt(expression.length() - 1) == '=') {
            expression = expression.substring(0, expression.length() - 1);  // remove equal sign if necessary
            if (expression.isEmpty()) {
                return false;
            }
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
            } else if (parsedType == Type.UNARY_OPERATOR && !strToken.equals("-")) {    // functions count as an open parenthesis
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
     * @return the result of the operation as a String, or {@code null} if evaluation failed.
     */
    protected String evaluate() {
        outputStack.clear();    // reset output stack
        boolean error = false;

        for (Token token : RPN) {
            /* If we encounter an error, exit for loop. */
            if (error) {
                break;
            }
            switch (token.getType()) {
                case Token.TOKEN_NUMBER: {      // push operands to output stack
                    NumberToken numToken = (NumberToken) token;
                    outputStack.push(numToken.getValue());
                    break;
                }
                case Token.TOKEN_OPERATOR: {
                    OperatorToken opToken = (OperatorToken) token;
                    int numOperands = opToken.getOperator().getNumOperands();

                    /* Check if there are enough operands on the stack to perform the operation */
                    if (outputStack.size() < numOperands) {
                        System.out.println("[Error] Not enough operands for operator: " + opToken.getOperator().getSymbol());
                        error = true;
                        break;
                    }
                    double rightOperand = outputStack.pop();
                    double leftOperand = (numOperands == 2) ? outputStack.pop() : 0;
                    double result = 0;

                    if ("+".equals(opToken.getOperator().getSymbol())) {
                        result = leftOperand + rightOperand;
                    }
                    else if ("-".equals(opToken.getOperator().getSymbol())) {
                        if (numOperands == 1) {
                            result = -rightOperand;
                        } else {
                            result = leftOperand - rightOperand;
                        }
                    }
                    else if ("*".equals(opToken.getOperator().getSymbol())) {
                        result = leftOperand * rightOperand;
                    }
                    else if ("/".equals(opToken.getOperator().getSymbol())) {
                        if (rightOperand == 0) {
                            System.out.println("[Error] Division by zero.");
                            error = true;
                            break;
                        }
                        result = leftOperand / rightOperand;
                    }
                    else if ("^".equals(opToken.getOperator().getSymbol())) {
                        result = Math.pow(leftOperand, rightOperand);
                    }
                    else {
                        /* This error should never happen. */
                        System.out.println("[Error] Invalid operator: " + opToken.getOperator().getSymbol());
                        error = true;
                        break;
                    }
                    outputStack.push(result);
                    break;
                }
                case Token.TOKEN_FUNCTION: {
                    FunctionToken funToken = (FunctionToken) token;

                    if (outputStack.empty()) {
                        System.out.println("[Error] Not enough operands for function: " + funToken.getFunction().getName());
                        error = true;
                        break;
                    }
                    double operand = outputStack.pop();
                    double result = 0.0;

                    if ("sin".equals(funToken.getFunction().getName())) {
                        result = Math.sin(Math.toRadians(operand));
                    }
                    else if ("cos".equals(funToken.getFunction().getName())) {
                        result = Math.cos(Math.toRadians(operand));
                    }
                    else if ("tan".equals(funToken.getFunction().getName())) {
                        /* tan(x) = sin(x)/cos(x), so tan(x) is undefined when cos(x) = 0 (which is at x=90,270). */
                        if (areEqual(operand, 90) || areEqual(operand, 270)) {
                            System.out.println("[Error] Domain violation in function: " + funToken.getFunction().getName());
                            error = true;
                            break;
                        }
                        result = Math.tan(Math.toRadians(operand));
                    }
                    else if ("cot".equals(funToken.getFunction().getName())) {
                        /* cot(x) = 1/tan(x), so cot(x) is undefined when tan(x) = 0 (which is at x=0). */
                        if (areEqual(operand, 0)) {
                            System.out.println("[Error] Domain violation in function: " + funToken.getFunction().getName());
                            error = true;
                            break;
                        }
                        result = 1 / Math.tan(Math.toRadians(operand));
                    }
                    else if ("log".equals(funToken.getFunction().getName())) {
                        /* ln(x) is undefined when x <= 0. */
                        if (operand < 0 || areEqual(operand, 0)) {
                            System.out.println("[Error] Domain violation in function: " + funToken.getFunction().getName());
                            error = true;
                            break;
                        }
                        result = Math.log(operand);
                    }
                    else if ("log10".equals(funToken.getFunction().getName())) {
                        /* ln(x) is undefined when x <= 0. */
                        if (operand < 0 || areEqual(operand, 0)) {
                            System.out.println("[Error] Domain violation in function: " + funToken.getFunction().getName());
                            error = true;
                            break;
                        }
                        result = Math.log10(operand);
                    }
                    else {
                        /* This error should never happen. */
                        System.out.println("[Error] Invalid function: " + funToken.getFunction().getName());
                        error = true;
                        break;
                    }
                    outputStack.push(result);
                    break;
                }
            } // switch
        } // for

        if (error) {
            return null;
        } else if (outputStack.size() != 1) {
            System.out.println("[Error] Evaluation failed: check that all functions/operators have the correct number of operands.");
            return null;
        } else {    // SUCCESS
            return new DecimalFormat("##########0.#####").format(outputStack.pop());
        }
    }

    /**
     * Simple utility method to determine if two doubles are equal.
     * @param a operand 1.
     * @param b operand 2.
     * @return {@code true} if they are essentially equal (within a certain threshold).
     */
    private static boolean areEqual(double a, double b) {
        double threshold = 0.000001;
        return (Math.abs(a - b) <= threshold);
    }

    /* GETTERS */
    public Token[] getRPN() {
        return RPN;
    }
    public String getRPNString() {
        if (RPN == null) {
            return "";
        }
        String strRPN = "";
        for (Token tok : RPN) {
            if (tok instanceof NumberToken) {
                NumberToken numTok = (NumberToken) tok;
                strRPN += new DecimalFormat("##########0.#####").format(numTok.getValue());
            } else if (tok instanceof OperatorToken) {
                OperatorToken opTok = (OperatorToken) tok;
                strRPN += opTok.getOperator().getSymbol();
            } else if (tok instanceof FunctionToken) {
                FunctionToken funTok = (FunctionToken) tok;
                strRPN += funTok.getFunction().getName();
            }
            strRPN += " ";
        }
        return strRPN.substring(0, strRPN.length() - 1);
    }

    public Stack<Double> getOutputStack() {
        return outputStack;
    }

} // CalculatorEngine
