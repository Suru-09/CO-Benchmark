import benchmark.bench.cpu.MonteCarlo;
import benchmark.testbench.TestMonteCarlo;

class Multithreading1 extends Thread {
    public void run() {
        TestMonteCarlo test = new TestMonteCarlo ();
        MonteCarlo bench = test.getBench();
        bench.run(1);
    }
}

public class monteCarlo {
    public static void main(String[] args) {
        for(int i = 0 ; i < 4; ++i) {
            Multithreading1 obj = new Multithreading1();
            obj.start();
        }
    }
}
