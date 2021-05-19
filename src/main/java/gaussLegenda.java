//import benchmark.bench.cpu.MonteCarlo;
//import benchmark.testbench.TestGaussLegendre;
//import benchmark.testbench.TestMonteCarlo;
//
//class Multithreading2 extends Thread {
//    public void run() {
//        TestGaussLegendre test = new TestGaussLegendre();
//        MonteCarlo bench = test.getBench();
//        bench.run(1);
//    }
//}
//
//public class gaussLegenda {
//    public static void main(String[] args) {
//        for(int i = 0 ; i < 4; ++i) {
//            Multithreading2 obj = new Multithreading2();
//            obj.start();
//        }
//    }
//}