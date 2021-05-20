package service.login;

import controller.SceneManager;
import repository.UserRepository;

public class LoginService {
    private final UserRepository userRepo = UserRepository.getInstance();

    public LoginService(){}

    public int signIn(String username, String password) {
        if ( !userRepo.userExists(username, password) ){
            return -1;
        }

        SceneManager.getInstance().switchScene(SceneManager.States.HOME);

        return 0;
    }
}
