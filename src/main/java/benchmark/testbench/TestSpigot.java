package benchmark.testbench;

import benchmark.bench.IBenchmark;
import benchmark.bench.cpu.SpigotAlgorithm;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;
import java.lang.Thread;

import java.util.ArrayList;
import java.util.List;


public class TestSpigot extends TestAlgoritm {
	private final IBenchmark bench = new SpigotAlgorithm();
	private int size;
	private int threads;

	public TestSpigot(int size, int threads) {
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

		super.setTime(TimeUnit.toTimeUnit(time, TimeUnit.Milli));

		bench.clean();
	}

	public void threads() {

		ArrayList<MultiThreading<TestSpigot>> threadsArr = new ArrayList<>();

		for(int i = 0 ; i < threads; ++i) {
			threadsArr.add(new MultiThreading<>(new TestSpigot(size, threads)));
			threadsArr.get(i).setSize(size);
			threadsArr.get(i).setThreads(threads);
			threadsArr.get(i).start();
		}

		for(int i = 0 ; i < threads; ++i) {
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
