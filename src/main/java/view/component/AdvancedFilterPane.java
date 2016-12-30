package view.component;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Created by Erwan on 29/12/2016.
 */
public class AdvancedFilterPane extends GridPane{
    public AdvancedFilterPane(){
        super();

        Label label = new Label("Vous souhaitez effectuer un filtre avancé sur les différents critères : ");

        Label label2 = new Label("au");
        RadioButton rb1 = new RadioButton("Date de publication : du ");
        DatePicker datePickerDebut = new DatePicker();
        DatePicker datePickerFin = new DatePicker();

        ToggleGroup group = new ToggleGroup();
        RadioButton rb2 = new RadioButton("Auteur");
        TextField textFieldAuteur = new TextField();
        textFieldAuteur.setText("Alfred de Musset");
        RadioButton rb3 = new RadioButton("Titre");
        TextField textFieldTitre = new TextField();
        textFieldTitre.setText("On ne Badine pas avec l'Amour");
        RadioButton rb4 = new RadioButton("Source");
        TextField textFieldSource = new TextField();
        textFieldSource.setText("Topito");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        rb4.setToggleGroup(group);


        Button buttonTrier = new Button("buttonTrier");
        buttonTrier.setText("Trier");


        GridPane.setConstraints(label, 0, 2);
        this.getChildren().add(label);

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

        GridPane.setConstraints(rb3, 0, 6);
        this.getChildren().add(rb3);

        GridPane.setConstraints(textFieldTitre, 1, 6);
        this.getChildren().add(textFieldTitre);

        GridPane.setConstraints(rb4, 0, 7);



        this.getChildren().add(rb4);
        GridPane.setConstraints(textFieldSource, 1, 7);
        this.getChildren().add(textFieldSource);

        GridPane.setConstraints(buttonTrier, 0, 8);
        this.getChildren().add(buttonTrier);

    }
}
