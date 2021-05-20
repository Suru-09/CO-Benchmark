package benchmark.testbench;

import benchmark.bench.IBenchmark;
import benchmark.bench.cpu.SpigotAlgorithm;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;


//class Multithreading extends Thread {
//	public void run() {
//		TestSpigot test = new TestSpigot(43);
//		SpigotAlgorithm bench = test.getBench();
//		bench.run(1);
//	}
//}

public class TestSpigot extends TestAlgoritm {
	private final IBenchmark bench = new SpigotAlgorithm();

	public TestSpigot(int size) {
		ITimer timer = new Timer();

		bench.initialize(size);
		bench.warmUp();

		timer.start();

		bench.run(1);

		long time = timer.stop();
		super.setTime(TimeUnit.toTimeUnit(time, TimeUnit.Milli));

		bench.clean();
	}

	public SpigotAlgorithm getBench() {
		return (SpigotAlgorithm) bench;
	}
}
