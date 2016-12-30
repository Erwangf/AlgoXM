package view.component;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 * Created by Erwan on 29/12/2016.
 */
public class FrequencyPane extends GridPane{
    public FrequencyPane() {
        TableView table = new TableView();
        TableColumn NomCol = new TableColumn("Nom");
        TableColumn RecCol = new TableColumn("Nombre de récurence");
        table.getColumns().addAll(RecCol, NomCol);

        GridPane.setConstraints(table, 1, 2);
        this.getChildren().add(table);
    }
}
