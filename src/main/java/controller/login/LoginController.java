package controller.login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.CustomNotification;
import service.login.LoginService;


public class LoginController {
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
              "User already exists",
              CustomNotification.Type.ERROR
            );
        }
    }

    public void createAccountClick() {

    }

}

