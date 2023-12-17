package com.GeeksForLess.task.JDBCLogic;

import com.GeeksForLess.task.expressionsCheackers.RootChecker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCGetROOT {
    private static final String URL = "jdbc:postgresql://localhost:8090/Expressions";
    private static final String USERNAME = "postgres";
    private static final String USERPASSWORD = "root";


    // Знаходимо вираз за одним коренем, з нашої бд.
    public static void getByRootInDB(double root) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement("SELECT expressionid FROM expression_root WHERE root =(?);");
            PreparedStatement statement2 = connection.prepareStatement("SELECT correct_expression FROM expressions WHERE expression_id = (?);");
            statement1.setDouble(1, root);
            ResultSet expressionsIdRes = statement1.executeQuery();
            if (!expressionsIdRes.isBeforeFirst()) {
                System.err.println("Рівнянь з таким коренем - немає !!!");
            }
            while (expressionsIdRes.next()) {
                Integer expId = expressionsIdRes.getInt("expressionid");
                statement2.setInt(1, expId);
                ResultSet result = statement2.executeQuery();
                if (result.next()) {
                    System.out.println(result.getString("correct_expression"));

                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("JDBCGetROOT Exception " + ex.getSQLState());
                System.err.println(e.getMessage());
            }
            System.err.println("JDBCGetROOT Exception " + e.getSQLState());
            System.err.println(e.getMessage());
        }


    }

    // Знаходимо рівняння які мають хоча б 1 корінь з набору.
    public static void getFromSetOfRoots(double... roots) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            PreparedStatement statement1 = connection.prepareStatement("SELECT correct_expression FROM expressions;");
            ResultSet resultSet = statement1.executeQuery();
            List<String> expressions = new ArrayList<>();

            //Завантажимо вирази в колекцію так, як ResultSet проходить лише 1 ітерацію.
            while (resultSet.next()) {
                expressions.add(resultSet.getString("correct_expression"));
            }
            for (double el : roots) {
                for (String expression : expressions) {
                    if (RootChecker.isExpressionRoot(expression, el)) {
                        System.out.println(el + " - корінь рівняння: " + expression);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
            System.err.println(e.getMessage());
        }

    }

// Шукаємо вираз, що відповідає усім введеним кореням.
    public static void getByRoots(double... roots) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            PreparedStatement statement1 = connection.prepareStatement("SELECT correct_expression FROM expressions;");
            ResultSet resultSet = statement1.executeQuery();
            List<String> expressions = new ArrayList<>();

            //Завантажимо вирази в колекцію так, як ResultSet проходить лише 1 ітерацію.
            while (resultSet.next()) {
                expressions.add(resultSet.getString("correct_expression"));
            }
            for (String expression : expressions) {
                boolean isValid = true;
                for (double el : roots) {
                    if (!RootChecker.isExpressionRoot(expression, el)) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    System.out.println("Шукане рівняння: " + expression);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
            System.err.println(e.getMessage());
        }

    }

}
