package benchmark.testbench;

import benchmark.bench.IBenchmark;
import benchmark.bench.cpu.GaussLegendre;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;

public class TestGaussLegendre extends TestAlgoritm {

    private IBenchmark bench = new GaussLegendre();

    private int size;
    private int threads;

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
        super.setTime(TimeUnit.toTimeUnit(time, TimeUnit.Milli));

        bench.clean();
    }

    public void threads() {
        
    }

    public IBenchmark getBench() {
        return (GaussLegendre)bench;
    }
}
