package benchmark.testbench;

import benchmark.bench.cpu.SpigotAlgorithm;
import benchmark.logging.ConsoleLogger;
import benchmark.logging.ILog;
import benchmark.logging.TimeUnit;
import benchmark.timing.ITimer;
import benchmark.timing.Timer;
import benchmark.bench.IBenchmark;

//
//class Multithreading extends Thread {
//	public void run() {
//		TestSpigot test = new TestSpigot();
//		SpigotAlgorithm bench = test.getBench();
//		bench.run(1);
//	}
//}

public class TestSpigot {
	private IBenchmark bench = new SpigotAlgorithm();
//	private ILog log = new ConsoleLogger();

	private double time;

	public TestSpigot(int size) {
		ITimer timer = new Timer();

		bench.initialize(size);
		bench.warmUp();

		timer.start();

		bench.run(1);

		long time = timer.stop();
		//log.writeTime("Finished in", time, timeUnit);
		//log.write("Result is", bench.getResult());

		this.time = TimeUnit.toTimeUnit(time, TimeUnit.Milli);

		bench.clean();
//		log.close();
	}


	public SpigotAlgorithm getBench() {
		return (SpigotAlgorithm) bench;
	}

	public double getTime() {
		return time;
	}

//	public ConsoleLogger getLog() {
//		return (ConsoleLogger) log;
//	}
}
