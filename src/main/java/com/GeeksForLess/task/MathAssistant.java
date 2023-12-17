package com.GeeksForLess.task;

import com.GeeksForLess.task.JDBCLogic.ByRootGetter;
import com.GeeksForLess.task.expressionsCheackers.Validator;


public class MathAssistant {
    public static void main(String[] args) {
        String expression = "(x/3) = (-232.2342)*(2.3+5)";

        System.out.println(Validator.expressionIsValid(expression));
//
//        boolean res = RootChecker.isExpressionRoot(expression, (double) 2);
//
//        System.out.println(res);
//        ExpressionController.upload(expression,6);

//        ByRootGetter.getByRootInDB(2);
//        ByRootGetter.getByRoots(2);

    }
}
