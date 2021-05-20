package benchmark.bench.cpu;

import benchmark.bench.IBenchmark;

import java.awt.geom.Point2D;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class MonteCarlo implements IBenchmark {

    private final Random random;
    private MonteCarlo computer;
    private int nrOfSamples;

    public MonteCarlo(Random random) {
        this.random = Objects.requireNonNull(
                random,
                "The input random number generator is null."
        );
    }

    public MonteCarlo() {
        this(new Random());
    }


    public double approximatePi(int samples) {
        Random r = this.random;
        Point2D.Double origin = new Point2D.Double();
        long pointsWithinUnitArc = IntStream.range(0, samples)
                .filter(i -> origin.distanceSq(r.nextDouble(), r.nextDouble()) < 1)
                .count();
        return (4.0 * pointsWithinUnitArc) / samples;
    }

    @Override
    public void initialize(Object... params) {
        if(params.length == 1) {
            if(params[0] instanceof Integer)
                nrOfSamples = (int) params[0];
        }
        computer = new MonteCarlo();
    }

    @Override
    public void warmUp() {

    }

    @Override
    public void run() {

    }

    @Override
    public void run(Object... options) {
        MonteCarlo computer = new MonteCarlo();
        for (int samples = 100_000_000; samples <= nrOfSamples * 1000; samples += 100_000_000) {
            double approximation = computer.approximatePi(samples);
//            double pctDiff = 100 * (approximation - Math.PI) / Math.PI;
//            System.out.format("%7d: %10f, deviation from exact Pi: %+10f%%%n",
//                    samples,
//                    approximation,
//                    pctDiff
//            );
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public void clean() {

    }

    @Override
    public String getResult() {
        return null;
    }
}
