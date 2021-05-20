package controller.login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

        loginService.signIn(username, password);
    }

    public void createAccountClick() {

    }

}

