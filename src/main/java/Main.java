import controller.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private SceneManager sceneManager;

    @Override
    public void start(Stage stage) throws Exception {
        SceneManager.setUp(stage);
        SceneManager.getInstance().switchScene(SceneManager.States.LOGIN);
    }

    public static void main(String[] args){
        launch(args);
    }

}
