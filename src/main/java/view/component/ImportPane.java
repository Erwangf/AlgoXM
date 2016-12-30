package view.component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Erwan on 29/12/2016.
 */
public class ImportPane extends GridPane {

    public ImportPane(){
        super();

        Label label = new Label("Choissisez le fichier à importer : ");
        TextField textField = new TextField();
        textField.setText("chemin du fichier ...");
        Button buttonParc = new Button("buttonParc");
        buttonParc.setText("Parcourir ...");
        RadioButton rb1 = new RadioButton("Je souhaite conserver les données déjà enregistrées");
        Button buttonImp = new Button("buttonImp");
        buttonImp.setText("Importer");


        GridPane.setConstraints(label, 0, 2);
        this.getChildren().add(label);

        GridPane.setConstraints(textField, 0, 3);
        this.getChildren().add(textField);

        GridPane.setConstraints(buttonParc, 1, 3);
        this.getChildren().add(buttonParc);

        GridPane.setConstraints(rb1, 0, 4);
        this.getChildren().add(rb1);

        GridPane.setConstraints(buttonImp, 0, 5);
        this.getChildren().add(buttonImp);
    }
}
