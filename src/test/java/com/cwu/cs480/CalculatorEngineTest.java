package com.cwu.cs480;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.util.List;


public class CalculatorEngineTest {

    private final CalculatorEngine calcEng = new CalculatorEngine();

    /**
     * Parse simple expression test
     */
    @Test
    public void parseToRPN_test1() throws ParseException {
        //Assertions.assertTrue(calcEng.parseToRPN("(1+2)"));

        //Assertions.assertEquals(List.of("1", "2", "+"), calcEng.getRPN());

    }

    /**
     * Parse empty expression test.
     */
    @Test
    public void parseToRPN_test2() throws ParseException {
        ////Assertions.assertFalse(calcEng.parseToRPN(""));

    }

    /**
     * Parse empty expression test.
     */
    @Test
    public void parseToRPN_test3() throws ParseException {
        //Assertions.assertTrue(calcEng.parseToRPN(""));
        //Assertions.assertTrue(calcEng.getRPN().empty());
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parseToRPN_test4() throws ParseException {
        //Assertions.assertFalse(calcEng.parseToRPN("(1+2"));
        //assertThrows
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parseToRPN_test5() {
        //Assertions.assertFalse(calcEng.parseToRPN("1+2)"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parseToRPN_test6() {
        //Assertions.assertFalse(calcEng.parseToRPN(")1+2)"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parseToRPN_test7() {
        //Assertions.assertFalse(calcEng.parseToRPN("1)+2"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parseToRPN_test08() {
        //Assertions.assertFalse(calcEng.parseToRPN("(1+(1+(1))"));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parseToRPN_test09() {
        //Assertions.assertFalse(calcEng.parseToRPN("(1+(1"));
    }

    /**
     * Parse expression with unary negation.
     */
    @Test
    public void parseToRPN_test8() {
        //Assertions.assertTrue(calcEng.parseToRPN("-2"));
        Assertions.assertEquals(List.of("0", "2", "-"), calcEng.getRPN());
    }

    /**
     * Parse expression with unary addition.
     */
    @Test
    public void parseToRPN_test9() {
        //Assertions.assertTrue(calcEng.parseToRPN("+1"));
        Assertions.assertEquals(List.of("1"), calcEng.getRPN());
    }

    /**
     * Parse expression with repeated unary negation.
     */
    @Test
    public void parseToRPN_test10() {
        //Assertions.assertTrue(calcEng.parseToRPN("--2"));
        Assertions.assertEquals(List.of("0", "0", "2", "-"), calcEng.getRPN());
    }

    /**
     * Parse expression with repeated unary negation.
     */
    @Test
    public void parseToRPN_test0() {
        //Assertions.assertTrue(calcEng.parseToRPN("-----2"));
        Assertions.assertEquals(List.of("-2"), calcEng.getRPN());
    }

    /**
     * Parse expression with unary negation.
     */
    @Test
    public void parseToRPN_test0000() {
        //Assertions.assertTrue(calcEng.parseToRPN("1--2"));
        Assertions.assertEquals(List.of("3"), calcEng.getRPN());
    }

    /**
     * Parse expression with unary addition.
     */
    @Test
    public void parseToRPN_test00000() {
        //Assertions.assertTrue(calcEng.parseToRPN("1++2"));
        Assertions.assertEquals(List.of("3"), calcEng.getRPN());
    }

    /**
     * Parse expression with mixed unary negation and addition.
     */
    @Test
    public void parseToRPN_test000000() {
        //Assertions.assertTrue(calcEng.parseToRPN("1-+2"));
        Assertions.assertEquals(List.of("-1"), calcEng.getRPN());
    }

    /**
     * Parse expression with mixed unary negation and addition.
     */
    @Test
    public void parseToRPN_test0000000() {
        //Assertions.assertTrue(calcEng.parseToRPN("-1+-2"));
        Assertions.assertEquals(List.of("-3"), calcEng.getRPN());
    }

    /**
     * Parse expression with repeated mixed unary negation and addition.
     */
    @Test
    public void parseToRPN_test00() {
        //Assertions.assertTrue(calcEng.parseToRPN("+-2"));
        Assertions.assertEquals(List.of("-2"), calcEng.getRPN());
    }

    /**
     * Parse expression with repeated mixed unary negation and addition.
     */
    @Test
    public void parseToRPN_test000() {
        //Assertions.assertTrue(calcEng.parseToRPN("+-+-+---2"));
        //assertEquals(List.of("-2"), calcEng.getRPN());
    }

    /**
     * Parse expression with multiplication.
     */
    @Test
    public void parseToRPN_test01() {
        //Assertions.assertTrue(calcEng.parseToRPN("2*2"));
        Assertions.assertEquals(List.of("2", "2", "*"), calcEng.getRPN());
    }

    /**
     * Parse expression with division.
     */
    @Test
    public void parseToRPN_test02() {
        //Assertions.assertTrue(calcEng.parseToRPN("4/2"));
        Assertions.assertEquals(List.of("4", "2", "/"), calcEng.getRPN());
    }

    /**
     * Parse expression with exponentiation.
     */
    @Test
    public void parseToRPN_test03() {
        //Assertions.assertTrue(calcEng.parseToRPN("2^3"));
        Assertions.assertEquals(List.of("2", "2", "*"), calcEng.getRPN());
    }

    /**
     * Parse expression with sin().
     */
    @Test
    public void parseToRPN_test04() {
        //Assertions.assertTrue(calcEng.parseToRPN(""));
    }

    /**
     * Parse expression with cos().
     */
    @Test
    public void parseToRPN_test05() {
        //Assertions.assertTrue(calcEng.parseToRPN(""));
    }

    /**
     * Parse expression with tan().
     */
    @Test
    public void parseToRPN_test06() {
        //Assertions.assertTrue(calcEng.parseToRPN(""));
    }

    /**
     * Parse expression with cot().
     */
    @Test
    public void parseToRPN_test07() {
        //Assertions.assertTrue(calcEng.parseToRPN(""));
    }

    /**
     * Parse expression with ln().
     */
    @Test
    public void parseToRPN_test008() {
        //Assertions.assertTrue(calcEng.parseToRPN(""));
    }

    /**
     * Parse expression with log()
     */
    @Test
    public void parseToRPN_test009() {
        //Assertions.assertTrue(calcEng.parseToRPN(""));
    }

    /**
     * Parse expression with
     */
    @Test
    public void parseToRPN_test() {
        //Assertions.assertTrue(calcEng.parseToRPN(""));
    }
}