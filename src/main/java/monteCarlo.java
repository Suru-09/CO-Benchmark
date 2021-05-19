import benchmark.bench.cpu.MonteCarlo;
import benchmark.bench.cpu.SpigotAlgorithm;
import benchmark.testbench.TestMonteCarlo;
import benchmark.testbench.TestSpigot;

class Multithreading1 extends Thread {
    public void run() {
        TestMonteCarlo test = new TestMonteCarlo ();
        MonteCarlo bench = test.getBench();
        bench.run(1);
    }
}

public class monteCarlo {
    public static void main(String[] args) {
        for(int i = 0 ; i < 8; ++i) {
            Multithreading1 obj = new Multithreading1();
            obj.start();
        }
    }
}
