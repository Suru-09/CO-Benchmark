package service.register;

import controller.SceneManager;
import controller.home.HomeController;
import domain.Configuration;
import domain.User;
import javafx.fxml.FXMLLoader;
import repository.RepoManager;
import repository.UserRepository;

public class RegisterService {
    private final UserRepository userRepo = RepoManager.getInstance().getUserRepo();

    public RegisterService(){}

    public boolean signUp(String username, String password, String cpu, String ram) {
        User user = new User(username, password, new Configuration(cpu, ram));

        try {
            userRepo.add(user);
        } catch (Exception ex) {
            return false;
        }
        userRepo.updateRepository();

        RepoManager.getInstance().setCurrentUser(user);
        FXMLLoader loader = SceneManager.getInstance().getFXML(SceneManager.States.HOME);
        HomeController controller = loader.getController();
        controller.populateTestTableView();

        return true;
    }

    public static boolean validUsername(String username){
        return username.matches("^.{4,20}$");
    }

    public static boolean validPassword(String password){
        return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{5,20}$");
    }

    public static boolean validCPU(String cpu) {
        return cpu.matches("^.{4,20}$");

    }

    public static boolean validRam(String ram) {
        return ram.matches("^.{1,10}$");
    }
}
