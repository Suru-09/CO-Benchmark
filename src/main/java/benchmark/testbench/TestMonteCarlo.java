package benchmark.testbench;

import benchmark.bench.IBenchmark;
import benchmark.bench.cpu.MonteCarlo;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;

//class Multithreading1 extends Thread {
//    public void run() {
//        TestMonteCarlo test = new TestMonteCarlo ();
//        MonteCarlo bench = test.getBench();
//        bench.run(1);
//    }
//}

public class TestMonteCarlo extends TestAlgoritm {
    private final IBenchmark bench = new MonteCarlo();

    public TestMonteCarlo(int size) {
        ITimer timer = new Timer();

        bench.initialize(size);
        bench.warmUp();

        timer.start();

        bench.run(1);

        long time = timer.stop();
        super.setTime( TimeUnit.toTimeUnit(time, TimeUnit.Milli) );

        bench.clean();
    }

    public MonteCarlo getBench() {
        return (MonteCarlo) bench;
    }
}
