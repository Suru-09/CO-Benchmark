package domain;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity<Long>{
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private Configuration configuration;

    private List<Test> tests = new ArrayList<>();

    @Expose
    private double score;

    public User(String username, String password, Configuration configuration) {
        this.username = username;
        this.password = password;
        this.configuration = configuration;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    //
    public void addTest(Test test){
        tests.add(test);
    }
}
