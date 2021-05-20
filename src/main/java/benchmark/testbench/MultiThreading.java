package benchmark.testbench;

public class MultiThreading<T> extends Thread {
    private int size, threads;
    private TestAlgoritm test;
    private long time;

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public MultiThreading(TestAlgoritm test) {
        this.test = test;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        if(test instanceof TestSpigot) {
            ((TestSpigot)test).start();
        }

        if(test instanceof TestGaussLegendre) {
            ((TestGaussLegendre)test).start();
        }

        if(test instanceof TestMonteCarlo) {
            ((TestMonteCarlo)test).start();
        }

    }
}
