package com.GeeksForLess.task.JDBCLogic;

import java.sql.*;

public class JDBCPostExpression {

    private static final String URL = "jdbc:postgresql://localhost:8090/Expressions";
    private static final String USERNAME = "postgres";
    private static final String USERPASSWORD = "root";
    private static final String POSTEXPRESSION = "INSERT INTO expressions (correct_expression) values (?);";
    private static final String GETEXPRESSIONID = "SELECT expression_id FROM expressions WHERE correct_expression = (?);";
    private static final String POSTEXPRESSIONROOT = "INSERT INTO expression_root (root, expressionid) VALUES ((?), (?));";


    public JDBCPostExpression(String expression) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            System.out.println("Connection is: " + !connection.isClosed());
            PreparedStatement statement = connection.prepareStatement(POSTEXPRESSION);
            statement.setString(1, expression);
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Exception in JDBCPostExpression" + e.getSQLState());
            System.err.println(e.getMessage());
        }
    }

    public JDBCPostExpression(String expression, double root) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            System.out.println("Connection is: " + !connection.isClosed());
            PreparedStatement statement1 = connection.prepareStatement(POSTEXPRESSION);
            PreparedStatement statement2 = connection.prepareStatement(GETEXPRESSIONID);
            PreparedStatement statement3 = connection.prepareStatement(POSTEXPRESSIONROOT);
            connection.setAutoCommit(false);
            if (expressionIsDuplicate(connection,expression) == false) {
                statement1.setString(1, expression);
                statement1.execute();
            }

                statement2.setString(1, expression);

                ResultSet resultSet = statement2.executeQuery();
                Integer result = null;
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                } else {
                    result = null;
                }
                statement3.setDouble(1, root);
                statement3.setInt(2, result);
                statement3.execute();
                connection.commit();
                connection.setAutoCommit(true);
            } catch(SQLException e){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.err.println("Exception in JDBCPostExpression (rollback) " + e.getSQLState());
                    System.err.println(e.getMessage());
                }
                System.err.println("Exception in JDBCPostExpression" + e.getSQLState());
                System.err.println(e.getMessage());
            }
        }


        private static boolean expressionIsDuplicate (Connection connection, String expression){
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT correct_expression FROM expressions WHERE correct_expression = (?);");
                statement.setString(1, expression);
                ResultSet resultSet = statement.executeQuery();

                return resultSet.next();

            } catch (SQLException e) {
                System.err.println("Duplicate SQLException" + e.getSQLState());
                System.err.println(e.getMessage());
            }
            return false;
        }

    }
