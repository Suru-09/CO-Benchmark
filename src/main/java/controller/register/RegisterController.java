package controller.register;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class RegisterController {

    @FXML
    private ChoiceBox<String> algorithmChoiceBox;
    private ChoiceBox<String> inputSizeChoiceBox;
    private ChoiceBox<String> threadChoiceBox;

    private ListView<String> threadResultsListView;
    private BarChart<Integer, Double> dtbStatisticsBarChart;

    private void startBenchmarkClick() {

    }

    private void seeStatisticsClick() {

    }

}
