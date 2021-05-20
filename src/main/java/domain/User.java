package domain;

import domain.strategy.Exclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends BaseEntity<Long>{
    private String username;
    private String password;
    private Configuration configuration;

    @Exclude
    private List<Test> tests = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", configuration=" + configuration +
                ", tests=" + tests +
                ", score=" + score +
                '}';
    }
}
