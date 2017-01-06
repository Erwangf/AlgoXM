package view.component;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Created by Erwan on 29/12/2016.
 */
public class AdvancedFilterPane extends GridPane{
    public AdvancedFilterPane(){
        super();

        Label criteriaSearchLabel = new Label("Recherche par critères :");

        Label label2 = new Label("au");
        RadioButton rb1 = new RadioButton("Date de publication : du ");
        DatePicker datePickerDebut = new DatePicker();
        DatePicker datePickerFin = new DatePicker();


        RadioButton rb2 = new RadioButton("Auteur");
        TextField textFieldAuteur = new TextField();
        textFieldAuteur.setText("Alfred de Musset");
        Label titleLabel = new Label("Titre : ");
        TextField textFieldTitre = new TextField();
        textFieldTitre.setText("On ne Badine pas avec l'Amour");
        Label linkLabel = new Label("Source :");

        TextField textFieldSource = new TextField();
        textFieldSource.setText("Topito");



        Button buttonTrier = new Button("buttonTrier");
        buttonTrier.setText("Trier");


        GridPane.setConstraints(criteriaSearchLabel, 0, 2);
        this.getChildren().add(criteriaSearchLabel);

        GridPane.setConstraints(rb1, 0, 3);
        this.getChildren().add(rb1);

        GridPane.setConstraints(datePickerDebut, 0, 4);
        this.getChildren().add(datePickerDebut);

        GridPane.setConstraints(label2, 1, 4);
        this.getChildren().add(label2);

        GridPane.setConstraints(datePickerFin, 2, 4);
        this.getChildren().add(datePickerFin);

        GridPane.setConstraints(rb2, 0, 5);
        this.getChildren().add(rb2);

        GridPane.setConstraints(textFieldAuteur, 1, 5);
        this.getChildren().add(textFieldAuteur);

        GridPane.setConstraints(titleLabel, 0, 6);
        this.getChildren().add(titleLabel);

        GridPane.setConstraints(textFieldTitre, 1, 6);
        this.getChildren().add(textFieldTitre);

        GridPane.setConstraints(linkLabel, 0, 7);



        this.getChildren().add(linkLabel);
        GridPane.setConstraints(textFieldSource, 1, 7);
        this.getChildren().add(textFieldSource);

        GridPane.setConstraints(buttonTrier, 0, 8);
        this.getChildren().add(buttonTrier);

    }
}
