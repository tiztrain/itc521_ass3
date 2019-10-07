import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class GradeProcessing extends Application {

    // Create text field boxes
    private TextField id = new TextField();
    private TextField name = new TextField();
    private TextField quiz = new TextField();
    private TextField a1 = new TextField();
    private TextField a2 = new TextField();
    private TextField exam = new TextField();
    private TextField cumulativeMark = new TextField();
    private TextField grade = new TextField();
    //private TextField confirmMsg = new TextField();
    private Label confirmMsg = new Label();

    // Create buttons for actions
    private Button btCalculateGrade = new Button("Calculate Grade");
    private Button btSearchStudent = new Button("Search for Student");
    private Button btInsertStudent = new Button("Insert Student");
    private Button btUpdateStudent = new Button("Update Student");

    private Statement stmt;

    // Main class to run functions
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }

    // GUI setup
    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        initializeDB();

        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(id, 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(name, 1, 1);
        gridPane.add(new Label("Quiz mark:"), 0, 2);
        gridPane.add(quiz, 1, 2);
        gridPane.add(new Label("Assignment 1 mark:"), 0, 3);
        gridPane.add(a1, 1, 3);
        gridPane.add(new Label("Assignment 2 mark:"), 0, 4);
        gridPane.add(a2, 1, 4);
        gridPane.add(new Label("Exam mark:"), 0, 5);
        gridPane.add(exam, 1, 5);
        gridPane.add(new Label("Cumulative Mark:"), 0, 6);
        gridPane.add(cumulativeMark, 1, 6);
        gridPane.add(new Label("Grade:"), 0, 7);
        gridPane.add(grade, 1, 7);
        gridPane.add(btCalculateGrade, 1, 9);
        gridPane.add(btSearchStudent, 1, 10);
        gridPane.add(btInsertStudent, 1, 11);
        gridPane.add(btUpdateStudent, 1, 12);
        confirmMsg.setText("Confirmation messages will show here");
        gridPane.add(confirmMsg, 1, 13);


        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        id.setAlignment(Pos.BOTTOM_RIGHT);
        name.setAlignment(Pos.BOTTOM_RIGHT);
        quiz.setAlignment(Pos.BOTTOM_RIGHT);
        a1.setAlignment(Pos.BOTTOM_RIGHT);
        a2.setAlignment(Pos.BOTTOM_RIGHT);
        exam.setAlignment(Pos.BOTTOM_RIGHT);
        cumulativeMark.setEditable(false);
        grade.setEditable(false);

        // create one object student array to work with Lambda
        final Student[] oneStudent = new Student[1];
        Student student = new Student();

        // Process events
        btCalculateGrade.setOnAction(e -> oneStudent[0] = calculateGrade());
        btInsertStudent.setOnAction(e -> insertStudent(student));
        btSearchStudent.setOnAction(e -> searchStudent(student));
        btUpdateStudent.setOnAction(e -> updateStudent(student));


        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 500, 600);
        primaryStage.setTitle("GradeProcessing"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }


    public boolean confirmCorrectValues(Student student) {
        boolean result;

        student.setId();
        student.setName();
        student.setQuizMark();
        student.setA1Mark();
        student.setA2Mark();
        student.setExamMark();
        student.setCumulativeMark();
        student.setGrade();

        int id = student.getId();
        double quiz = student.getQuizMark();
        double a1 = student.getA1Mark();
        double a2 = student.getA2Mark();
        double exam = student.getExamMark();


        int length = (int) (Math.log10(id) + 1);
        System.out.println("The number of digits are: " + length);
        System.out.println("ID = " + id);
        if (length != 8) {
            result = false;
            confirmMsg.setText("Please make sure the ID entered \nis 8 digits in length and try again");
            System.out.println("Please make sure the ID entered is 8 digits in length and try again");
        } else if (id <= 0) {
            result = false;
            confirmMsg.setText("Please make sure the ID is greater \nthan zero and try again");
            System.out.println("Please make sure the ID is greater than zero and try again");
        } else if (quiz < 0 || quiz > 100) {
            result = false;
            confirmMsg.setText("Please enter a number for quiz results \nthat is between 0 and 100");
            System.out.println("Please enter a number for quiz results that is between 0 and 100");
        } else if (a1 < 0 || a1 > 100) {
            result = false;
            confirmMsg.setText("Please enter a number for assignment 1 \nresults that is between 0 and 100");
            System.out.println("Please enter a number for assignment 1 results that is between 0 and 100");
        } else if (a2 < 0 || a2 > 100) {
            result = false;
            confirmMsg.setText("Please enter a number for assignment 2 \nresults that is between 0 and 100");
            System.out.println("Please enter a number for assignment 2 results that is between 0 and 100");
        } else if (exam < 0 || exam > 100) {
            result = false;
            confirmMsg.setText("Please enter a number for exam results \nthat is between 0 and 100");
            System.out.println("Please enter a number for exam results that is between 0 and 100");
        } else {
            result = true;
        }
        System.out.println("Result = " + result);
        return result;
    }


    public void test() {
        System.out.println("PRINT");
    }


    public void insertStudent(Student student) {

        student.setId();
        student.setName();
        student.setQuizMark();
        student.setA1Mark();
        student.setA2Mark();
        student.setExamMark();
        student.setCumulativeMark();
        student.setGrade();

        int id = student.getId();
        String name = student.getName();
        double quiz = student.getQuizMark();
        double a1 = student.getA1Mark();
        double a2 = student.getA2Mark();
        double exam = student.getExamMark();
        double cumulativeMark = student.getCumulativeMark();
        String grade = student.getGrade();


        String sql = "";
        sql = String.format("INSERT INTO Java2 VALUES (%s, '%s', %s, %s, %s, %s, %s, '%s')",
                id, name, quiz, a1, a2, exam, cumulativeMark, grade);
        System.out.println(sql);

        // Put data into the database, required this try catch due to Lambda not liking the SQLException
        try {
            // checks if values in correct formatting
            if (confirmCorrectValues(student)) {
                stmt.execute(sql);
                System.out.println("Student " + id + " inserted into database");
                confirmMsg.setText("Student " + id + " inserted into database");
            }
//            else{
//                confirmMsg.setText("Error. Student was not entered into the database. Please try again.");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchStudent(Student student) {

        student.setId();
        String id = String.valueOf(student.getId());

        try {
            //ResultSet resultSet = statement.executeQuery ("select * from Java2");
            ResultSet resultSet = stmt.executeQuery("SELECT * from Java2 WHERE ID = '" + id + "'");
            String result = "";
            ResultSetMetaData rsmd;
            int columnsNumber = 0;
            // Iterate through the result and print the student names
            while (resultSet.next()) {
                rsmd = resultSet.getMetaData();
                columnsNumber = rsmd.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    result = resultSet.getString(i);
//                    if (i == 1) {
//                        id.setText(result);
//                        student.getId();
//                    }
                    if (i == 2) {
                        name.setText(result);
                        student.getName();
                    } else if (i == 3) {
                        quiz.setText(result);
                        student.getQuizMark();
                    } else if (i == 4) {
                        a1.setText(result);
                        student.getA1Mark();
                    } else if (i == 5) {
                        a2.setText(result);
                        student.getA2Mark();
                    } else if (i == 6) {
                        exam.setText(result);
                        student.getExamMark();
                    } else if (i == 7) {
                        cumulativeMark.setText(result);
                        student.getCumulativeMark();
                    } else if (i == 8) {
                        grade.setText(result);
                        student.getGrade();
                    }
                    System.out.print(result + "\t");
                    //result = "";
                }
                System.out.println();
            }
            // displays the message in the GUI if the student is found or not
            if (result.equals("")) {
                confirmMsg.setText("Student not found. \nPlease enter another ID and try again.");
            } else {
                confirmMsg.setText("Found student " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {

        student.setId();
        student.setName();
        student.setQuizMark();
        student.setA1Mark();
        student.setA2Mark();
        student.setExamMark();
        student.setCumulativeMark();
        student.setGrade();

        int id = student.getId();
        String name = student.getName();
        double quiz = student.getQuizMark();
        double a1 = student.getA1Mark();
        double a2 = student.getA2Mark();
        double exam = student.getExamMark();
        double cumulativeMark = student.getCumulativeMark();
        String grade = student.getGrade();

        String sql = "";
        sql = String.format("UPDATE Java2 SET ID = %s, Name = '%s', Quiz = %s, A1 = %s, A2 = %s, Exam = %s, " +
                        "CumulativeMark = %s, Grade = '%s' WHERE ID = '%s'",
                id, name, quiz, a1, a2, exam, cumulativeMark, grade, id);
        System.out.println(sql);

        // Put data into the database, required this try catch due to Lambda not liking the SQLException
        try {
            // checks if values in correct formatting
            if (confirmCorrectValues(student)) {
                stmt.execute(sql);
                System.out.println("Student " + id + " updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student " + id + " " + name + " updated");
        confirmMsg.setText("Updated student details");

    }

    public Student calculateGrade() {

        // Initialise student object
        Student student = new Student();

        // Setup all the setters
        student.setId();
        student.setName();
        student.setQuizMark();
        student.setA1Mark();
        student.setA2Mark();
        student.setExamMark();
        student.setCumulativeMark();
        student.setGrade();

        // Calculate variable from student getters
        double cumulativeMarkText = student.getCumulativeMark();
        String gradeText = student.getGrade();

        // Display results in non editable fields
        cumulativeMark.setText(String.valueOf(cumulativeMarkText));
        grade.setText(gradeText);

        confirmMsg.setText("Grade Calculated");

        return student;
    }

    private void initializeDB() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Loaded the MySQL JDBC Driver");

            // Connect to a database
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gradeprocessing",
                    "root", "admin");
            System.out.println("Successfully connected to Database");

            // Create statement
            stmt = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public class Student {
        // Get values from text fields
        int idText;
        String nameText;
        double quizMark;
        double a1Mark;
        double a2Mark;
        double examMark;
        double cumulativeMark;
        String grade;

        public Student() {

        }

        public int getId() {
            return idText;
        }

        public void setId() {
            this.idText = Integer.parseInt(id.getText());
        }

        public String getName() {
            return nameText;
        }

        public void setName() {
            this.nameText = name.getText();
        }

        public double getQuizMark() {
            return quizMark;
        }

        public void setQuizMark() {
            this.quizMark = Double.parseDouble(quiz.getText());
        }

        public double getA1Mark() {
            return a1Mark;
        }

        public void setA1Mark() {
            this.a1Mark = Double.parseDouble(a1.getText());
        }

        public double getA2Mark() {
            return a2Mark;
        }

        public void setA2Mark() {
            this.a2Mark = Double.parseDouble(a2.getText());
        }

        public double getExamMark() {
            return examMark;
        }

        public void setExamMark() {
            this.examMark = Double.parseDouble(exam.getText());
        }

        public double getCumulativeMark() {
            return cumulativeMark;
        }

        public void setCumulativeMark() {
            // converts the result to 2 decimal places
            this.cumulativeMark = Math.round(((quizMark * 0.05) + (a1Mark * 0.15) + (a2Mark * 0.2) + (examMark * 0.6)) * 100.0) / 100.0;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade() {
            if (cumulativeMark < 50) {
                grade = "Fail";
            } else if (cumulativeMark >= 50 && cumulativeMark < 65) {
                grade = "Pass";
            } else if (cumulativeMark >= 65 && cumulativeMark < 75) {
                grade = "CR";
            } else if (cumulativeMark >= 75 && cumulativeMark < 85) {
                grade = "DI";
            } else
                grade = "HD";
            this.grade = grade;
        }
    }
}

