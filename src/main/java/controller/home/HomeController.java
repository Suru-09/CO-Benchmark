package controller.home;

import controller.SceneManager;
import domain.Test;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;
import repository.TestRepository;
import repository.UserRepository;
import service.home.HomeService;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    UserRepository userRepository = new UserRepository();
    HomeService homeService;
    TestRepository testRepository = new TestRepository();

    @FXML
    public ChoiceBox<String> algorithmChoiceBox;
    public ChoiceBox<String> inputSizeChoiceBox;
    public ChoiceBox<String> threadChoiceBox;

    public ListView<Test> threadResultsListView;
    public BarChart<Integer, Double> dtbStatisticsBarChart;

    public void startBenchmarkClick() {
        String algorithm = getAlgorithmChoiceBox();
        int inputSize = getInputSize();
        int threads = getThreadsChoiceBox();

        homeService.addTests(homeService.runTestbench(Test.Algorithm.fromString(algorithm), inputSize/100, threads),
                Test.Algorithm.fromString(algorithm), inputSize, threads);

        ObservableList<Test> listTests = FXCollections.observableArrayList();

        listTests.addAll(testRepository.getTestsForUser(homeService.getUser().getId()));

        threadResultsListView.getItems().addAll(listTests);

        System.out.println(listTests);



    }

    public void seeStatisticsClick() {

    }

    public void setAlgorithmChoiceBox() {
        ObservableList<String> algs = FXCollections.observableArrayList("Spigot", "Monte Carlo",
                "Gauss Legendre");

        algorithmChoiceBox.getItems().addAll(algs);
        algorithmChoiceBox.getSelectionModel().selectFirst();

    }

    public void setInputSizeChoiceBox() {
        ObservableList<String> nums = FXCollections.observableArrayList("1000000", "10000000",
                "50000000");

        inputSizeChoiceBox.getItems().addAll(nums);
        inputSizeChoiceBox.getSelectionModel().selectFirst();
    }

    public void setThreadChoiceBox() {
        ObservableList<String> threads = FXCollections.observableArrayList("2", "4",
                "8");

        threadChoiceBox.getItems().addAll(threads);
        threadChoiceBox.getSelectionModel().selectFirst();

    }

    public String getAlgorithmChoiceBox() {
        return algorithmChoiceBox.getSelectionModel().getSelectedItem();
    }

    public int getInputSize() {
        return Integer.parseInt(inputSizeChoiceBox.getSelectionModel().getSelectedItem());
    }

    public int getThreadsChoiceBox() {
        return Integer.parseInt(threadChoiceBox.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAlgorithmChoiceBox();
        setInputSizeChoiceBox();
        setThreadChoiceBox();
    }

    public void setUsername(String username) {
        System.out.println(userRepository.getUserAfterUsername(username));
        homeService = new HomeService(userRepository.getUserAfterUsername(username));
    }

    public void goBackClick() {
        SceneManager.getInstance().switchScene(SceneManager.States.LOGIN);
    }
}
