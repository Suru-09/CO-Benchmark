package controller.home;

import controller.SceneManager;
import domain.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.RepoManager;
import service.home.HomeService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    HomeService homeService = new HomeService();

    @FXML
    public ChoiceBox<String> algorithmChoiceBox;
    public ChoiceBox<String> inputSizeChoiceBox;
    public ChoiceBox<String> threadChoiceBox;

    public TableView<Test> testTableView;
    public TableColumn<Test, Test.Algorithm> algorithmColumn;
    public TableColumn<Test, Integer> sizeColumn;
    public TableColumn<Test, Integer> threadsColumn;
    public TableColumn<Test, Double> timeColumn;
    public TableColumn<Test, Double> scoreColumn;

    public BarChart<Integer, Double> statisticsBarChart;

    public void setTestTableView(){
        testTableView.setPlaceholder(new Label(""));

        algorithmColumn.setCellValueFactory(new PropertyValueFactory<>("algorithm"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        threadsColumn.setCellValueFactory(new PropertyValueFactory<>("threads"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    public void populateTestTableView(){
        testTableView.getItems().clear();

        ArrayList<Test> tests = homeService.getTestsForCurrentUser();

        for (Test test : tests){
            testTableView.getItems().add(test);
        }

        testTableView.refresh();
    }

    public void populate

    public void startBenchmarkClick() {
        String algorithm = getAlgorithmChoiceBox();
        int inputSize = getInputSize();
        int threads = getThreadsChoiceBox();

        List<Long> testTimes = homeService.runTestbench(Test.Algorithm.fromString(algorithm), inputSize/100, threads, RepoManager.getInstance().getCurrentUser().getId());
        homeService.addTests(testTimes, Test.Algorithm.fromString(algorithm), inputSize, threads, RepoManager.getInstance().getCurrentUser().getId());

        populateTestTableView();
    }

    public void seeStatisticsClick() {
        homeService.doEverything();
    }

    public void setAlgorithmChoiceBox() {
        ObservableList<String> algs = FXCollections.observableArrayList("Spigot", "Monte Carlo",
                "Gauss Legendre");

        algorithmChoiceBox.getItems().addAll(algs);
        algorithmChoiceBox.getSelectionModel().selectFirst();

    }

    public void setInputSizeChoiceBox() {
        ObservableList<String> sizes = FXCollections.observableArrayList(
                "1000000", "2500000", "5000000", "7500000"
                , "10000000", "25000000", "50000000", "75000000");

        inputSizeChoiceBox.getItems().addAll(sizes);
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
        setTestTableView();
    }

    public void goBackClick() {
        testTableView.getItems().clear();
        SceneManager.getInstance().switchScene(SceneManager.States.LOGIN);
    }
}
