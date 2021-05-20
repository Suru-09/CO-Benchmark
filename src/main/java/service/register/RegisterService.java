package service.register;

import controller.SceneManager;
import domain.Configuration;
import domain.User;
import repository.UserRepository;

public class RegisterService {
    private final UserRepository userRepo = UserRepository.getInstance();

    public RegisterService(){}

    public int signUp(String username, String password, String cpu, String ram) {
        int valid = 0;

        if ( !validUsername(username) ){
            valid += 1;
        }

        if ( !validPassword(password) ){
            valid += 2;
        }

        if ( valid != 0 ){
            return valid;
        }

        User user = new User(username, password, new Configuration(cpu, ram));

        try {
            userRepo.add(user);
            userRepo.updateRepository();
        } catch (Exception ex) {
            return -1;
        }

        SceneManager.getInstance().switchScene(SceneManager.States.HOME);

        return 0;
    }

    private static boolean validUsername(String username){
        return ( username.length() > 4 && username.length() < 20 );
    }

    private static boolean validPassword(String password){
        return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{5,20}$");
    }
}
