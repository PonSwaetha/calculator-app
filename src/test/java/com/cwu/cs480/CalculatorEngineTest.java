package com.cwu.cs480;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

public class CalculatorEngineTest {

    private final CalculatorEngine calcEng = new CalculatorEngine();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Parse simple expression test
     */
    @Test
    public void parseToRPN_test1() {
        assertTrue(calcEng.parseToRPN("(1+2)"));
        assertEquals(List.of("1", "2", "+"), calcEng.getRPNStack());

    }

    /**
     * Parse empty expression test.
     */
    @Test
    public void parseToRPN_test2() {
        assertFalse(calcEng.parseToRPN(""));

    }

    /**
     * Parse empty expression test.
     */
    @Test
    public void parseToRPN_test3() {
        assertTrue(calcEng.parseToRPN(""));
    }

    /**
     * Parse expression with mismatched parentheses.
     */
    @Test
    public void parseToRPN_test4() {
        assertFalse(calcEng.parseToRPN("(1+2"));
    }
}