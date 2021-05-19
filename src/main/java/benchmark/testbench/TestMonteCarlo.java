package benchmark.testbench;

import benchmark.bench.cpu.MonteCarlo;
import benchmark.bench.cpu.SpigotAlgorithm;
import benchmark.logging.ConsoleLogger;
import benchmark.logging.ILog;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;
import benchmark.bench.IBenchmark;

public class TestMonteCarlo {

    private IBenchmark bench = new MonteCarlo();
    private ILog log = new ConsoleLogger();

    public ILog getLogger() {
        return log;
    }

    public MonteCarlo getBench() {
        return (MonteCarlo)bench;
    }

    public void TestBench() {
        ITimer timer = new Timer();
        TimeUnit timeUnit = TimeUnit.Milli;

        bench.initialize(10000000);
        bench.warmUp();

        timer.start();

        bench.run(1);

        long time = timer.stop();
        log.writeTime("Finished in", time, timeUnit);
        log.write("Result is", bench.getResult());

        bench.clean();
        log.close();
    }
}
