package uploader.tasks;

import javafx.concurrent.Task;
import javafx.scene.control.TableView;
import uploader.entity.UploadJob;

public class FileUploadTask extends Task<Void> {
    private TableView<UploadJob> uploadTable;

    public FileUploadTask(TableView<UploadJob> table) {
        this.uploadTable = table;
    }

    @Override
    protected Void call() throws Exception {
        for (UploadJob item : uploadTable.getItems()) {
            item.setStatus("done");
            uploadTable.refresh();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
