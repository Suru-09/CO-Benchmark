package benchmark.logging;

public enum TimeUnit {

	Nano, Micro, Milli, Sec;

	/**
	 * Converts time given in nanoseconds to another time unit
	 * @param time Time in nanoseconds
	 * @param unit Unit to convert to
	 * @return Time expressed in given unit
	 */
	public static double toTimeUnit(long time, TimeUnit unit) {

		return switch (unit) {
			case Nano -> time;
			case Micro -> time / 1000.0;
			case Milli -> time / 1000000.0;
			case Sec -> time / 1000000000.0;
		};
	}
}
