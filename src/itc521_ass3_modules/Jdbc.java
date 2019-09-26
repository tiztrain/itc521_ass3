package itc521_ass3_modules;

import java.sql.*;

public class Jdbc {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Loaded the MySQL JDBC Driver");

        // Connect to a database
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gradeprocessing", "root", "admin");
        System.out.println("Successfully connected to Database");

        // Create a statement
        Statement statement = connection.createStatement();

        // Hardcode variables for now
        int id = 1234;
        String name = "bob";
        double quiz = 90;
        double a1 = 75;
        double a2 = 77;
        double exam = 68;
        double cumulativeMark = 79;
        String grade = "D";


//        String sql="";
//        sql = "INSERT INTO Java2 " + "VALUES (" + id + ",'" + name + "'," + quiz + "," + a1 + "," + a2 + "," + exam + "," + cumulativeMark + ",'" + grade + "')";
//        statement.execute(sql);
//        sql ="INSERT INTO Java2 " + "VALUES (6111, 'Test', 90, 90, 90, 90, 90, 'HD')";
//        statement.execute(sql);

        // Execute a statement
        ResultSet resultSet = statement.executeQuery("select * from Java2");
        System.out.println("Inserted into database");
    }
}
