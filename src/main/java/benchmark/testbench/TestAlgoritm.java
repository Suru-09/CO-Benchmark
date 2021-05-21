package benchmark.testbench;

import java.util.ArrayList;
import java.util.List;

public abstract class TestAlgoritm {
    private double time;
    private final List<Double> timeList = new ArrayList<>();

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public List<Double> getTimeList() {
        return timeList;
    }

    public void addTime(double time){
        timeList.add(time);
    }
}
