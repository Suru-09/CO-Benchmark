package controller.register;

import controller.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.register.RegisterService;

public class RegisterController {
    RegisterService registerService = new RegisterService();

    @FXML
    public TextField usernameTextField;
    public PasswordField passwordPasswordField;
    public TextField cpuTextField;
    public TextField ramTextField;

    public Label usernameErrLabel;
    public Label passwordErrLabel;

    public void signUpClick() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        String cpu = cpuTextField.getText();
        String ram = ramTextField.getText();

        int state = registerService.signUp(username, password, cpu, ram);

        switch (state){
            case -1 -> {
                System.out.println("User already exists!");
                usernameErrLabel.setVisible(false);
                passwordErrLabel.setVisible(false);
            }
            case 1 -> {
                System.out.println("Invalid username!");
                usernameErrLabel.setVisible(true);
                passwordErrLabel.setVisible(false);
            }
            case 2 -> {
                System.out.println("Invalid password!");
                usernameErrLabel.setVisible(false);
                passwordErrLabel.setVisible(true);
            }
            case 3 -> {
                System.out.println("Invalid username and password!");
                usernameErrLabel.setVisible(true);
                passwordErrLabel.setVisible(true);
            }
        }

        usernameTextField.clear();
        passwordPasswordField.clear();
        cpuTextField.clear();
        ramTextField.clear();

        SceneManager.getInstance().switchScene(SceneManager.States.HOME);

    }

    public void goBackClick() {
        SceneManager.getInstance().switchScene(SceneManager.States.LOGIN);
    }

}
