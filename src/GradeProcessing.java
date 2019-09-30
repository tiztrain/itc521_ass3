import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

    // Create buttons for actions
    private Button btCalculateRecord = new Button("Calculate Record");
    private Button btSearchRecord = new Button("Search for Record");
    private Button btInsertRecord = new Button("Insert Record");
    private Button btUpdateRecord = new Button("Update Record");

    //public Jdbc jdbc;

    // Main class to run functions
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        //run jdbc and establish connection
//        Jdbc jdbc = new Jdbc();
//        jdbc.setup();
        //setup stage and GUI
        launch(args);
    }

    // GUI setup
    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
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
        gridPane.add(btCalculateRecord, 1, 9);
        gridPane.add(btSearchRecord, 1, 10);
        gridPane.add(btInsertRecord, 1, 11);
        gridPane.add(btUpdateRecord, 1, 12);

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
        final Student[] student = new Student[1];
        // Process events
        btCalculateRecord.setOnAction(e -> student[0] = calculateGrade());
        btInsertRecord.setOnAction(e -> insertStudent(student[0]));


        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 600);
        primaryStage.setTitle("GradeProcessing"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }


    public void insertStudent(Student student) { //throws SQLException, ClassNotFoundException{
        // Initialise database
        Jdbc jdbc = new Jdbc();

        // used this try catch as SQLException not working correct with Lambda
        Connection connection = null;
        try {
            connection = jdbc.setup();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Create a statement object, required this try/catch due to Lambda not liking the SQLException
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        System.out.println(sql);

        // Put data into the database, required this try catch due to Lambda not liking the SQLException
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student " + id + " inserted into database");

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

        return student;
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
            this.cumulativeMark = (quizMark * 0.05) + (a1Mark * 0.15) + (a2Mark * 0.2) + (examMark * 0.6);
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

