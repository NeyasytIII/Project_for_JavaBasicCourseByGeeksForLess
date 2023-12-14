package com.GeeksForLess.task;

import com.GeeksForLess.task.expressionsCheackers.RootChecker;
import com.GeeksForLess.task.expressionsCheackers.Validator;


public class MathAssistant {
    public static void main(String[] args) {
        String expression = "-0.455*x*x=(10)";

        System.out.println(Validator.expressionIsValid(expression));

        boolean res = RootChecker.isExpressionRoot(expression, (double) -2.2360679775);


    }
}
