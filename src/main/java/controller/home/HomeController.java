package controller.home;

import controller.SceneManager;
import domain.Test;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

    public AnchorPane barChartPane;
    public AnchorPane tableViewPane;

    public TableView<Test> testTableView;
    public TableColumn<Test, Test.Algorithm> algorithmColumn;
    public TableColumn<Test, Integer> sizeColumn;
    public TableColumn<Test, Integer> threadsColumn;
    public TableColumn<Test, Double> timeColumn;
    public TableColumn<Test, Double> scoreColumn;

    public CategoryAxis cpuAxis;
    public NumberAxis scoreAxis;
    public BarChart<String, Double> statisticsBarChart;

    // ACTIONS
    public void startBenchmarkClick(){
        barChartPane.setVisible(false);
        tableViewPane.setVisible(true);

        startBenchmark();
    }

    public void seeStatisticsClick() {
        barChartPane.setVisible(true);
        tableViewPane.setVisible(false);

        seeStatistics();
    }

    // SETUP
    public void setTestTableView(){
        testTableView.setPlaceholder(new Label(""));

        algorithmColumn.setCellValueFactory(new PropertyValueFactory<>("algorithm"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        threadsColumn.setCellValueFactory(new PropertyValueFactory<>("threads"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    public void setStatisticsBarChart(){

    }

    public void populateTestTableView(){
        testTableView.getItems().clear();

        ArrayList<Test> tests = homeService.getTestsForCurrentUser();

        for (Test test : tests){
            testTableView.getItems().add(test);
        }

        testTableView.refresh();
    }

    public void populateStatisticsBarChart(){
        statisticsBarChart.getData().clear();

        ArrayList<User> users = homeService.getUsers();
        XYChart.Series<String, Double> spigotSeries = new XYChart.Series<>();
        spigotSeries.setName("SPIGOT");
        XYChart.Series<String, Double> gaussSeries = new XYChart.Series<>();
        gaussSeries.setName("GAUSS LEGENDRE");
        XYChart.Series<String, Double> carloSeries = new XYChart.Series<>();
        carloSeries.setName("MONTE CARLO");

        for (User user : users){
            if ( !user.getTests().isEmpty() ){
                String configText = user.getUsername() + " - " + user.getConfiguration().getCpu();

                spigotSeries.getData().add(new XYChart.Data<>(configText, user.getScoreSpigot()) );
                gaussSeries.getData().add(new XYChart.Data<>(configText, user.getScoreGaussLegendre()) );
                carloSeries.getData().add(new XYChart.Data<>(configText, user.getScoreMonteCarlo()) );
            }
        }

        statisticsBarChart.getData().add(spigotSeries);
        statisticsBarChart.getData().add(gaussSeries);
        statisticsBarChart.getData().add(carloSeries);
    }

    public void startBenchmark() {
        String algorithm = getAlgorithmChoiceBox();
        int inputSize = getInputSize();
        int threads = getThreadsChoiceBox();

        List<Long> testTimes = homeService.runTestbench(Test.Algorithm.fromString(algorithm), inputSize, threads, RepoManager.getInstance().getCurrentUser().getId());
        homeService.addTests(testTimes, Test.Algorithm.fromString(algorithm), inputSize, threads, RepoManager.getInstance().getCurrentUser().getId());

        populateTestTableView();
    }

    public void seeStatistics() {
        homeService.addTestsToUsers();

        populateStatisticsBarChart();
    }

    public void setAlgorithmChoiceBox() {
        ObservableList<String> algs = FXCollections.observableArrayList("Spigot", "Monte Carlo",
                "Gauss Legendre");

        algorithmChoiceBox.getItems().addAll(algs);
        algorithmChoiceBox.getSelectionModel().selectFirst();

    }

    public void setInputSizeChoiceBox() {
        ObservableList<String> sizes = FXCollections.observableArrayList(
                "10000", "25000", "50000", "75000"
                , "100000", "125000", "150000", "175000"
                , "200000", "225000", "250000");

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
