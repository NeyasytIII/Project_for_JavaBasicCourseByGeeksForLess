package com.GeeksForLess.task;

import com.GeeksForLess.task.JDBCLogic.JDBCPostExpression;
import com.GeeksForLess.task.expressionsCheackers.ValidConnector;

public class MathAssistant {
    public static void main(String[] args) {
        String expression = "-1.3*5/x=1.2";
        ValidConnector.send(expression);

    }
}
