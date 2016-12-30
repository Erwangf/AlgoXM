package view.component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Erwan on 29/12/2016.
 */
public class ExportPane extends GridPane{
    public ExportPane() {
        Label label = new Label("Choissisez le nom du fichier : ");
        TextField textField = new TextField();
        textField.setText("nom du fichier ...");
        Button buttonExp = new Button("buttonExp");
        buttonExp.setText("Exporter");
        //	    AJOUTER BARRE CHARGEMENT


        GridPane.setConstraints(label, 0, 2);
        this.getChildren().add(label);
        GridPane.setConstraints(textField, 0, 3);
        this.getChildren().add(textField);
        GridPane.setConstraints(buttonExp, 0, 4);
        this.getChildren().add(buttonExp);
    }
}
