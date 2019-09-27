import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc extends GradeProcessing {
    Student student = new Student();
    private Connection connection;


    public void setup() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Loaded the MySQL JDBC Driver");

        // Connect to a database
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gradeprocessing", "root", "admin");
        System.out.println("Successfully connected to Database");
    }

    public void insertStudent() throws SQLException, ClassNotFoundException {

        // Create a statement
        Statement statement = connection.createStatement();

        int id = student.getId();
        String name = student.getName();
        double quiz = student.getQuizMark();
        double a1 = student.getA1Mark();
        double a2 = student.getA2Mark();
        double exam = student.getExamMark();
        double cumulativeMark = student.getCumulativeMark();
        String grade = student.getGrade();

        String sql = "";
        sql = "INSERT INTO Java2 " + "VALUES (" + id + ",'" + name + "'," + quiz + "," + a1 + "," + a2 + "," + exam + "," + cumulativeMark + ",'" + grade + "')";
        statement.execute(sql);
        System.out.println("Student inserted into database");
    }

//        String sql="";
//        sql = "INSERT INTO Java2 " + "VALUES (" + id + ",'" + name + "'," + quiz + "," + a1 + "," + a2 + "," + exam + "," + cumulativeMark + ",'" + grade + "')";
//        statement.execute(sql);
//        System.out.println("Inserted into database");
//        sql ="INSERT INTO Java2 " + "VALUES (6111, 'Test', 90, 90, 90, 90, 90, 'HD')";
//        statement.execute(sql);


}
