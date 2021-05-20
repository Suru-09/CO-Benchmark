package service.login;

import controller.SceneManager;
import repository.UserRepository;

public class LoginService {
    private final UserRepository userRepo = new UserRepository();

    public LoginService(){}

    public int signIn(String username, String password) {
        if ( !userRepo.userExists(username, password) ){
            return -1;
        }

        return 0;
    }
}
