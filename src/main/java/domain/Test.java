package domain;

import com.google.gson.annotations.Expose;
import service.home.HomeService;

public class Test extends BaseEntity<Long> {
    private Algorithm algorithm;

    private int size;
    private int threads;
    private double time;
    private double score;

    private Long userID;

    public enum Algorithm{
        GAUSS_LEGENDRE,
        MONTE_CARLO,
        SPIGOT
    }

    public Test(Algorithm algorithm, int size, int threads, Long userID) {
        this.algorithm = algorithm;
        this.size = size;
        this.threads = threads;
        this.userID = userID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
        calculateScore();
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


    private void calculateScore(){
        switch ( algorithm ){
            case SPIGOT -> {
                this.score = size/time * 1000;
            }
            case GAUSS_LEGENDRE -> {

            }
            case MONTE_CARLO -> {

            }
        }
    }

    @Override
    public String toString() {
        return "Test{" +
                "algorithm=" + algorithm +
                ", size=" + size +
                ", threads=" + threads +
                ", time=" + time +
                ", score=" + score +
                ", userID=" + userID +
                '}';
    }
}
