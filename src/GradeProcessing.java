import itc521_ass3_modules.Jdbc;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

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

    // Main class to run functions
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //run jdbc and establish connection
        Jdbc.main(args);
        //setup stage and GUI
        launch(args);
    }

    // GUI setup
    @Override
    public void start(Stage primaryStage) {
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


        // Process events
        btCalculateRecord.setOnAction(e -> calculateGrade());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 600);
        primaryStage.setTitle("GradeProcessing"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }

    private void calculateGrade() {
        // Assign variables to values in text fields
        double quizMark = Double.parseDouble(quiz.getText());
        double a1Mark = Double.parseDouble(a1.getText());
        double a2Mark = Double.parseDouble(a2.getText());
        double examMark = Double.parseDouble(exam.getText());

        // Initialise student object
        Student student = new Student(quizMark, a1Mark, a2Mark, examMark);

        // Calculate total marks and allocate to variable
        double totalMark = student.getTotalMark();
        // Assigned total mark to student
        student.setTotalMark(totalMark);
        // Calculate grade result off total mark
        String result = student.getResult();
        // Display results in non editable fields
        cumulativeMark.setText(String.valueOf(totalMark));
        grade.setText(result);


        // Get values from text fields
//        double quizMark = Double.parseDouble(quiz.getText());
//        double a1Mark = Double.parseDouble(a1.getText());
//        double a2Mark = Double.parseDouble(a2.getText());
//        double examMark = Double.parseDouble(exam.getText());
//        double totalMark;
//        String result;
//
//        totalMark = (quizMark * 0.05) + (a1Mark * 0.15) + (a2Mark * 0.2) + (examMark * 0.6);
//
//        if (totalMark < 50) {
//            result = "Fail";
//        } else if (totalMark >= 50 && totalMark < 65) {
//            result = "Pass";
//        } else if (totalMark >= 65 && totalMark < 75) {
//            result = "CR";
//        } else if (totalMark >= 75 && totalMark < 85) {
//            result = "DI";
//        } else
//            result = "HD";
//
//        cumulativeMark.setText(String.valueOf(totalMark));
//        grade.setText(result);
    }

    class Student {
        // Get values from text fields
        double quizMark;
        double a1Mark;
        double a2Mark;
        double examMark;
        double totalMark;
        String result;

//        public Student() {
//            this.quizMark = quizMark;
//            this.a1Mark = a1Mark;
//            this.a2Mark = a2Mark;
//            this.examMark = examMark;
//        }

        public Student(double quizMark, double a1Mark, double a2Mark, double examMark) {
            this.quizMark = quizMark;
            this.a1Mark = a1Mark;
            this.a2Mark = a2Mark;
            this.examMark = examMark;
        }

        public double getQuizMark() {
            return quizMark;
        }

        public void setQuizMark(double quizMark) {
            this.quizMark = quizMark;
        }

        public double getA1Mark() {
            return a1Mark;
        }

        public void setA1Mark(double a1Mark) {
            this.a1Mark = a1Mark;
        }

        public double getA2Mark() {
            return a2Mark;
        }

        public void setA2Mark(double a2Mark) {
            this.a2Mark = a2Mark;
        }

        public double getExamMark() {
            return examMark;
        }

        public void setExamMark(double examMark) {
            this.examMark = examMark;
        }

        public double getTotalMark() {
            return (quizMark * 0.05) + (a1Mark * 0.15) + (a2Mark * 0.2) + (examMark * 0.6);
        }

        public void setTotalMark(double totalMark) {
            this.totalMark = totalMark;
        }

        public String getResult() {
            if (totalMark < 50) {
                result = "Fail";
            } else if (totalMark >= 50 && totalMark < 65) {
                result = "Pass";
            } else if (totalMark >= 65 && totalMark < 75) {
                result = "CR";
            } else if (totalMark >= 75 && totalMark < 85) {
                result = "DI";
            } else
                result = "HD";
            return result;
        }

    }
}

