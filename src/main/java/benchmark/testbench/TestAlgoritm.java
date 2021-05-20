package benchmark.testbench;

import java.util.ArrayList;
import java.util.List;

public abstract class TestAlgoritm {
    private double time;
    private List<Long> timeList = new ArrayList<>();

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public List<Long> getTimeList() {
        return timeList;
    }

    public void addTime(Long time){
        timeList.add(time);
    }
}
