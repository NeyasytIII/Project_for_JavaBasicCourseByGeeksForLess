package com.GeeksForLess.task.expressionsCheackers;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ValidatorTest {
    // Перевіряємо валідатор на коректність роботи методу для валідації чисел.
    @Test
    public void testValidator_numsIsValid_Valid() {
        assertTrue(Validator.expressionIsValid("2*x+5=10"));
        assertTrue(Validator.expressionIsValid("2*x+5.5=10"));
        assertTrue(Validator.expressionIsValid("0.5*x=2"));
        assertTrue(Validator.expressionIsValid("5.02*x=2"));
        assertTrue(Validator.expressionIsValid("12312.897*x=2"));
    }

    @Test
    public void testValidator_numsIsValid_Invalid() {
        assertFalse(Validator.expressionIsValid("2*x+5.=10"));
        assertFalse(Validator.expressionIsValid("2*x+5=10."));
        assertFalse(Validator.expressionIsValid("2*x+5=10.5.5"));
        assertFalse(Validator.expressionIsValid("2*x+5=10.5.5."));
        assertFalse(Validator.expressionIsValid("2*x+5=10..5"));
        assertFalse(Validator.expressionIsValid("2*x+5=10a"));
        assertFalse(Validator.expressionIsValid("2*x+5=1.2.3"));
        assertFalse(Validator.expressionIsValid("02*x+5=10"));
        assertFalse(Validator.expressionIsValid("0.5*x.2=2"));
        assertFalse(Validator.expressionIsValid(".2*x+5=10"));
        assertFalse(Validator.expressionIsValid("00.5*x=2"));
    }

    // Перевіряємо валідатор на коректність роботи методу для валідації операторів.
    @Test
    public void testValidator_operatorsIsValid_Valid() {
        assertTrue(Validator.expressionIsValid("2*x+5=10"));
        assertTrue(Validator.expressionIsValid("2*x+-5=10"));
        assertTrue(Validator.expressionIsValid("2*x+5=-10"));
        assertTrue(Validator.expressionIsValid("-2*x+5=-10"));
    }

    @Test
    public void testValidator_operatorsIsValid_Invalid() {
        assertFalse(Validator.expressionIsValid("2*x+5=10+"));
        assertFalse(Validator.expressionIsValid("2*x+5=+10"));
        assertFalse(Validator.expressionIsValid("2*x++5=10"));
        assertFalse(Validator.expressionIsValid("2*x*/5=10"));
        assertFalse(Validator.expressionIsValid("2*x*5=10-"));
        assertFalse(Validator.expressionIsValid("2*x*5-=10"));
    }

    //Перевіряємо валідатор на коректність роботи методу для валідації дужок.
    @Test
    public void testValidator_bracketsIsValid_Valid() {
        assertTrue(Validator.expressionIsValid("(2*x+5)=17"));
        assertTrue(Validator.expressionIsValid("2*(x+5)=10"));
        assertTrue(Validator.expressionIsValid("2*(x+5+5)=10"));
        assertTrue(Validator.expressionIsValid("(2*x)+(5+5)=10"));
        assertTrue(Validator.expressionIsValid("2*(x+(5+5))=10"));
        assertTrue(Validator.expressionIsValid("2*(x+(5+5))=(-10)"));
        assertTrue(Validator.expressionIsValid("2*(x+(5+5))=(-10.34234324)"));
    }


    @Test
    public void testValidator_bracketsIsValid_Invalid() {
        assertFalse(Validator.expressionIsValid("2*(x+5=17"));
        assertFalse(Validator.expressionIsValid("2*x+5)+5=10)"));
        assertFalse(Validator.expressionIsValid("(2*x+5+5=10)"));
        assertFalse(Validator.expressionIsValid("2*x+)(5+5=10"));
        assertFalse(Validator.expressionIsValid(")2*x+5+5(=10"));
        assertFalse(Validator.expressionIsValid("(2*x+5+5=10"));
        assertFalse(Validator.expressionIsValid("2*x+5+5=10)"));
        assertFalse(Validator.expressionIsValid("2*x+5+5(=)10"));
        assertFalse(Validator.expressionIsValid("2*x+5+5=(10)"));
        assertFalse(Validator.expressionIsValid("2*x+(5+5+)=10"));
        assertFalse(Validator.expressionIsValid("2*x+(5)=10"));
        assertFalse(Validator.expressionIsValid("2*x+(524234.2342)=10"));

    }

    // Перевіримо валідатор на коректне використання метода ля перевірки валідність змінної.
    @Test
    public void testValidator_variableIsCorrect_Valid() {
        assertTrue(Validator.expressionIsValid("2*x+5=10"));
        assertTrue(Validator.expressionIsValid("2*x+5=10 +x"));
        assertTrue(Validator.expressionIsValid("2+x=10"));
        assertTrue(Validator.expressionIsValid("(2+x)=10"));
        assertTrue(Validator.expressionIsValid("-x=10"));
        assertTrue(Validator.expressionIsValid("x=10")); // латиниця
        assertTrue(Validator.expressionIsValid("х=10")); // кирилиця
        assertTrue(Validator.expressionIsValid("x+2=10"));
    }

    @Test
    public void testValidator_variableIsCorrect_Invalid() {
        assertFalse(Validator.expressionIsValid("2*x+5=10xx"));
        assertFalse(Validator.expressionIsValid("2*хx+5=10х"));
        assertFalse(Validator.expressionIsValid("xx+ 2+x=10x"));
        assertFalse(Validator.expressionIsValid("хx+ 2+x=10"));// кирилиця + латиниця
        assertFalse(Validator.expressionIsValid("2+х=10х"));//латиниця
        assertFalse(Validator.expressionIsValid("2+х=10х"));//кирилиця
        assertFalse(Validator.expressionIsValid("(x)=10x"));
        assertFalse(Validator.expressionIsValid("(2+х)=10х"));
        assertFalse(Validator.expressionIsValid("2*x+5=10y"));
        assertFalse(Validator.expressionIsValid("2*x+5=10у"));
        assertFalse(Validator.expressionIsValid("(2+x)=10y"));
    }

    // Перевіримо валідатор на коректне використання для визначення виразу
    @Test
    public void testValidator_isExpression_Valid() {
        assertTrue(Validator.expressionIsValid("2*x+5=10"));
        assertTrue(Validator.expressionIsValid("2*х+5=10"));
        assertTrue(Validator.expressionIsValid("2+x=10"));
        assertTrue(Validator.expressionIsValid("2+х=10"));
        assertTrue(Validator.expressionIsValid("(2+x)=10"));
    }
    @Test
    public void testValidator_isExpression_Invalid() {
        assertFalse(Validator.expressionIsValid("2*x+5"));
        assertFalse(Validator.expressionIsValid("2*х+5"));
        assertFalse(Validator.expressionIsValid("2+x"));
        assertFalse(Validator.expressionIsValid("(2+x)"));
        assertFalse(Validator.expressionIsValid("2*x+5=10y"));
        assertFalse(Validator.expressionIsValid("2*x+5=10у"));
        assertFalse(Validator.expressionIsValid("(2+x)=10y"));
    }


    // Перевірка валідатора на коректне використання загального методу перевірки виразів.
    @Test
    public void testValidator_expressionIsValid_Valid() {
        assertTrue(Validator.expressionIsValid("2*x+5=17"));
        assertTrue(Validator.expressionIsValid("-1.3*5/x=1.2"));
        assertTrue(Validator.expressionIsValid("2*x*x=10"));
        assertTrue(Validator.expressionIsValid("2*x*x=10"));
        assertTrue(Validator.expressionIsValid("2*(х+5+x)+5=10")); // латиниця + кирилиця
        assertTrue(Validator.expressionIsValid("17=2*x+5"));
        assertTrue(Validator.expressionIsValid("2*x+5=10"));
        assertTrue(Validator.expressionIsValid("2*х+5=10"));
        assertTrue(Validator.expressionIsValid("2+x=10"));
        assertTrue(Validator.expressionIsValid("2+х=10"));
        assertTrue(Validator.expressionIsValid("(2+x)=10"));
    }
    @Test
    public void testValidator_expressionIsValid_Invalid() {
        assertFalse(Validator.expressionIsValid(""));
        assertFalse(Validator.expressionIsValid("2*x+5"));
        assertFalse(Validator.expressionIsValid("2*х+5"));
        assertFalse(Validator.expressionIsValid("2+x"));
        assertFalse(Validator.expressionIsValid("2+х"));
        assertFalse(Validator.expressionIsValid("(2+x)"));
        assertFalse(Validator.expressionIsValid("2*x+5=10y"));
        assertFalse(Validator.expressionIsValid("2*x+5=10у"));
        assertFalse(Validator.expressionIsValid("(2+x)=10y"));
        assertFalse(Validator.expressionIsValid("(2+x/0)=10"));
        assertFalse(Validator.expressionIsValid("(2+x)=10/0"));
        assertFalse(Validator.expressionIsValid("2/0+x=10"));
    }

}