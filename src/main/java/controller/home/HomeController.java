package controller.home;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class HomeController {

    @FXML
    public ChoiceBox<String> algorithmChoiceBox;
    public ChoiceBox<String> inputSizeChoiceBox;
    public ChoiceBox<String> threadChoiceBox;

    public ListView<String> threadResultsListView;
    public BarChart<Integer, Double> dtbStatisticsBarChart;

    public void startBenchmarkClick() {

    }

    public void seeStatisticsClick() {

    }

}
