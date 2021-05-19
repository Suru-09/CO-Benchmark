package benchmark.bench.cpu;

import benchmark.bench.IBenchmark;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ROUND_HALF_UP;

public class GaussLegendre implements IBenchmark {
    private int n;
    private Boolean running;
    public static BigDecimal PI;
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FOUR = new BigDecimal(4);

    @Override
    public void initialize(Object...init_params) {
        if(init_params.length == 1) {
            n = (int) init_params[0];
        }
    }

    @Override
    public void run() {
        PI = pi(n);
    }

    @Override
    public void run(Object... run_params) {

    }

    @Override
    public void warmUp() {
        this.run();
    }

    // Gauss-Legendre Algorithm
    public static BigDecimal pi(final int SCALE) {
        BigDecimal a = ONE;
        BigDecimal b = ONE.divide(sqrt(TWO, SCALE), SCALE, ROUND_HALF_UP);
        BigDecimal t = new BigDecimal(0.25);
        BigDecimal x = ONE;
        BigDecimal y;

        while (!a.equals(b)) {
            y = a;
            a = a.add(b).divide(TWO, SCALE, ROUND_HALF_UP);
            b = sqrt(b.multiply(y), SCALE);
            t = t.subtract(x.multiply(y.subtract(a).multiply(y.subtract(a))));
            x = x.multiply(TWO);
        }

        return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), SCALE, ROUND_HALF_UP);
    }

    // the Babylonian square root method (Newton's method)
    public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));

        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);
        }

        return x1;
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult() {
        return null;
    }
}
