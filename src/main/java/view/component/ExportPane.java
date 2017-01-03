package view.component;

import controller.IOController;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import view.MainGUI;

import java.io.File;

/**
 * Created by Erwan on 29/12/2016.
 */
public class ExportPane extends GridPane {
    private final MainGUI mainGUI;

    public ExportPane(MainGUI mainGUI) {

        Button buttonExp = new Button("buttonExp");
        buttonExp.setText("Exporter");


        buttonExp.setOnAction((e) -> this.showExportFileChooser());
        //TODO  AJOUTER BARRE CHARGEMENT



        GridPane.setConstraints(buttonExp, 0, 5);
        this.getChildren().add(buttonExp);

        this.mainGUI = mainGUI;
    }

    public void showExportFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save...");
        File f = fileChooser.showSaveDialog(mainGUI.stage);

        if (f != null) {
            writeToFile(f.getPath());
        }
    }

    public void writeToFile(String path) {
        if (path.length() != 0) {
            IOController.writeToCSV(this.mainGUI.currentArticles, path);
        }

    }


}
