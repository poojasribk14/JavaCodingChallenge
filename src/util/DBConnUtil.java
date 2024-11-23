package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import exception.DatabaseConnectionException;

public class DBConnUtil {
    
    // Method to establish a connection with the database
    public static Connection getConnection() throws DatabaseConnectionException {
        Properties properties = DBPropertyUtil.loadProperties();  // Load the database properties
        String dbURL = properties.getProperty("url");
        String dbUser = properties.getProperty("user");
        String dbPassword = properties.getProperty("password");
        
        try {
            // Load the database driver (for MySQL)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionException("Error connecting to the database", e);
        }
    }
    
    // Method to close the database connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
