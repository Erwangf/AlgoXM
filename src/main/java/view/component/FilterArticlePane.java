package view.component;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Created by Erwan on 29/12/2016.
 */
public class FilterArticlePane extends GridPane {

    public FilterArticlePane(){

        super();

        Label label = new Label("Vous souhaitez trier les articles selon : ");
        TextField textField = new TextField();
        textField.setText("mot clés...");
        Label label2 = new Label("au");
        RadioButton rb1 = new RadioButton("Date de publication : du ");
        DatePicker datePickerDebut = new DatePicker();
        DatePicker datePickerFin = new DatePicker();

        ToggleGroup group = new ToggleGroup();
        RadioButton rb2 = new RadioButton("Titre par ordre alphabéthique");
        RadioButton rb3 = new RadioButton("Auteur par ordre alphabéthique");
        RadioButton rb4 = new RadioButton("Source par ordre alphabéthique");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        rb4.setToggleGroup(group);


        Button buttonTrier = new Button("buttonTrier");
        buttonTrier.setText("Trier");


        GridPane.setConstraints(label, 0, 2);
        this.getChildren().add(label);

        GridPane.setConstraints(textField, 0, 3);
        this.getChildren().add(textField);

        GridPane.setConstraints(rb1, 0, 4);
        this.getChildren().add(rb1);

        GridPane.setConstraints(datePickerDebut, 0, 5);
        this.getChildren().add(datePickerDebut);

        GridPane.setConstraints(label2, 1, 5);
        this.getChildren().add(label2);

        GridPane.setConstraints(datePickerFin, 2, 5);
        this.getChildren().add(datePickerFin);

        GridPane.setConstraints(rb3, 0, 6);
        this.getChildren().add(rb3);

        GridPane.setConstraints(rb2, 0, 7);
        this.getChildren().add(rb2);

        GridPane.setConstraints(rb4, 0, 8);
        this.getChildren().add(rb4);

        GridPane.setConstraints(buttonTrier, 0, 9);
        this.getChildren().add(buttonTrier);


    }
}
