package com.cwu.cs480;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyTokenTest {

    /**************************
     * isToken(Type, String)
     **************************/

    @Test
    void isTokenGroupingOperator_valid1() {
        String[] tokens = {"(", ")"};
        assertAll("Grouping operators (VALID)",
                () -> assertTrue(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[0])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[1]))
        );
    }

    @Test
    void isTokenGroupingOperator_invalid1() {
        String[] tokens = {"{", "[", "a", "2", ".", " ", ""};
        assertAll("Grouping operators (INVALID)",
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[0])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[1])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[2])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[3])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[4])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[5])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[6]))
        );
    }

    @Test
    void isTokenGroupingOperator_invalid2() {
        String[] tokens = {"()", ")(", "sdf", "123", "   ", "`-=", "a1b2c3"};
        assertAll("Grouping operators (INVALID)",
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[0])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[1])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[2])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[3])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[4])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[5])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.GROUPING_OPERATOR, tokens[6]))
        );
    }

    @Test
    void isTokenBinaryOperator_valid1() {
        String[] tokens = {"+", "-", "*", "/"};
        assertAll("Binary operators (VALID)",
                () -> assertTrue(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[0])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[1])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[2])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[3]))
        );
    }

    @Test
    void isTokenBinaryOperator_invalid1() {
        String[] tokens = {"{", "[", "a", "2", ".", " ", ""};
        assertAll("Binary operators (INVALID)",
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[0])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[1])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[2])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[3])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[4])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[5])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[6]))
        );
    }

    @Test
    void isTokenBinaryOperator_invalid2() {
        String[] tokens = {"()", ")(", "sdf", "123", "   ", "`-=", "a1b2c3"};
        assertAll("Binary operators (INVALID)",
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[0])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[1])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[2])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[3])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[4])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[5])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.BINARY_OPERATOR, tokens[6]))
        );
    }

    @Test
    void isTokenUnaryOperator_valid1() {
        String[] tokens = {"^", "-", "sin", "cos", "tan", "cot", "log", "ln"};
        assertAll("Unary operators (VALID)",
                () -> assertTrue(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[0])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[1])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[2])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[3])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[4])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[5])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[6])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[7]))
        );
    }

    @Test
    void isTokenUnaryOperator_invalid1() {
        String[] tokens = {"{", "[", "a", "2", ".", " ", "", "_"};
        assertAll("Binary operators (INVALID)",
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[0])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[1])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[2])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[3])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[4])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[5])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[6])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[7]))
        );
    }

    @Test
    void isTokenUnaryOperator_invalid2() {
        String[] tokens = {"()", ")(", "sdf", "123", "   ", "`-=", "a1b2c3"};
        assertAll("Unary operators (INVALID)",
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[0])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[1])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[2])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[3])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[4])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[5])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.UNARY_OPERATOR, tokens[6]))
        );
    }

    @Test
    void isTokenOperand_valid1() {
        String[] tokens = {"0", "123", "12.345", "001", "100999", "123456789.12345", ".625", "0.875"};
        assertAll("Operands (VALID)",
                () -> assertTrue(MyToken.isToken(MyToken.Type.OPERAND, tokens[0])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.OPERAND, tokens[1])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.OPERAND, tokens[2])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.OPERAND, tokens[3])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.OPERAND, tokens[4])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.OPERAND, tokens[5])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.OPERAND, tokens[6])),
                () -> assertTrue(MyToken.isToken(MyToken.Type.OPERAND, tokens[7]))
        );
    }

    @Test
    void isTokenOperand_invalid1() {
        String[] tokens = {"x", "+", "pi", "2e1", ".", " ", "", "1.2.3", "1.", "+5", "12.34a", "1a2"};
        assertAll("Operands (INVALID)",
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[0])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[1])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[2])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[3])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[4])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[5])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[6])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[7])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[8])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[9])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[10])),
                () -> assertFalse(MyToken.isToken(MyToken.Type.OPERAND, tokens[11]))
        );
    }


    @Test
    void testisToken() {
    }

    @Test
    void testOpPrecedence() {
    }

    @Test
    void testCompareTo() {
    }

    @Test
    void testEquals1() {
    }

    @Test
    void testHashCode1() {
    }

    @Test
    void testIsParsable() {
    }
}