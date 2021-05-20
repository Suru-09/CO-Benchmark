package service;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class CustomNotification {

    private Notifications notification;

    public enum Type {
        ERROR("/images/error.png"),
        SUCCESS("images/success.png");

        public String url;

        Type(String url) {
            this.url = url;
        }
    }

    public CustomNotification(String title, String text, Type type) {
        Image image = new Image(type.url, true);
        ImageView view = new ImageView(image);
        view.setFitHeight(50);
        view.setFitWidth(50);

        notification = Notifications.create()
                .title(title)
                .text(text)
                .graphic(view)
                .hideAfter(Duration.seconds(0.74))
                .position(Pos.CENTER)
                .onAction(e -> {
                    System.out.println("Clicked on notification");
                });
        notification.darkStyle();
        notification.show();
    }
}
