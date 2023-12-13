package com.GeeksForLess.task.JDBCLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPostExpression {

    private static final String URL = "jdbc:postgresql://localhost:8090/Expressions";
    private static final String USERNAME = "postgres";
    private static final String USERPASSWORD = "root";
    private static final String POSTREQUEST = "INSERT INTO expressions  (correct_expression) values (?);";
    private static Connection connection;


    public JDBCPostExpression(String expression) {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            System.out.println("Connection is: " + !connection.isClosed());
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        try {
            PreparedStatement statement = connection.prepareStatement(POSTREQUEST);
            statement.setString(1, expression);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }


}
