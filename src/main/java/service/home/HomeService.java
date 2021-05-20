package service.home;

import benchmark.testbench.TestAlgoritm;
import benchmark.testbench.TestGaussLegendre;
import benchmark.testbench.TestMonteCarlo;
import benchmark.testbench.TestSpigot;
import domain.Test;
import domain.User;
import repository.TestRepository;
import repository.UserRepository;

public class HomeService {
    private TestRepository testRepo = TestRepository.getInstance();
    private UserRepository userRepo = UserRepository.getInstance();
    private User currentUser;

    public HomeService(){
        currentUser = userRepo.getCurrentUser();
    }

    public void runTestbench(Test.Algorithm algorithm, int size, int threads){
        TestAlgoritm testAlgoritm;
        Test test = new Test(algorithm, size, threads, currentUser.getId());
        
        switch ( algorithm ){
            case SPIGOT -> {
                testAlgoritm = new TestSpigot(size);
            }
            case MONTE_CARLO -> {
                testAlgoritm = new TestMonteCarlo(size);
            }
            case GAUSS_LEGENDRE -> {
                testAlgoritm = new TestGaussLegendre(size);
            }
            default -> {
                return;
            }
        }
        double time = testAlgoritm.getTime();

        test.setTime(time);

        try {
            testRepo.add(test);
            testRepo.updateRepository();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

class Oof {
    public static void main(String[] args) {
        HomeService hs = new HomeService();
        UserRepository repo = new UserRepository();

        try {
            repo.setCurrentUser(repo.findById(0L));
            hs.runTestbench(Test.Algorithm.GAUSS_LEGENDRE, 10000, 1);
        }
        catch (Exception ignored){ }
    }
}
