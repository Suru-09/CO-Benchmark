package benchmark.testbench;

import benchmark.bench.IBenchmark;
import benchmark.bench.cpu.GaussLegendre;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;

import java.util.ArrayList;
import java.util.List;

public class TestGaussLegendre extends TestAlgoritm {
    private final IBenchmark bench = new GaussLegendre();

    private final int size;
    private final int threads;

    public TestGaussLegendre(int size, int threads) {
        this.size = size;
        this.threads = threads;
    }

    public void start() {
        ITimer timer = new Timer();

        bench.initialize(size);
        bench.warmUp();

        timer.start();

        bench.run();
        long time = timer.stop();

        System.out.println("TIME: " + time);

        MultiThreading<TestGaussLegendre> currThread = (MultiThreading<TestGaussLegendre>) Thread.currentThread();
        currThread.setTime(time);
        super.setTime(TimeUnit.toTimeUnit(time, TimeUnit.Milli));

        bench.clean();
    }

    public void threads() {
        ArrayList<MultiThreading<TestGaussLegendre>> threadsArr = new ArrayList<>();

        for(int i = 0 ; i < threads; ++i) {
            threadsArr.add(new MultiThreading<>(new TestGaussLegendre(size, threads)));
            threadsArr.get(i).setSize(size);
            threadsArr.get(i).setThreads(threads);
            threadsArr.get(i).start();
        }

        for(int i = 0; i < threads; ++i) {
            try {
                threadsArr.get(i).join();
                super.addTime(threadsArr.get(i).getTime());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
