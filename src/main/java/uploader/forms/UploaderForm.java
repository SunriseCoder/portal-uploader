package uploader.forms;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uploader.entity.UploadJob;
import uploader.tasks.FileUploadTask;

public class UploaderForm {
    private static TableView<UploadJob> uploadTable;

    public static Scene createScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().add(rowConstraints);

        uploadTable = createTable();
        grid.add(uploadTable, 0, 0, 1, 2);

        VBox rightBox = new VBox(10);
        grid.add(rightBox, 1, 1);

        HBox tableButtonsBox = createTableButtons();
        rightBox.getChildren().add(tableButtonsBox);

        HBox processButtonsBox = createProcessButtons();
        rightBox.getChildren().add(processButtonsBox);

        VBox progressBarsBox = createProgressBars();
        rightBox.getChildren().add(progressBarsBox);

        TextArea log = new TextArea();
        log.setMaxHeight(Double.MAX_VALUE);
        grid.add(log, 1, 0);

        Scene scene = new Scene(grid);
        return scene;
    }

    private static TableView<UploadJob> createTable() {
        TableView<UploadJob> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<UploadJob, String> columnName = new TableColumn<>("Name");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(columnName);

        TableColumn<UploadJob, String> columnStatus = new TableColumn<>("Status");
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.getColumns().add(columnStatus);

        return table;
    }

    private static HBox createTableButtons() {
        HBox buttonsBox = new HBox(10);

        Button addButton = new Button("Add");
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setOnAction((event) -> addTableEntry(uploadTable));
        buttonsBox.getChildren().add(addButton);

        Button changeButton = new Button("Change");
        changeButton.setMaxWidth(Double.MAX_VALUE);
        changeButton.setOnAction((event) -> changeItem(uploadTable));
        buttonsBox.getChildren().add(changeButton);

        return buttonsBox;
    }

    private static HBox createProcessButtons() {
        HBox buttonsBox = new HBox(10);

        Button startButton = new Button("Start");
        startButton.setMaxWidth(Double.MAX_VALUE);
        startButton.setOnAction((event) -> startUpload(uploadTable));
        buttonsBox.getChildren().add(startButton);

        Button pauseButton = new Button("Pause");
        pauseButton.setMaxWidth(Double.MAX_VALUE);
        pauseButton.setOnAction((event) -> startUpload(uploadTable));
        buttonsBox.getChildren().add(pauseButton);

        Button abortButton = new Button("Abort");
        abortButton.setMaxWidth(Double.MAX_VALUE);
        abortButton.setOnAction((event) -> showLoginForm());
        buttonsBox.getChildren().add(abortButton);

        return buttonsBox;
    }

    private static VBox createProgressBars() {
        VBox progressBarsBox = new VBox(10);

        ProgressBar singleProgressBar = new ProgressBar();
        progressBarsBox.getChildren().add(singleProgressBar);

        ProgressBar totalProgressBar = new ProgressBar();
        progressBarsBox.getChildren().add(totalProgressBar);

        return progressBarsBox;
    }

    private static void addTableEntry(TableView<UploadJob> uploadTable) {
        UploadJob uploadJob = new UploadJob();
        uploadJob.setName("Eh");
        uploadJob.setStatus("ready");
        uploadTable.getItems().add(uploadJob);
    }

    private static void changeItem(TableView<UploadJob> uploadTable) {
        int size = uploadTable.getItems().size();
        if (size > 0) {
            UploadJob item = uploadTable.getItems().get(size - 1);
            item.setStatus("finished");
            uploadTable.refresh();
        }
    }

    private static void startUpload(TableView<UploadJob> uploadTable) {
        Task<Void> task = new FileUploadTask(uploadTable);
        Thread thread = new Thread(task);
        thread.start();
    }

    private static void showLoginForm() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Scene loginScene = LoginForm.createScene();
        stage.setScene(loginScene);
        stage.show();
    }
}
