package edu.mohamed.restaurantfinder.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLTest {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8775734";
        String username = "sql8775734";
        String password = "qTQWmUFHUr";//"Beta23A1pha";
        
        // Try to establish a connection
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Successfully connected to MySQL database!");
            
            // Optional: Print some database metadata
            System.out.println("\nDatabase Information:");
            System.out.println("Database name: " + connection.getCatalog());
            System.out.println("Database version: " + connection.getMetaData().getDatabaseProductVersion());
            
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            System.err.println("Error message: " + e.getMessage());
        }
    }
}
