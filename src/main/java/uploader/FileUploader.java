package uploader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uploader.forms.LoginForm;
import uploader.forms.UploaderForm;

public class FileUploader extends Application {
    private Scene loginScene = LoginForm.createScene();
    private Scene uploaderForm = UploaderForm.createScene();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Portal file uploader");
        primaryStage.setScene(uploaderForm);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
