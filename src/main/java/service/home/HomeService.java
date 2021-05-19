package service.home;

import benchmark.testbench.TestSpigot;
import domain.Test;
import domain.User;
import repository.TestRepository;
import repository.UserRepository;

public class HomeService {
    private TestRepository testRepo;
    private UserRepository userRepo;
    private User currentUser;

    public void runTestbench(Test.Algorithm algorithm, int size, int threads){
        
        switch ( algorithm ){
            case SPIGOT -> {
                TestSpigot testSpigot = new TestSpigot(size);
                double time = testSpigot.getTime();

                Test test = new Test(algorithm, size, threads, 23423L);
                test.setTime(time);

                System.out.println(test);
            }
            case MONTE_CARLO -> {

            }
            case GAUSS_LEGENDRE -> {

            }
        }
    }
}

class Oof {
    public static void main(String[] args) {
        HomeService hs = new HomeService();

        hs.runTestbench(Test.Algorithm.SPIGOT, 10000, 2);
    }
}
