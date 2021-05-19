package benchmark.testbench;

import benchmark.bench.IBenchmark;
import benchmark.bench.cpu.GaussLegendre;
import benchmark.logging.ConsoleLogger;
import benchmark.logging.ILog;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;

public class TestGaussLegendre {

    private IBenchmark bench = new GaussLegendre();
    private ILog log = new ConsoleLogger();

    public ILog getLogger() {
        return log;
    }

    public IBenchmark getBench() {
        return (GaussLegendre)bench;
    }

    public TestGaussLegendre() {
        ITimer timer = new Timer();

        int N = 1000000;

        bench.initialize(N);
        bench.warmUp();

        timer.resume();
        bench.run();
        long time = timer.pause();

        log.write(GaussLegendre.PI);
        long total_time = timer.stop();

        log.close();
        bench.clean();
    }

}
