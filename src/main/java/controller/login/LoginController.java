package controller.login;

import controller.SceneManager;
import controller.register.RegisterController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import service.CustomNotification;
import service.login.LoginService;


public class LoginController {
    LoginService loginService = new LoginService();

    @FXML
    public TextField usernameTextField;
    public PasswordField passwordPasswordField;

    public void signInClick() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        if ( !loginService.signIn(username, password) ) {
            CustomNotification notification = new CustomNotification(
              "Error",
              "User or password doesn't exist",
              CustomNotification.Type.ERROR
            );

            return;
        }

        usernameTextField.clear();
        passwordPasswordField.clear();


        SceneManager.getInstance().switchScene(SceneManager.States.HOME);

    }

    public void createAccountClick() {
        usernameTextField.clear();
        passwordPasswordField.clear();

        FXMLLoader loader = SceneManager.getInstance().getFXML(SceneManager.States.REGISTER);
        RegisterController controller = loader.getController();
        controller.setup();

        SceneManager.getInstance().switchScene(SceneManager.States.REGISTER);
    }

    @FXML
    private void enterPressed(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ENTER) {
            signInClick();
        }
    }

}

