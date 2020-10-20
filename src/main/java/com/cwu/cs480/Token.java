package com.cwu.cs480;

import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class representing a token within a mathematical expression.
 */
public class Token implements Comparable<Token> {

    enum Type {
        BINARY_OPERATOR,
        UNARY_OPERATOR,
        GROUPING_OPERATOR,
        OPERAND
    }
    /* Supported operations and functions */
    // safe & efficient way to use an enum as an array index
    private static final EnumMap<Type, String[]> OPERATORS = new EnumMap<>(Map.of(
            Type.GROUPING_OPERATOR, new String[] {"(", ")"},
            Type.BINARY_OPERATOR, new String[] {"+", "-", "*", "/"},
            Type.UNARY_OPERATOR, new String[] {"^", "-", "sin", "cos", "tan", "cot", "log", "ln"}
    ));

    //private static final String OPERATORS = "+-*/^";
    private static final String GROUPING_OPERATORS = "()";
    private static final String[] FUNCTIONS = {         // include these with unary operators?
            "sin",
            "cos",
            "tan",
            "cot",
            "log",
            "ln"
    };

    String value;
    Type type;

    /**
     * Parameterized Token constructor.
     * @param value Token value.
     * @param type Token type.
     */
    Token (String value, Type type) {
        this.value = value;
        this.type = type;
    }

    /**
     * Generic token validity check for each Type.
     * @param type the Type we are validating this token string against.
     * @param token the string token being validated.
     * @return {@code true} if {@code token} is a valid token of type {@code type}; {@code false} if not.
     */
    public static boolean isToken(Type type, String token) {

        if (type == Type.OPERAND) {
            return isParsable(token);   // call Apache NumberUtils.isParsable()
        } else {    // if type is an OPERATOR
            // string length pre-check
            if (token.length() != 1 && (type == Type.BINARY_OPERATOR || type == Type.GROUPING_OPERATOR)) {
                return false;
            }
            // check for matches in the operators array corresponding to this type
            for (String strIdx : OPERATORS.get(type)) {
                if (strIdx.equals(token)) {
                    return true;
                }
            }
        }
        return false;       // if no match was found
    } // isTokenA

    /**
     * A non-static overload of {@link #isToken}, which checks token validity based on its Type.
     * @param type the Type we are validating this Token object against.
     * @return {@code true} if {@code this.value} is a valid token of type {@code type}; {@code false} if not.
     */
    public boolean isToken(Type type) {
        return isToken(type, this.value);
    } // isTokenA

    /**
     * Determines the precedence level of an operator.
     * @param operator the operator being checked.
     * @return the precedence level as an int.
     */
    public static int opPrecedence(String operator) {
        int precedence;
        switch (operator.charAt(0))
        {
            case '_':               // Unary negation
                precedence = 4;
                break;
            case '^':               // Exponentiation
                precedence = 3;
                break;
            case '*':               // Multiplication
            case '/':               // Division
                precedence = 2;
                break;
            case '+':               // Addition
            case '-':               // Subtraction
                precedence = 1;
                break;
            default:    // invalid operator-- this should never happen because we validate first
                precedence = Integer.MIN_VALUE;
        }
        return precedence;
    }

    /**
     * Compare two Token objects based on <strong>operator precedence</strong>.
     * <p>Note: not intended to be used with operand Tokens.</p>
     * @param o the operator Token we are comparing against.
     * @return {@code 0} if equal precedence, {@code 1} if this operator takes precedence, {@code -1} if other operator takes precedence.
     */
    @Override
    public int compareTo(@NotNull Token o) {
        return Integer.compare(Token.opPrecedence(this.value), Token.opPrecedence(o.value));
    }

    /**
     * Check for equality between two Token objects based on <strong>value and type</strong>.
     * @param obj the Token object we are comparing against
     * @return {@code true} if the Tokens are equal (have the same value and type), {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // self check
        if (this == obj) {
            return true;
        }
        // null check
        if (obj == null) {
            return false;
        }
        // type check and cast
        if (getClass() != obj.getClass()) {
            return false;
        }
        Token token = (Token) obj;
        // field comparison
        return Objects.equals(value, token.value)
                && Objects.equals(type, token.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    /* Getters */
    public String getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    /*
    Note: The utility methods below were copied from the apache/commons/lang3/math/NumberUtils library to avoid
    creating an additional dependency for a single function.
    https://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/math/NumberUtils.html#line.1721
     */
    /**
     * <p>Checks whether the given String is a parsable number.</p>
     *
     * <p>Parsable numbers include those Strings understood by {@link Integer#parseInt(String)},
     * {@link Long#parseLong(String)}, {@link Float#parseFloat(String)} or
     * {@link Double#parseDouble(String)}. This method can be used instead of catching {@link java.text.ParseException}
     * when calling one of those methods.</p>
     *
     * <p>Hexadecimal and scientific notations are <strong>not</strong> considered parsable.
     *
     * <p>{@code Null} and empty String will return {@code false}.</p>
     *
     * @param str the String to check.
     * @return {@code true} if the string is a parsable number.
     */
    public static boolean isParsable(final String str) {
        if (str.isEmpty()) {
            return false;
        }
        if (str.charAt(str.length() - 1) == '.') {  // last char cannot be a decimal
            return false;
        }
        if (str.charAt(0) == '-') {     // if negative sign is the only char
            if (str.length() == 1) {
                return false;
            }
            return withDecimalsParsing(str, 1);
        }
        return withDecimalsParsing(str, 0);
    }

    private static boolean withDecimalsParsing(final String str, final int beginIdx) {
        int decimalPoints = 0;
        for (int i = beginIdx; i < str.length(); i++) {
            final boolean isDecimalPoint = str.charAt(i) == '.';
            if (isDecimalPoint) {
                decimalPoints++;
            }
            if (decimalPoints > 1) {
                return false;
            }
            if (!isDecimalPoint && !Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
