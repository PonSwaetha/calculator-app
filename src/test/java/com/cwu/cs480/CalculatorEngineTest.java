package com.cwu.cs480;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class CalculatorEngineTest {

    private final CalculatorEngine calcEng = new CalculatorEngine();

    /**
     * Parse simple expression test
     */
    @Test
    public void parse_Evaluate_test1() {
        Assertions.assertTrue(calcEng.parseToRPN("(1+2)"));
        Assertions.assertEquals("1 2 +", calcEng.getRPNString());
        Assertions.assertEquals("3", calcEng.evaluate());
    }

    /**
     * Parse empty expression test.
     */
    @Test
    public void parse_Evaluate_test2() {
        Assertions.assertFalse(calcEng.parseToRPN(""));
        Assertions.assertTrue(calcEng.getRPNString().isEmpty());
    }

    /**
     * Parse space only expression test.
     */
    @Test
    public void parse_Evaluate_test3() {
        Assertions.assertFalse(calcEng.parseToRPN(" "));
        Assertions.assertTrue(calcEng.getRPNString().isEmpty());
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parse_Evaluate_test4() {
        Assertions.assertFalse(calcEng.parseToRPN("(1+2"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parse_Evaluate_test5() {
        Assertions.assertFalse(calcEng.parseToRPN("1+2)"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parse_Evaluate_test6() {
        Assertions.assertFalse(calcEng.parseToRPN(")1+2)"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parse_Evaluate_test7() {
        Assertions.assertFalse(calcEng.parseToRPN("1)+2"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parse_Evaluate_test8() {
        Assertions.assertFalse(calcEng.parseToRPN("(1+(1+(1))"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parse_Evaluate_test9() {
        Assertions.assertFalse(calcEng.parseToRPN("(1+(1"));
    }

    /**
     * Parse expression with unary negation.
     */
    @Test
    public void parse_Evaluate_test10() {
        Assertions.assertTrue(calcEng.parseToRPN("-2"));
        Assertions.assertEquals("2 -", calcEng.getRPNString());
        Assertions.assertEquals("-2", calcEng.evaluate());
    }

    /**
     * Parse expression with unary addition.
     */
    @Test
    public void parse_Evaluate_test11() {
        Assertions.assertTrue(calcEng.parseToRPN("+1"));
        Assertions.assertEquals("1 +", calcEng.getRPNString());
        Assertions.assertEquals("1", calcEng.evaluate());
    }

    /**
     * Parse expression with repeated unary negation.
     */
    @Test
    public void parse_Evaluate_test12() {
        Assertions.assertTrue(calcEng.parseToRPN("--2"));
        Assertions.assertEquals("2 - -", calcEng.getRPNString());
        Assertions.assertEquals("2", calcEng.evaluate());
    }

    /**
     * Parse expression with repeated unary negation.
     */
    @Test
    public void parse_Evaluate_test13() {
        Assertions.assertTrue(calcEng.parseToRPN("-----2"));
        Assertions.assertEquals("2 - - - - -", calcEng.getRPNString());
        Assertions.assertEquals("-2", calcEng.evaluate());
    }

    /**
     * Parse expression with unary negation and subtraction.
     */
    @Test
    public void parse_Evaluate_test14() {
        Assertions.assertTrue(calcEng.parseToRPN("1--2"));
        Assertions.assertEquals("1 2 - -", calcEng.getRPNString());
        Assertions.assertEquals("3", calcEng.evaluate());
    }

    /**
     * Parse expression with unary addition.
     */
    @Test
    public void parse_Evaluate_test15() {
        Assertions.assertTrue(calcEng.parseToRPN("1++2"));
        Assertions.assertEquals("1 2 + +", calcEng.getRPNString());
    }

    /**
     * Parse expression with mixed unary negation and addition.
     */
    @Test
    public void parse_Evaluate_test16() {
        Assertions.assertTrue(calcEng.parseToRPN("1-+2"));
        Assertions.assertEquals("1 2 + -", calcEng.getRPNString());
    }

    /**
     * Parse expression with mixed unary negation and addition.
     */
    @Test
    public void parse_Evaluate_test17() {
        Assertions.assertTrue(calcEng.parseToRPN("-1+-2"));
        Assertions.assertEquals("1 - 2 - +", calcEng.getRPNString());
        Assertions.assertEquals("-3", calcEng.evaluate());
    }

    /**
     * Parse expression with repeated mixed unary negation and addition.
     */
    @Test
    public void parse_Evaluate_test18() {
        Assertions.assertTrue(calcEng.parseToRPN("+-2"));
        Assertions.assertEquals("2 - +", calcEng.getRPNString());
        Assertions.assertEquals("-2", calcEng.evaluate());
    }

    /**
     * Parse expression with repeated mixed unary negation and addition.
     */
    @Test
    public void parse_Evaluate_test19() {
        Assertions.assertTrue(calcEng.parseToRPN("+-+-+---2"));
        Assertions.assertEquals("2 - - - + - + - +", calcEng.getRPNString());
        Assertions.assertEquals("-2", calcEng.evaluate());
    }

    /**
     * Parse expression with multiplication.
     */
    @Test
    public void parse_Evaluate_test20() {
        Assertions.assertTrue(calcEng.parseToRPN("2*2"));
        Assertions.assertEquals("2 2 *", calcEng.getRPNString());
        Assertions.assertEquals("4", calcEng.evaluate());
    }

    /**
     * Parse expression with division.
     */
    @Test
    public void parse_Evaluate_testDiv1() {
        Assertions.assertTrue(calcEng.parseToRPN("4/2"));
        Assertions.assertEquals("4 2 /", calcEng.getRPNString());
        Assertions.assertEquals("2", calcEng.evaluate());
    }

    /**
     * Parse expression with exponentiation.
     */
    @Test
    public void parse_Evaluate_testPow1() {
        Assertions.assertTrue(calcEng.parseToRPN("2^3"));
        Assertions.assertEquals("2 3 ^", calcEng.getRPNString());
        Assertions.assertEquals("8", calcEng.evaluate());
    }

    /**
     * Parse expression with sin().
     */
    @Test
    public void parse_Evaluate_testSin1() {
        Assertions.assertTrue(calcEng.parseToRPN("sin(30)"));
        Assertions.assertEquals("30 sin", calcEng.getRPNString());
        Assertions.assertEquals("0.5", calcEng.evaluate());
    }

    /**
     * Parse expression with cos().
     */
    @Test
    public void parse_Evaluate_testCos1() {
        Assertions.assertTrue(calcEng.parseToRPN("cos(0)"));
        Assertions.assertEquals("0 cos", calcEng.getRPNString());
        Assertions.assertEquals("1", calcEng.evaluate());
    }

    /**
     * Parse expression with tan().
     */
    @Test
    public void parse_Evaluate_testTan1() {
        Assertions.assertTrue(calcEng.parseToRPN("tan(45)"));
        Assertions.assertEquals("45 tan", calcEng.getRPNString());
        Assertions.assertEquals("1", calcEng.evaluate());
    }

    /**
     * Parse expression with cot().
     */
    @Test
    public void parse_Evaluate_testCot1() {
        Assertions.assertTrue(calcEng.parseToRPN("cot(45)"));
        Assertions.assertEquals("45 cot", calcEng.getRPNString());
        Assertions.assertEquals("1", calcEng.evaluate());
    }

    /**
     * Parse expression with ln().
     */
    @Test
    public void parse_Evaluate_testLn1() {
        Assertions.assertTrue(calcEng.parseToRPN("ln(1)"));
        Assertions.assertEquals("1 log", calcEng.getRPNString());
        Assertions.assertEquals("0", calcEng.evaluate());
    }

    /**
     * Parse expression with log().
     */
    @Test
    public void parse_Evaluate_testLog1() {
        Assertions.assertTrue(calcEng.parseToRPN("log(100)"));
        Assertions.assertEquals("100 log10", calcEng.getRPNString());
        Assertions.assertEquals("2", calcEng.evaluate());
    }

    /**
     * Parse expression in which functions have mismatched parenthesis.
     */
    @Test
    public void parse_Evaluate_testFuncs1() {
        String[] expressions = {"sin(", "cos(", "tan(", "cot(", "ln(", "log("};
        assertAll("Functions parse (INVALID)",
                () -> assertFalse(calcEng.parseToRPN(expressions[0])),
                () -> assertFalse(calcEng.parseToRPN(expressions[1])),
                () -> assertFalse(calcEng.parseToRPN(expressions[2])),
                () -> assertFalse(calcEng.parseToRPN(expressions[3])),
                () -> assertFalse(calcEng.parseToRPN(expressions[4])),
                () -> assertFalse(calcEng.parseToRPN(expressions[5]))
        );
    }

    /**
     * Parse expression with a combination of operators.
     */
    @Test
    public void parse_Evaluate_test() {
        Assertions.assertTrue(calcEng.parseToRPN("-5.78+-(4-2.23)+sin(0)*cos(1)/(1+tan(2*ln(-3+2*(1.23+99.111))))"));
        Assertions.assertEquals("5.78 - 4 2.23 - - + 0 sin 1 cos * 1 2 3 - 2 1.23 99.111 + * + log * tan + / +", calcEng.getRPNString());
        Assertions.assertEquals("-7.55", calcEng.evaluate());
    }


    /**
     * Parse expression with
     */
    /*
    @Test
    public void parse_Evaluate_test() {
        Assertions.assertTrue(calcEng.parseToRPN(""));
        Assertions.assertEquals("", calcEng.getRPNString());
        Assertions.assertEquals("", calcEng.evaluate());
    }
     */
}