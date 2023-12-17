package com.GeeksForLess.task.expressionsCheackers;

import com.GeeksForLess.task.JDBCLogic.JDBCPostExpression;

public class ExpressionController {

    //Завантажимо перевірені вирази.
    public static void upload(String expression) {
        if (Validator.expressionIsValid(expression)) {
            JDBCPostExpression postExpression = new JDBCPostExpression(expression);
            System.out.println("Ваш вираз додано");
        } else {
            System.err.println("Ви не можете додати цей вираз!!!");
            System.err.println("Не коректне рівняння");
        }
    }

    //Завантажуємо перевірені корені + вираз ящо не містить дублікату.

    public static void upload(String expression, double... root) {
        if (Validator.expressionIsValid(expression)) {
            for (double el : root) {
                if (RootChecker.isExpressionRoot(expression, el)) {
                    JDBCPostExpression postExpression = new JDBCPostExpression(expression, el);
                    System.out.println("Корінь: " + el + " - додано");
                } else {
                    System.err.println("Корінь: " + el + " - не вірний");
                }
            }
        } else {
            System.err.println("Не коректне рівняння");
        }

    }


}
