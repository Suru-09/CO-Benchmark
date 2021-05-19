package domain;

import com.google.gson.annotations.Expose;

public class Test extends BaseEntity<Long> {
    private int size;
    private int threads;
    private double time;
    private double score;

    private Long userID;

    public Test(int size, int threads, Long userID) {
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
        this.score = size/time * 1000;
    }
}
