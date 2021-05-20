package controller.home;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TestPropertyWrapper {

    StringProperty userID;
    StringProperty algorithm;
    StringProperty score;
    StringProperty size;
    StringProperty time;
    StringProperty threads;

    public TestPropertyWrapper(String userID, String algorithm, String score, String size, String time, String threads) {
        this.userID = new SimpleStringProperty(userID);
        this.algorithm = new SimpleStringProperty(algorithm);
        this.score = new SimpleStringProperty(score);
        this.size = new SimpleStringProperty(size);
        this.time = new SimpleStringProperty(time);
        this.threads = new SimpleStringProperty(threads);
    }


}
