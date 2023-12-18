package com.GeeksForLess.task.expressionsCheackers;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionControllerTest {

    @Test
    void testUploadWithValidExpression() {

        String expression = "2*x+5=17";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        ExpressionController.upload(expression);
        System.setOut(System.out);
        assertEquals("Ваш вираз додано!\n", outContent.toString());
    }

    @Test
    void testUploadWithInvalidExpression() {

        String expression = "x+5*(1+3=";
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();

        System.setErr(new PrintStream(errContent));
        ExpressionController.upload(expression);
        System.setErr(System.err);
        assertEquals("Ви не можете додати цей вираз!!!" +
                "\n" + "Не коректне рівняння\n", errContent.toString());
    }

    @Test
    void testUploadWithValidExpressionAndRoot() {

        String expression = "2*x=3+1";
        double root = 2.0;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ExpressionController.upload(expression, root);
        System.setOut(System.out);
        assertEquals("Корінь: 2.0 - додано\n", outContent.toString());
    }

    @Test
    void testUploadWithInvalidExpressionAndRoot() {

        String expression = "x+2x=1";
        double root = 3.0;

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        ExpressionController.upload(expression, root);
        System.setErr(System.err);
        assertEquals("Не коректне рівняння\n", errContent.toString());
    }
}
