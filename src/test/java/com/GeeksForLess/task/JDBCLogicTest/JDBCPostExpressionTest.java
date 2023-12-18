package com.GeeksForLess.task.JDBCLogicTest;

import com.GeeksForLess.task.JDBCLogic.JDBCPostExpression;
import com.GeeksForLess.task.expressionsCheackers.RootChecker;
import com.GeeksForLess.task.expressionsCheackers.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCPostExpressionTest {

    private static final String TESTURL = "jdbc:postgresql://localhost:8090/Expressions";
    private static final String TESTUSERNAME = "postgres";
    private static final String TESTPASSWORD = "root";

    @Test
    public void testConstructorWithExpression() {
        try {

            String testExpression = "Test Expression";

            JDBCPostExpression jdbcPostExpression = new JDBCPostExpression(testExpression);
            assertTrue(expressionInDatabase(testExpression));

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testConstructorWithExpressionAndRoot() {
        try {

            String testExpression = "x=2";
            double testRoot = 2.0;
            if (Validator.expressionIsValid(testExpression)&& RootChecker.isExpressionRoot(testExpression,testRoot)) {

                JDBCPostExpression jdbcPostExpression = new JDBCPostExpression(testExpression, testRoot);

                assertTrue(expressionInDatabase(testExpression));
                assertTrue(rootInDatabase(testRoot));
            }
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }


    private boolean expressionInDatabase(String expression) {
        try (Connection connection = DriverManager.getConnection(TESTURL, TESTUSERNAME, TESTPASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT correct_expression FROM expressions WHERE correct_expression = (?);");
            statement.setString(1, expression);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            fail("Exception in expressionInDatabase: " + e.getMessage());
            return false;
        }
    }

    private boolean rootInDatabase(double root) {
        try (Connection connection = DriverManager.getConnection(TESTURL, TESTUSERNAME, TESTPASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT root FROM expression_root WHERE root = (?);");
            statement.setDouble(1, root);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            fail("Exception in rootInDatabase: " + e.getMessage());
            return false;
        }
    }
}
