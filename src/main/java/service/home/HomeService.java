package service.home;

import benchmark.testbench.TestAlgoritm;
import benchmark.testbench.TestGaussLegendre;
import benchmark.testbench.TestMonteCarlo;
import benchmark.testbench.TestSpigot;
import domain.Test;
import domain.User;
import repository.TestRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeService{
    private TestRepository testRepo = TestRepository.getInstance();
    private User currentUser;

    public User getUser() {
        return currentUser;
    }

    public HomeService(User user){
        currentUser = user;
    }

    public List<Long> runTestbench(Test.Algorithm algorithm, int size, int threads){
        TestAlgoritm testAlgoritm;

        List<Long> timeList = new ArrayList<>();

        switch ( algorithm ){
            case SPIGOT -> {
                testAlgoritm = new TestSpigot(size, threads);
                ((TestSpigot)testAlgoritm).threads();
                System.out.println(((TestSpigot)testAlgoritm).getTimeList());
                return ((TestSpigot)testAlgoritm).getTimeList();
            }
            case MONTE_CARLO -> {
                testAlgoritm = new TestMonteCarlo(size, threads);
                ((TestMonteCarlo)testAlgoritm).threads();
                System.out.println(((TestMonteCarlo)testAlgoritm).getTimeList());
                return ((TestMonteCarlo)testAlgoritm).getTimeList();
            }
            case GAUSS_LEGENDRE -> {
                testAlgoritm = new TestGaussLegendre(size, threads);
                ((TestGaussLegendre)testAlgoritm).threads();
                System.out.println(((TestGaussLegendre)testAlgoritm).getTimeList());
                return ((TestGaussLegendre)testAlgoritm).getTimeList();
            }
            default -> {
                return timeList;
            }
        }

    }

    public void addTests(List<Long> timeList) {

//        test.setTime(time);
//
//        try {
//            testRepo.add(test);
//            testRepo.updateRepository();
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
    }

}

class Oof {
    public static void main(String[] args) {
//        HomeService hs = new HomeService();
////        UserRepository repo = new UserRepository();
////
////        try {
////            ///repo.setCurrentUser(repo.findById(0L));
////            hs.runTestbench(Test.Algorithm.MONTE_CARLO, 100_000_0, 4);
////        }
////        catch (Exception ignored){
////            ignored.printStackTrace();
////        }
//        System.out.println(hs.getUser());
    }
}
