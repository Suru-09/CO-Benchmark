import benchmark.bench.cpu.SpigotAlgorithm;
import benchmark.testbench.TestSpigot;

class Multithreading extends Thread {
    public void run() {
        TestSpigot test = new TestSpigot();
        SpigotAlgorithm bench = test.getBench();
        bench.run(1);
    }
}

public class spigot {
    public static void main(String[] args) {
        for(int i = 0 ; i < 8; ++i) {
            Multithreading obj = new Multithreading();
            obj.start();
        }
    }
}
