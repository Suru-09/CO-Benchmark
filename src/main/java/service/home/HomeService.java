package service.home;

import benchmark.testbench.TestAlgoritm;
import benchmark.testbench.TestGaussLegendre;
import benchmark.testbench.TestMonteCarlo;
import benchmark.testbench.TestSpigot;
import domain.Test;
import domain.User;
import domain.exception.CustomException;
import repository.RepoManager;
import repository.TestRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeService{
    private final TestRepository testRepo = RepoManager.getInstance().getTestRepo();
    private final UserRepository userRepo = RepoManager.getInstance().getUserRepo();

    public ArrayList<Test> getTestsForCurrentUser(){
        return (ArrayList<Test>) testRepo.getTestsForUser(RepoManager.getInstance().getCurrentUser().getId());
    }

    public ArrayList<User> getUsers(){
        return (ArrayList<User>) userRepo.getAll();
    }

    public void addTestsToUsers(){
        for (User user : userRepo.getAll()){
            user.setTests(testRepo.getTestsForUser(user.getId()));
        }
        userRepo.updateRepository();
    }

    public List<Double> runTestbench(Test.Algorithm algorithm, int size, int threads, Long userID){
        TestAlgoritm testAlgoritm;

        List<Double> timeList = new ArrayList<>();

        switch ( algorithm ){
            case SPIGOT -> {
                testAlgoritm = new TestSpigot(size, threads);
                ((TestSpigot)testAlgoritm).threads();
                System.out.println(testAlgoritm.getTimeList());
                return testAlgoritm.getTimeList();
            }
            case MONTE_CARLO -> {
                testAlgoritm = new TestMonteCarlo(size, threads);
                ((TestMonteCarlo)testAlgoritm).threads();
                System.out.println(testAlgoritm.getTimeList());
                return testAlgoritm.getTimeList();
            }
            case GAUSS_LEGENDRE -> {
                testAlgoritm = new TestGaussLegendre(size, threads);
                ((TestGaussLegendre)testAlgoritm).threads();
                System.out.println(testAlgoritm.getTimeList());
                return testAlgoritm.getTimeList();
            }
            default -> {
                return timeList;
            }
        }
    }

    public void addTests(List<Double> timeList, Test.Algorithm alg, int size, int threads, Long userID) {
        for (Double i : timeList) {
            Test test = new Test(alg, size, threads, userID);
            test.setTime(i);
            try {
                testRepo.add(test);
            }catch (CustomException e) {
                e.printStackTrace();
            }
        }

        testRepo.updateRepository();
    }
}
