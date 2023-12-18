package com.GeeksForLess.task.expressionsCheackers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class RootCheckerTest {

    // Перевіряємо метод на коректність введеного кореня
    @Test
    public void RootChecker_ValidRoot() {
        assertTrue(RootChecker.isExpressionRoot("2*x+5=10", 2.5));
        assertTrue(RootChecker.isExpressionRoot("2*x+5.5=10", 2.25));
        assertTrue(RootChecker.isExpressionRoot("0.5*x=2", 4.0));
        assertTrue(RootChecker.isExpressionRoot("5.02*x=2", 0.3984063745));
        assertTrue(RootChecker.isExpressionRoot("12312.897*x=2", 0.0001624313109));
        assertTrue(RootChecker.isExpressionRoot("x*x=4", -2.0));
        assertTrue(RootChecker.isExpressionRoot("x*x=4", 2.0));
        assertTrue(RootChecker.isExpressionRoot("2*x+5=17", 6.0));
        assertTrue(RootChecker.isExpressionRoot("-1.3*5/x=1.2",-5.41666667));
        assertTrue(RootChecker.isExpressionRoot("2*x*x=10", 2.23606797739));
        assertTrue(RootChecker.isExpressionRoot("2*x*x=10", -2.23606797739));
        assertTrue(RootChecker.isExpressionRoot("2*(х+5+x)+5=10", -1.25)); // латиниця + кирилиця
        assertTrue(RootChecker.isExpressionRoot("17=2*x+5", 6));

    }

    @Test
    public void testIsExpressionRoot_InvalidRoot() {
        assertFalse(RootChecker.isExpressionRoot("2*x+5=10", 3.0));
        assertFalse(RootChecker.isExpressionRoot("2*x+5.5=10", 2.0));
        assertFalse(RootChecker.isExpressionRoot("0.5*x=2", 1.0));
        assertFalse(RootChecker.isExpressionRoot("5.02*x=2", 10.0));
        assertFalse(RootChecker.isExpressionRoot("12312.897*x=2", 0.0));
    }
}
