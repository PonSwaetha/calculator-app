package com.cwu.cs480;

import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class representing a token within a mathematical expression.
 */
public class MyToken implements Comparable<MyToken> {

    String value;
    Type type;

    /**
     * Parameterized MyToken constructor.
     * @param value MyToken value.
     * @param type MyToken type.
     */
    MyToken(String value, Type type) {
        this.value = value;
        this.type = type;
    }

    enum Type {
        GROUPING_OPERATOR,
        UNARY_OPERATOR,
        BINARY_OPERATOR,
        OPERAND
    }

    enum Associativity {
        NONE,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    /* Supported operations and functions */
    private static final EnumMap<Type, String[]> OPERATORS = new EnumMap<>(Map.of(
            Type.GROUPING_OPERATOR, new String[] {"(", ")"},
            Type.UNARY_OPERATOR, new String[] {"-", "sin[", "cos[", "tan[", "cot[", "log[", "ln["},
            Type.BINARY_OPERATOR, new String[] {"^", "*", "/", "+", "-"}
    ));

    /**
     * Parse a string to determine if it's a valid token.
     * @param token string being parsed.
     * @return If token is valid, return its {@code Type}. If invalid, return {@code null}.
     */
    public static Type parseToken(String token) {
        Type tokenType = null;
        // validate the token
        if (isToken(Type.GROUPING_OPERATOR, token)) {
            tokenType = Type.GROUPING_OPERATOR;
        } else if (isToken(Type.BINARY_OPERATOR, token)) {      // must parse binary ops first, because of unary minus
            tokenType = Type.BINARY_OPERATOR;
        } else if (isToken(Type.UNARY_OPERATOR, token)) {
            tokenType = Type.UNARY_OPERATOR;
        } else if (isToken(Type.OPERAND, token)) {
            tokenType = Type.OPERAND;
        }
        return tokenType;       // null if no match found
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
    } // isToken

    /**
     * A non-static override of {@link #isToken(Type, String)}, which checks token validity based on its Type.
     * @param type the Type we are validating this MyToken object against.
     * @return {@code true} if {@code this.value} is a valid token of type {@code type}; {@code false} if not.
     */
    public boolean isToken(Type type) {
        return (this.type == type);
    } // isToken

    /**
     * Determines the precedence level of a token.
     * @param token the token being checked.
     * @return the precedence level as an int.
     */
    public static int getPrecedence(MyToken token) {
        switch (token.getType())
        {
            case GROUPING_OPERATOR:
                if (token.value.equals("(")) {
                    return 5;         // Open parenthesis
                } else {
                    return 0;         // Close parenthesis
                }
            case UNARY_OPERATOR:
                if (token.value.equals("-")) {
                    return 3;         // Unary negation
                } else {
                    return 5;         // Functions
                }
            case BINARY_OPERATOR:
            switch (token.getValue()) {
                case "^":               // Exponentiation
                    return 4;
                case "*":               // Multiplication
                case "/":               // Division
                    return 2;
                case "+":                // Addition
                case "-":                // Subtraction
                    return 1;
            }
            case OPERAND:
                return 0;
            default:    // invalid/null token-- this should never happen because we validate first
                return Integer.MIN_VALUE;
        }
    }

    /**
     * A non-static override of {@link #getPrecedence(MyToken)}, which returns the precedence of a token.
     * @return the precedence level as an int.
     */
    public int getPrecedence() {
        return getPrecedence(this);
    }

    public static Associativity getAssociativity(MyToken token) {
        switch (token.getType()) {
            case UNARY_OPERATOR:
                if (token.value.equals("-")) {
                    return Associativity.RIGHT_TO_LEFT;
                } else {
                    return Associativity.NONE;      // functions
                }
            case BINARY_OPERATOR:
                if (token.value.equals("^")) {
                    return Associativity.RIGHT_TO_LEFT;
                } else {
                    return Associativity.LEFT_TO_RIGHT;
                }
            default:
                return Associativity.NONE;      // grouping & operands
        }
    }

    public Associativity getAssociativity() {
        return getAssociativity(this);
    }

    /**
     * Compare two MyToken objects based on <strong>operator precedence</strong>.
     * <p>Note: not intended to be used with operand Tokens.</p>
     * @param o the operator MyToken we are comparing against.
     * @return {@code 0} if equal precedence, {@code 1} if this operator takes precedence, {@code -1} if other operator takes precedence.
     */
    @Override
    public int compareTo(@NotNull MyToken o) {
        return Integer.compare(this.getPrecedence(), o.getPrecedence());
    }

    /**
     * Check for equality between two MyToken objects based on <strong>value and type</strong>.
     * @param obj the MyToken object we are comparing against
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
        MyToken token = (MyToken) obj;
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
