import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc extends GradeProcessing {
    private Connection connection;

    public Connection setup() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Loaded the MySQL JDBC Driver");

        // Connect to a database
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gradeprocessing", "root", "admin");
        System.out.println("Successfully connected to Database");

        return connection;
    }

}
