package uploader.forms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginForm {

    public static Scene createScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Log In");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        HBox hbSceneTitle = new HBox(10);
        hbSceneTitle.setAlignment(Pos.CENTER);
        hbSceneTitle.getChildren().add(sceneTitle);
        grid.add(hbSceneTitle, 0, 0, 2, 1);

        Label userNameLabel = new Label("User name:");
        grid.add(userNameLabel, 0, 1);
        TextField userNameField = new TextField();
        grid.add(userNameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button signInButton = new Button("Sign In");
        HBox hbSingInButton = new HBox(10);
        hbSingInButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbSingInButton.getChildren().add(signInButton);
        grid.add(hbSingInButton, 1, 4);

        final Text actionText = new Text();
        grid.add(actionText, 1, 6);

        Scene scene = new Scene(grid, 300, 275);
        return scene;
    }
}
