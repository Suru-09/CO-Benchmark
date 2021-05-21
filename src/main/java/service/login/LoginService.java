package service.login;

import controller.SceneManager;
import controller.home.HomeController;
import javafx.fxml.FXMLLoader;
import repository.RepoManager;
import repository.UserRepository;

public class LoginService {
    private final UserRepository userRepo = RepoManager.getInstance().getUserRepo();

    public LoginService(){}

    public boolean signIn(String username, String password) {
        if ( !userRepo.userExists(username, password) ){
            return false;
        }

        RepoManager.getInstance().setCurrentUser(userRepo.getUserAfterUsername(username));
        FXMLLoader loader = SceneManager.getInstance().getFXML(SceneManager.States.HOME);
        HomeController controller = loader.getController();
        controller.populateTestTableView();

        return true;
    }
}
