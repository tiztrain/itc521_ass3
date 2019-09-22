package itc521_ass3_modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Loaded the MySQL JDBC Driver");

        // Connect to a database
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gradeprocessing", "root", "admin");
        System.out.println("Successfully connected to Database");

    }
}
