package com.GeeksForLess.task.expressionsCheackers;

import com.GeeksForLess.task.JDBCLogic.JDBCPostExpression;

public class UploadExpression {
    public static void upload(String expression){
        if(Validator.expressionIsValid(expression)){
            JDBCPostExpression postExpression = new JDBCPostExpression(expression);
            System.out.println("Ваш вираз надіслано");
        }
        else {
            System.err.println("Ви не можете надіслати цей вираз!!!");
        }
    }

    public static void upload(String expression, int root){
        if(Validator.expressionIsValid(expression)){
            if(RootChecker.isExpressionRoot(expression,root)){
                JDBCPostExpression postExpression = new JDBCPostExpression(expression, root);
            }
        }
    }
}
