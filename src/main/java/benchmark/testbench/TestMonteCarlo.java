package benchmark.testbench;

import benchmark.bench.IBenchmark;
import benchmark.bench.cpu.MonteCarlo;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;

import java.util.ArrayList;
import java.util.List;


public class TestMonteCarlo extends TestAlgoritm {
    private final IBenchmark bench = new MonteCarlo();
    private int size;
    private int threads;
    private long time;

    private List<Long> timeList = new ArrayList<>();

    public List<Long> getTimeList() {
        return timeList;
    }

    public TestMonteCarlo(int size, int threads) {
        this.size = size;
        this.threads = threads;
    }

    public void start() {
        ITimer timer = new Timer();

        bench.initialize(size);
        bench.warmUp();

        timer.start();

        bench.run(1);
        long time = timer.stop();

        MultiThreading<TestSpigot> currThread = (MultiThreading<TestSpigot>) Thread.currentThread();
        currThread.setTime(time);

        super.setTime( TimeUnit.toTimeUnit(time, TimeUnit.Milli) );

        bench.clean();
    }

    public void threads() {

        ArrayList<MultiThreading<TestMonteCarlo>> threadsArr = new ArrayList<>();

        for(int i = 0 ; i < threads; ++i) {
            threadsArr.add(new MultiThreading<>(new TestMonteCarlo(size, threads)));
            threadsArr.get(i).setSize(size);
            threadsArr.get(i).setThreads(threads);
            threadsArr.get(i).start();
        }

        for(int i = 0 ; i < threads; ++i) {
            try {
                threadsArr.get(i).join();
                timeList.add(threadsArr.get(i).getTime());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MonteCarlo getBench() {
        return (MonteCarlo) bench;
    }
}
