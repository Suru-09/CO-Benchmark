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

    public TreeTableView<TestPropertyWrapper> threadResultsTableView;
    public BarChart<Integer, Double> dtbStatisticsBarChart;

    public void startBenchmarkClick() {
        String algorithm = getAlgorithmChoiceBox();
        int inputSize = getInputSize();
        int threads = getThreadsChoiceBox();

        homeService.addTests(homeService.runTestbench(Test.Algorithm.fromString(algorithm), inputSize/100, threads),
                Test.Algorithm.fromString(algorithm), inputSize, threads);

        ObservableList<TestPropertyWrapper> listTests = FXCollections.observableArrayList();

        System.out.println(listTests);

        TreeTableColumn<TestPropertyWrapper, String> idCol = new TreeTableColumn<>("User ID");

        idCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String> param) {
              return param.getValue().getValue().userID;
          }
      });

        TreeTableColumn <TestPropertyWrapper, String> algCol = new TreeTableColumn<>("Algorithm");

        algCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String> param) {
                return param.getValue().getValue().algorithm;
            }
        });

        TreeTableColumn<TestPropertyWrapper, String> scoreCol = new TreeTableColumn<>("Score");

        scoreCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String> param) {
                return param.getValue().getValue().score;
            }
        });

        TreeTableColumn<TestPropertyWrapper, String> timeCol = new TreeTableColumn<>("Time");

        timeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String> param) {
                return param.getValue().getValue().time;
            }
        });

        TreeTableColumn<TestPropertyWrapper, String> threadsCol = new TreeTableColumn<>("Threads");

        threadsCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TestPropertyWrapper, String> param) {
                return param.getValue().getValue().threads;
            }
        });

        for (Test i: testRepository.getTestsForUser(homeService.getUser().getId())) {
            TestPropertyWrapper testPropertyWrapper = new TestPropertyWrapper("" + i.getUserID(), "" +
                    i.getAlgorithm(), "" + i.getScore(), "" + i.getSize(), "" + i.getTime(),
                    "" + i.getThreads());
            listTests.add(testPropertyWrapper);

        }

        //threadResultsTableView.getColumns().addAll(listTests);
        threadResultsTableView.getColumns().addAll(idCol, algCol);//, scoreCol, timeCol, threadsCol);

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
