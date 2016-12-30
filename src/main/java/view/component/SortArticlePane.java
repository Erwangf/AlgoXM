package view.component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * Created by Erwan on 29/12/2016.
 */
public class SortArticlePane extends GridPane {



    public SortArticlePane() // PAGE TRIS ARTICLES
    {
        super();

        Label label = new Label("Vous souhaitez trier les articles selon : ");
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Date de publication");
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

        GridPane.setConstraints(rb1, 0, 3);
        this.getChildren().add(rb1);

        GridPane.setConstraints(rb2, 0, 4);
        this.getChildren().add(rb2);

        GridPane.setConstraints(rb3, 0, 5);
        this.getChildren().add(rb3);

        GridPane.setConstraints(rb4, 0, 6);
        this.getChildren().add(rb4);

        GridPane.setConstraints(buttonTrier, 0, 7);
        this.getChildren().add(buttonTrier);

    }
}
