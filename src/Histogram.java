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
    public int[] data1 = new int[DATA_SIZE];
    public int[] data2 = new int[DATA_SIZE];
    public int[] data3 = new int[DATA_SIZE];


    @Override
    public void start(Stage primaryStage) throws Exception {

        // gets 30 random numbers and duplicates them into 3 lists
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

        //used if i wanted to put an axis on the chart
//        xAxis.setLabel("X Axis");
//        yAxis.setLabel("Y Axis");

        // creates the xy series
        XYChart.Series selectionChart = new XYChart.Series();
        selectionChart.setName("Selection Sort");
        XYChart.Series insertionChart = new XYChart.Series();
        insertionChart.setName("Insertion Sort");
        XYChart.Series bubbleChart = new XYChart.Series();
        bubbleChart.setName("Bubble Sort");

        // create bar chart and add in pane
        barChartSelect.getData().addAll(selectionChart);
        barChartInsert.getData().addAll(insertionChart);
        barChartBubble.getData().addAll(bubbleChart);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(barChartSelect, barChartInsert, barChartBubble);

        // create tasks for different sorting methods
        Runnable selSortAlgo = new SelectionSorting(data1, selectionChart);
        Runnable insSortAlgo = new InsertionSorting(data2, insertionChart);
        Runnable bubSortAlgo = new BubbleSorting(data3, bubbleChart);

        // create a thread for each sorting task
        Thread selThread = new Thread(selSortAlgo);
        Thread insThread = new Thread(insSortAlgo);
        Thread bubThread = new Thread(bubSortAlgo);

        // start threads
        selThread.start();
        insThread.start();
        bubThread.start();


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
            data1[i] = random.nextInt(DATA_SIZE);
            // create 3 of the same lists to adjust the values based on the sort type
            data2[i] = data1[i];
            data3[i] = data1[i];
        }
    }
}

class SelectionSorting implements Runnable {
    private int[] data;
    private XYChart.Series sortChart;


    public SelectionSorting(int[] d, XYChart.Series s) {
        data = d;
        sortChart = s;
    }


    @Override
    public void run() {
        try {
            int i;
            int runTime = 0;
            for (i = 0; i <= data.length - 1; i++) {
                // used to see what was running when
                System.out.println("SELECTION RUNTIME: " + runTime);
                runTime++;

                // the selection sorting algorithm
                int index = i;
                for (int j = i + 1; j < data.length; j++)
                    if (data[j] < data[index])
                        index = j;
                int smallerNumber = data[index];
                data[index] = data[i];
                data[i] = smallerNumber;

                // displays the chart in a inner loop
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 30; i++) {
                            System.out.println("selection data = " + data[i]);
                            sortChart.getData().add(new XYChart.Data(String.valueOf(i), data[i]));
                        }
                    }
                });

                // once complete, puts the thread on hold
                Thread.sleep(800);
            }
        }//end outer for loop}//end selection sort
        catch (InterruptedException ex) {
        }
    }
}


class InsertionSorting implements Runnable {
    private int[] data;
    private XYChart.Series sortChart;


    public InsertionSorting(int[] d, XYChart.Series s) {
        data = d;
        sortChart = s;
    }


    @Override
    public void run() {
        try {
            int i;
            int j;
            int runTime = 0;
            int temp;
            for (j = 1; j < data.length; j++) {
                // used to see what was running when
                System.out.println("                    INSERT RUNTIME: " + runTime);
                runTime++;

                // the insertion sorting algorithm
                temp = data[j];
                i = j; // range 0 to j-1 is sorted
                while (i > 0 && data[i - 1] >= temp) {
                    data[i] = data[i - 1];
                    i--;
                }
                data[i] = temp;
                //} // end outer for loop

                // displays the chart in a inner loop
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 30; i++) {
                            System.out.println("                    insertion data = " + data[i]);
                            sortChart.getData().add(new XYChart.Data(String.valueOf(i), data[i]));
                        }
                    }
                });

                // once complete, puts the thread on hold
                Thread.sleep(800);
            }
        }//end outer for loop}//end selection sort
        catch (InterruptedException ex) {
        }
    }
}


class BubbleSorting implements Runnable {
    private int[] data;
    private XYChart.Series sortChart;


    public BubbleSorting(int[] d, XYChart.Series s) {
        data = d;
        sortChart = s;
    }


    @Override
    public void run() {
        try {
            int i;
            int j;
            int runTime = 0;
            int temp;
            for (i = data.length - 1; i > 0; i--) {
                // used to see what was running when
                System.out.println("                                            BUBBLE RUNTIME: " + runTime);
                runTime++;

                // the bubble sorting algorithm
                for (j = 0; j < i; j++) {
                    if (data[j] > data[j + 1]) {
                        temp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                    }
                }// end inner for loop

                // displays the chart in a inner loop
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 30; i++) {
                            System.out.println("                                            bubble data = " + data[i]);
                            sortChart.getData().add(new XYChart.Data(String.valueOf(i), data[i]));
                        }
                    }
                });

                // once complete, puts the thread on hold
                Thread.sleep(800);
            }
        }//end outer for loop}//end selection sort
        catch (InterruptedException ex) {
        }
    }
}