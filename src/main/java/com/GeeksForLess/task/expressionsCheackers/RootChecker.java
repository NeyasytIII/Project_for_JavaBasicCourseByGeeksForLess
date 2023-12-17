package com.GeeksForLess.task.expressionsCheackers;

import com.ezylang.evalex.*;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;


public class RootChecker {


    //Створимо метод для перевірки чи є число коренем виразу.
    //Число вважаємо коренем рівняння, якщо за умови підстановки цього числа
    //замість всіх входжень x, різниця між значеннями лівої та правої частин
    //рівняння не перевищує 10 у степені -9
    public static boolean isExpressionRoot(String expression, double root) {
        // розділяємо вираз н праву та ліву частини
        String leftPart = expression.substring(0, expression.indexOf('='));
        String rightPart = expression.substring(expression.indexOf('=') + 1, expression.length());
        // для зручності підрахунку скористаємося не великою бібліотекою - EvalEx
        EvaluationValue leftPartRes = null;
        EvaluationValue rightPartRes = null;

        try {
            // розрахуємо результат обох частин завдяки заміні значення змінної root замість змінної.

            leftPartRes = new Expression(leftPart)
                    .with(Value(leftPart), root)
                    .evaluate();
            rightPartRes = new Expression(rightPart)
                    .with(Value(rightPart), root)
                    .evaluate();
        } catch (EvaluationException e) {
            System.err.println("EvaluationException" + e.getMessage());
            return false;
        } catch (ParseException e) {
            System.err.println("ParseException" + e.getMessage());
            return false;
        }
// Для більшої інформативності виведемо значення лівої та правої частин
//        System.out.println("Left Part Result: " + leftPartRes.getNumberValue().doubleValue());
//        System.out.println("Right Part Result: " + rightPartRes.getNumberValue().doubleValue());

        // Повертаємо "true" якщо різниця обох частин по модулю через те,
        // що нам треба знати різницю між результатом операції між двома числами
        // з 10 зі значенням степені -9
        // у гіршому випадку ми будемо зрівнювати числа з різними знаками
        return Math.abs(leftPartRes.getNumberValue().doubleValue() - rightPartRes.getNumberValue().doubleValue()) <= Math.pow(10, -9);
    }

    //Оскільки в нас є 2 варіанти змінної (кирилицею та латиницею) ми створимо відстежувач для змінної
    private static String Value(String expression) {
        if (expression.contains("x")) {
            return "x";
        } else {
            return "х";
        }
    }

}
