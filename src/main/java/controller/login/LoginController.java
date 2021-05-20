package controller.login;

import controller.SceneManager;
import controller.home.HomeController;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import repository.UserRepository;
import service.CustomNotification;
import service.login.LoginService;

import java.io.IOException;


public class LoginController {
    UserRepository userRepository;
    LoginService loginService = new LoginService();

    @FXML
    public TextField usernameTextField;
    public PasswordField passwordPasswordField;

    public Label usernameErrLabel;
    public Label passErrLabel;

    public void signInClick() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        if ( loginService.signIn(username, password) == -1) {
            CustomNotification notification = new CustomNotification(
              "Error",
              "User doesn't exist",
              CustomNotification.Type.ERROR
            );
        }

        FXMLLoader loader = SceneManager.getInstance().getFXML(SceneManager.States.HOME);
        try {
            HomeController controller = loader.getController();
            controller.setUsername(username);
        }
        catch (Exception e) {
           e.printStackTrace();
        }

        SceneManager.getInstance().switchScene(SceneManager.States.HOME);

    }

    public void createAccountClick() {

    }

}

