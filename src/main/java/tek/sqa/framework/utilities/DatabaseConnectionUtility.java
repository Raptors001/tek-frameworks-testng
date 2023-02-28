package tek.sqa.framework.utilities;

import com.aventstack.extentreports.service.ExtentTestManager;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DatabaseConnectionUtility {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseConnectionUtility(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() {
        try {
            ExtentTestManager.getTest().info("Making Connection to Database");
            System.out.println("Making Connection to Database");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            ExtentTestManager.getTest().fail("Failed Making Connection to Database with message " + e.getMessage());
            throw new RuntimeException("Error Connecting to Database");
        }
    }

    private Statement getConnectionStatement() {
        try {
            ExtentTestManager.getTest().info("Creating Connection Statement");
            System.out.println("Creating Connection Statement");
            return getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            throw new RuntimeException("Error Error Creating Statement");
        }
    }

    public ResultSet executeQuery(String query) {
        Statement statement;
        try {
            ExtentTestManager.getTest().info("Executing Query " + query);
            System.out.println("Executing Query " + query);
            statement = this.getConnectionStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            ExtentTestManager.getTest().fail("Error Executing Query with message " + e.getMessage());
            throw new RuntimeException("Error executing query");
        }
    }

    public List<Map<String, Object>> convertResultToMap(String query) {
        Statement statement = null;
        try {
            List<Map<String, Object>> list = new LinkedList<>();

            ResultSet resultSet = this.executeQuery(query);
            statement = resultSet.getStatement();
            ResultSetMetaData metadata = resultSet.getMetaData();
            int columns = metadata.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columns; i++) {
                    map.put(metadata.getColumnName(i), resultSet.getObject(i));
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            throw new RuntimeException("Error executing query");
        }finally {
            if (statement != null) {
                try {
                    ExtentTestManager.getTest().info("Attempting to close all database connections");
                    statement.getConnection().close();
                    statement.close();
                } catch (SQLException e) {
                    ExtentTestManager.getTest().fail("Failed to Close Connections " + e.getMessage());
                }
            }
        }
    }

}
