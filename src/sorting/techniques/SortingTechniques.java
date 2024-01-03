package sorting.techniques;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SortingTechniques extends Application implements Initializable {

    @FXML
    Label arrays_data, title;
    @FXML
    TextField sizeArray;
    @FXML
    BarChart<String, Number> chart, chart1, chart2;
    @FXML
    ChoiceBox<String> menu;
    ObservableList list = FXCollections.observableArrayList();

    static String test = "", test1 = "", test2 = "", Title = "", res22 = "";

    XYChart.Series<String, Number> series1, series2, series3;
    XYChart.Series<String, Number> series11, series22, series33;
    XYChart.Series<String, Number> series111, series222, series333;

    public int Counts_of_interchange = 0;
    public int Counts_of_comparisons = 0;
    boolean check = false;
    int[] RandomArray, RandomArraySorted, RandomArrayReversed;

    @Override
    public void start(Stage primaryStage) {

        FXMLLoader fxmlLoader2 = new FXMLLoader(SortingTechniques.class.getResource("/sorting/techniques/Home.fxml"));
        Scene scene2 = null;
        try {
            scene2 = new Scene(fxmlLoader2.load());
        } catch (IOException ex) {
            Logger.getLogger(SortingTechniques.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setTitle("Alexandria University");
        primaryStage.setScene(scene2);

        primaryStage.show();

        primaryStage.setOnCloseRequest((event) -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure you want to close this app?");
            alert.setContentText("Close App?");
            alert.setHeaderText("");
            if (alert.showAndWait().get() == ButtonType.OK) {
                System.exit(0);
            }
            event.consume();
        });

    }

    public void generate(ActionEvent event) throws IOException {
        check = true;
        if (sizeArray.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Enter size of the array");
            alert.showAndWait();
            title.setText("");
        } else {
            title.setText("Array: \n\nSorted: \n\nInversely sorted : ");

            int si = Integer.parseInt(sizeArray.getText());
            RandomArray = generateRandomArray(si);
            printArr(RandomArray);

            RandomArraySorted = new int[RandomArray.length];
            RandomArrayReversed = new int[RandomArray.length];
            System.arraycopy(RandomArray, 0, RandomArraySorted, 0, RandomArray.length);
            Arrays.sort(RandomArraySorted);
            printArr(RandomArraySorted);

            RandomArrayReversed = reverse(RandomArraySorted, RandomArraySorted.length);
            printArr(RandomArrayReversed);
        }

    }

    public void visualize(ActionEvent event) throws IOException {
        Counts_of_interchange = 0;
        Counts_of_comparisons = 0;
        if (!sizeArray.getText().isEmpty() && check) {
            chart.getData().removeAll(Collections.singleton(chart.getData().setAll()));
            chart1.getData().removeAll(Collections.singleton(chart1.getData().setAll()));
            chart2.getData().removeAll(Collections.singleton(chart2.getData().setAll()));

            series1 = new XYChart.Series();
            series2 = new XYChart.Series();
            series3 = new XYChart.Series();
            series11 = new XYChart.Series();
            series22 = new XYChart.Series();
            series33 = new XYChart.Series();
            series111 = new XYChart.Series();
            series222 = new XYChart.Series();
            series333 = new XYChart.Series();

            int[] RandomArraySort = new int[RandomArray.length];
            System.arraycopy(RandomArray, 0, RandomArraySort, 0, RandomArray.length);

            int[] RandomArraySort2 = new int[RandomArrayReversed.length];
            System.arraycopy(RandomArrayReversed, 0, RandomArraySort2, 0, RandomArrayReversed.length);

            int[] RandomArraySort3 = new int[RandomArraySorted.length];
            System.arraycopy(RandomArraySorted, 0, RandomArraySort3, 0, RandomArraySorted.length);

            series1.setName("Random");
            series2.setName("inversely sorted");
            series3.setName("Sorted");

            series11.setName("Random");
            series22.setName("inversely sorted");
            series33.setName("Sorted");

            series111.setName("Random");
            series222.setName("inversely sorted");
            series333.setName("Sorted");

            if (menu.getSelectionModel().isSelected(0)) {

                double startTime = System.nanoTime();
                Title = "Bubble sort simuation:\n";
                test = "Random Array:\n\n";
                test += bubbleSort(RandomArraySort, "");
                series11.getData().add(new XYChart.Data("", Counts_of_interchange));
                series111.getData().add(new XYChart.Data("", Counts_of_comparisons));
                double endTime = System.nanoTime();
                double elapsedTime = endTime - startTime;

                test1 = "inversely sorted:\n";
                double startTime2 = System.nanoTime();
                test1 += bubbleSort(RandomArraySort2, "");
                series22.getData().add(new XYChart.Data("", Counts_of_interchange));
                series222.getData().add(new XYChart.Data("", Counts_of_comparisons));
                double endTime2 = System.nanoTime();
                double elapsedTime2 = endTime2 - startTime2;

                test2 = "Sorted:\n";
                double startTime1 = System.nanoTime();
                test2 += bubbleSort(RandomArraySort3, "");
                series33.getData().add(new XYChart.Data("", Counts_of_interchange));
                series333.getData().add(new XYChart.Data("", 0));
                double endTime1 = System.nanoTime();
                double elapsedTime1 = endTime1 - startTime1;

                series1.getData().add(new XYChart.Data("", elapsedTime / 1000000));
                series2.getData().add(new XYChart.Data("", elapsedTime1 / 1000000));
                series3.getData().add(new XYChart.Data("", elapsedTime2 / 1000000));

                chart.getData().addAll(series1, series2, series3);
                chart1.getData().addAll(series11, series22, series33);
                chart2.getData().addAll(series111, series222, series333);

            } else if (menu.getSelectionModel().isSelected(1)) {

                res22 = "";
                Title = "Quick sort simuation:\n";
                test = "Random Array:\n\n";
                double startTime = System.nanoTime();
                test += quickSort(RandomArraySort, 0, RandomArraySort.length - 1, "");
                series11.getData().add(new XYChart.Data("", Counts_of_interchange));
                series111.getData().add(new XYChart.Data("", Counts_of_comparisons));
                double endTime = System.nanoTime();
                double elapsedTime = endTime - startTime;

                res22 = "";
                test1 = "inversely sorted:\n\n";
                double startTime2 = System.nanoTime();
                test1 += quickSort(RandomArraySort2, 0, RandomArraySort2.length - 1, "");
                series22.getData().add(new XYChart.Data("", Counts_of_interchange));
                series222.getData().add(new XYChart.Data("", Counts_of_comparisons));
                double endTime2 = System.nanoTime();
                double elapsedTime2 = endTime2 - startTime2;

                res22 = "";
                test2 = "Sorted:\n\n";
                double startTime1 = System.nanoTime();
                test2 += quickSort(RandomArraySort3, 0, RandomArraySort3.length - 1, "");
                series33.getData().add(new XYChart.Data("", Counts_of_interchange));
                series333.getData().add(new XYChart.Data("", Counts_of_comparisons));
                double endTime1 = System.nanoTime();
                double elapsedTime1 = endTime1 - startTime1;

                series1.getData().add(new XYChart.Data("", elapsedTime / 1000000));
                series2.getData().add(new XYChart.Data("", elapsedTime1 / 1000000));
                series3.getData().add(new XYChart.Data("", elapsedTime2 / 1000000));

                chart.getData().addAll(series1, series2, series3);
                chart1.getData().addAll(series11, series22, series33);
                chart2.getData().addAll(series111, series222, series333);

            } else if (menu.getSelectionModel().isSelected(2)) {

                Title = "Count sort simuation:\n";
                test = "Random Array:\n\n";
                double startTime = System.nanoTime();
                test += countingSort(RandomArraySort); 
                double endTime = System.nanoTime();
                double elapsedTime = endTime - startTime;

                test1 = "inversely sorted:\n\n";
                double startTime2 = System.nanoTime();
                test1 += countingSort(RandomArraySort2); 
                double endTime2 = System.nanoTime();
                double elapsedTime2 = endTime2 - startTime2;

                test2 = "Sorted:\n\n";
                double startTime1 = System.nanoTime();
                test2 += countingSort(RandomArraySort3); 
                double endTime1 = System.nanoTime();
                double elapsedTime1 = endTime1 - startTime1;

                series1.getData().add(new XYChart.Data("", elapsedTime / 1000000));
                series2.getData().add(new XYChart.Data("", elapsedTime / 1000000));
                series3.getData().add(new XYChart.Data("", elapsedTime / 1000000));

                chart.getData().addAll(series1, series2, series3); 

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid data");
                alert.setHeaderText("Select Algorithm first");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Enter size of the array and generate it");
            alert.showAndWait();
        }
    }

    public void iterations(ActionEvent event) throws IOException {
        if (!sizeArray.getText().isEmpty() && check && (menu.getSelectionModel().isSelected(0)
                || menu.getSelectionModel().isSelected(1)
                || menu.getSelectionModel().isSelected(2))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sorting/techniques/iterations.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Report Page");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Generate and visualize First");
            alert.showAndWait();
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        Counts_of_interchange++;
    }

    public int[] reverse(int a[], int n) {
        int[] b = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }
        return b;
    }

    public void printArr(int[] arr) {
        String apostrophe = "";
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                apostrophe = " , ";
            }
            arrays_data.setText(arrays_data.getText() + apostrophe + arr[i] + " ");
        }

        arrays_data.setText(arrays_data.getText() + "\n\n");
    }

    public String printArr_Iterations(int[] arr, String res) {
        String apostrophe = "";
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                apostrophe = " , ";
            }
            res += apostrophe + arr[i] + " ";
        }
        res += "\n\n";
        return res;
    }

    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int pivot_index = low;

        for (int j = low; j <= high - 1; j++) {
            Counts_of_comparisons++;
            if (arr[j] <= pivot) {
                swap(arr, pivot_index, j);
                pivot_index++;
            }
        }
        swap(arr, pivot_index, high);
        return pivot_index;
    }

    public String quickSort(int[] arr, int low, int high, String res) {
        if (low >= high) {
            return res22;
        }
        res22 += printArr_Iterations(arr, "");
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1, res);
        quickSort(arr, pi + 1, high, res);
        return res22;
    }

    public String bubbleSort(int[] arr, String res) {
        Counts_of_interchange = 0;
        Counts_of_comparisons = 0;
        int n = arr.length;
        String res2 = "";
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                Counts_of_comparisons++;
                if (arr[j - 1] > arr[j]) {
                    res2 += printArr_Iterations(arr, res);
                    swap(arr, j - 1, j);
                }
            }
        }
        if (Counts_of_interchange > 0) {
            res2 += printArr_Iterations(arr, res);
        }
        return res2;
    }
 
    public String countingSort(int[] array) {
        int maxNum = array[0], minNum = array[0];
        String res2 = "";
        for (int i = 1; i < array.length; i++) {
            if (maxNum < array[i]) {
                maxNum = array[i];
            }
            if (minNum > array[i]) {
                minNum = array[i];
            }
        }

        int[] count = new int[maxNum - minNum + 1];
        for (int i = 0; i < array.length; i++) {
            count[array[i] - minNum]++;
        }

        for (int i = minNum + 1; i <= maxNum; i++) {
            count[i - minNum] += count[i - 1 - minNum];
        }

        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[count[array[i] - minNum] - 1] = array[i];
            res2 += printArr_Iterations(result, "");
            count[array[i] - minNum]--;
        }

        return res2;
    }

    public int[] generateRandomArray(int n) {
        arrays_data.setText("");
        int listt[] = new int[n];
        Random random = new Random();

        for (int i = 0; i < n / 2; i++) {
            listt[i] = random.nextInt(100);
        }
        for (int i = n / 2; i < n; i++) {
            listt[i] = random.nextInt(21) - 10;
        }
        return listt;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.addAll("Bubble Sort", "Quick Sort", "Count Sort");
        menu.getItems().addAll(list);
    }
}
