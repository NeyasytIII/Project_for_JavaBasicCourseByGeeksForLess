package com.GeeksForLess.task.expressionsCheackers;

import com.GeeksForLess.task.JDBCLogic.JDBCPostExpression;

public class ValidConnector {
    public static void send(String expression){
        if(Validator.expressionIsValid(expression)){
            JDBCPostExpression postExpression = new JDBCPostExpression(expression);
            System.out.println("Ваш вираз надіслано");
        }
        else {
            System.err.println("Ви не можете надіслати цей вираз!!!");
        }
    }
}
