package repository;

import domain.User;

public class RepoManager {
    private static UserRepository userRepo;
    private static TestRepository testRepo;
    private static User currentUser;

    private static RepoManager instance = null;

    public RepoManager(){
        instance = this;
        userRepo = new UserRepository();
        testRepo = new TestRepository();
    }

    public static RepoManager getInstance() {
        if ( instance == null ){
            instance = new RepoManager();
        }
        return instance;
    }

    public TestRepository getTestRepo() {
        return testRepo;
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        RepoManager.currentUser = currentUser;
    }
}
