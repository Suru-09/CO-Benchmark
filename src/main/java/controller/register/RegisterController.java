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
    public Label cpuErrLabel;
    public Label ramErrLabel;

    public void signUpClick() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        String cpu = cpuTextField.getText();
        String ram = ramTextField.getText();

        usernameErrLabel.setVisible(false);
        passwordErrLabel.setVisible(false);
        cpuErrLabel.setVisible(false);
        ramErrLabel.setVisible(false);

        boolean valid = true;
        if ( !RegisterService.validUsername(username) ){
            System.out.println("Invalid username!");
            usernameErrLabel.setVisible(true);
            valid = false;
        }

        if ( !RegisterService.validPassword(password) ){
            System.out.println("Invalid password!");
            passwordErrLabel.setVisible(true);
            valid = false;
        }

        if ( !RegisterService.validCPU(cpu) ){
            System.out.println("Invalid cpu!");
            cpuErrLabel.setVisible(true);
            valid = false;
        }

        if ( !RegisterService.validRam(ram) ){
            System.out.println("Invalid ram!");
            ramErrLabel.setVisible(true);
            valid = false;
        }

        if ( !valid ) return;


        if ( !registerService.signUp(username, password, cpu, ram) ){
            System.out.println("User already exists!");
            return;
        }

        usernameTextField.clear();
        passwordPasswordField.clear();

        SceneManager.getInstance().switchScene(SceneManager.States.HOME);
    }

    public void goBackClick() {
        SceneManager.getInstance().switchScene(SceneManager.States.LOGIN);
    }

    public void setup(){
//        String[] cpuSuggestions = {"bruh", "hatz"};
//
//        TextFields.bindAutoCompletion(cpuTextField, cpuSuggestions);
    }

}
