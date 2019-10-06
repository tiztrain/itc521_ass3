import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Histogram extends Application {

    int DATA_SIZE = 30;
    public int[] data = new int[DATA_SIZE];


    @Override
    public void start(Stage primaryStage) throws Exception {

        prepareData();

        // set axis to charts
        final CategoryAxis xAxisSel = new CategoryAxis();
        final NumberAxis yAxisSel = new NumberAxis();
        final CategoryAxis xAxisIns = new CategoryAxis();
        final NumberAxis yAxisIns = new NumberAxis();
        final CategoryAxis xAxisBub = new CategoryAxis();
        final NumberAxis yAxisBub = new NumberAxis();

        // create bar charts
        final BarChart<String, Number> barChartSelect = new BarChart<>(xAxisSel, yAxisSel);
        final BarChart<String, Number> barChartInsert = new BarChart<>(xAxisIns, yAxisIns);
        final BarChart<String, Number> barChartBubble = new BarChart<>(xAxisBub, yAxisBub);

        // sets bar charts to be touching and full in size
        barChartSelect.setCategoryGap(0);
        barChartSelect.setBarGap(0);
        barChartInsert.setCategoryGap(0);
        barChartInsert.setBarGap(0);
        barChartBubble.setCategoryGap(0);
        barChartBubble.setBarGap(0);


        //removes the flashing full bar chart issue
        barChartSelect.setAnimated(false);
        barChartInsert.setAnimated(false);
        barChartBubble.setAnimated(false);

//        xAxis.setLabel("X Axis");
//        yAxis.setLabel("Y Axis");

        // creates the xy series
        XYChart.Series selectionSort = new XYChart.Series();
        selectionSort.setName("Selection Sort");
        XYChart.Series insertionSort = new XYChart.Series();
        insertionSort.setName("Insertion Sort");
        XYChart.Series bubbleSort = new XYChart.Series();
        bubbleSort.setName("Bubble Sort");

        // create bar chart and add in pane
        barChartSelect.getData().addAll(selectionSort);
        barChartInsert.getData().addAll(insertionSort);
        barChartBubble.getData().addAll(bubbleSort);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(barChartSelect, barChartInsert, barChartBubble);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i;
                    int runTime = 0;
                    for (i = 0; i <= data.length - 1; i++) {

                        System.out.println("SELECTION RUNTIME: " + runTime);
                        runTime++;

                        int index = i;
                        for (int j = i + 1; j < data.length; j++)
                            if (data[j] < data[index])
                                index = j;

                        int smallerNumber = data[index];
                        data[index] = data[i];
                        data[i] = smallerNumber;

                        // potentially need to add the display chart functionality here
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < DATA_SIZE; i++) {
                                    System.out.println("selection data = " + data[i]);
                                    selectionSort.getData().add(new XYChart.Data(String.valueOf(i), data[i]));
                                }
                            }
                        });

                        Thread.sleep(200);
                    }
                }//end outer for loop}//end selection sort
                catch (InterruptedException ex) {
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i;
                    int j;
                    int runTime = 0;
                    int temp;
                    for (j = 1; j < data.length - 1; j++) {

                        System.out.println("    INSERT RUNTIME: " + runTime);
                        runTime++;

                        temp = data[j];
                        i = j; // range 0 to j-1 is sorted
                        while (i > 0 && data[i - 1] >= temp) {
                            data[i] = data[i - 1];
                            i--;
                        }
                        data[i] = temp;
                        //} // end outer for loop

                        // potentially need to add the display chart functionality here
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < DATA_SIZE; i++) {
                                    System.out.println("    insertion data = " + data[i]);
                                    insertionSort.getData().add(new XYChart.Data(String.valueOf(i), data[i]));
                                }
                            }
                        });

                        Thread.sleep(200);
                    }
                }//end outer for loop}//end selection sort
                catch (InterruptedException ex) {
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i;
                    int j;
                    int runTime = 0;
                    int temp;
                    for (i = data.length - 1; i > 0; i--) {

                        System.out.println("        BUBBLE RUNTIME: " + runTime);
                        runTime++;

                        for (j = 0; j < i; j++) {
                            if (data[j] > data[j + 1]) {
                                temp = data[j];
                                data[j] = data[j + 1];
                                data[j + 1] = temp;
                            }
                        }// end inner for loop

                        // potentially need to add the display chart functionality here
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < DATA_SIZE; i++) {
                                    System.out.println("        bubble data = " + data[i]);
                                    bubbleSort.getData().add(new XYChart.Data(String.valueOf(i), data[i]));
                                }
                            }
                        });

                        Thread.sleep(200);
                    }
                }//end outer for loop}//end selection sort
                catch (InterruptedException ex) {
                }
            }
        }).start();


        // create scene and place on stage
        Scene scene = new Scene(vBox, 800, 1000);

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





