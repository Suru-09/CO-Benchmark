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

    @Exclude
    private double scoreSpigot;
    @Exclude
    private double scoreMonteCarlo;
    @Exclude
    private double scoreGaussLegendre;

    public User(String username, String password, Configuration configuration) {
        this.username = username;
        this.password = password;
        this.configuration = configuration;
        this.scoreSpigot = 0;
        this.scoreMonteCarlo = 0;
        this.scoreGaussLegendre = 0;
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
        calculateScore();
    }

    public double getScoreSpigot() {
        return scoreSpigot;
    }

    public double getScoreMonteCarlo() {
        return scoreMonteCarlo;
    }

    public double getScoreGaussLegendre() {
        return scoreGaussLegendre;
    }

    private void calculateScore(){
        if ( tests.isEmpty() ){
            return;
        }
        int spigotTestSize = 0;
        int carloTestSize = 0;
        int gaussTestSize = 0;
        for (Test test : tests){
            switch (test.getAlgorithm()){
                case SPIGOT -> {
                    scoreSpigot += test.getScore();
                    spigotTestSize++;
                }
                case MONTE_CARLO -> {
                    scoreMonteCarlo += test.getScore();
                    carloTestSize++;
                }
                case GAUSS_LEGENDRE -> {
                    scoreGaussLegendre += test.getScore();
                    gaussTestSize++;
                }
            }
        }
        scoreSpigot = ( spigotTestSize != 0 ) ? scoreSpigot/spigotTestSize : 0;
        scoreMonteCarlo = ( carloTestSize != 0 ) ? scoreMonteCarlo/carloTestSize : 0;
        scoreGaussLegendre = ( gaussTestSize != 0 ) ? scoreGaussLegendre/gaussTestSize : 0;
    }

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
                ", scoreSpigot=" + scoreSpigot +
                ", scoreMonteCarlo=" + scoreMonteCarlo +
                ", scoreGaussLegendre=" + scoreGaussLegendre +
                '}';
    }
}
