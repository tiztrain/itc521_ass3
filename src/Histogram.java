import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Histogram extends Application {

    int DATA_SIZE = 30;
    int[] data = new int[DATA_SIZE];


    @Override
    public void start(Stage primaryStage) throws Exception {

        prepareData();

//        SelSortChart selSortThread = new SelSortChart();
//        selSortThread.selectionSort(data);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setCategoryGap(0);
        barChart.setBarGap(0);

//        xAxis.setLabel("X Axis");
//        yAxis.setLabel("Y Axis");

        XYChart.Series selectionSort = new XYChart.Series();
        selectionSort.setName("Selection Sort");

        for (int i = 0; i < DATA_SIZE; i++) {
            selectionSort.getData().add(new XYChart.Data(String.valueOf(i), data[i]));
        }

        barChart.getData().addAll(selectionSort);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(barChart);

        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 800, 450);

        primaryStage.setTitle("Multithreading for Sorting Methods");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    //create random integers for charts
    private void prepareData() {

        Random random = new Random();
        for (int i = 0; i < DATA_SIZE; i++) {
            data[i] = random.nextInt(DATA_SIZE);
        }
    }
}


class SelSortChart implements Runnable {
    private int lastNum;

    public SelSortChart(int dataSize) {
        lastNum = dataSize;
    }

    void selectionSort(int[] data) {
        int i, j, temp, pos_greatest;
        for (i = data.length - 1; i > 0; i--) {
            pos_greatest = 0;
            for (j = 0; j <= i; j++) {
                if (data[j] > data[pos_greatest])
                    pos_greatest = j;
            }//end inner for loop
            temp = data[i];
            data[i] = data[pos_greatest];
            data[pos_greatest] = temp;
        }//end outer for loop}//end selection sort
    }

    @Override
    /** Override the run() method to tell the system
     *  what the task to perform
     */
    public void run() {

        //Thread thread = new Thread(new selectionSort)
    }
}


