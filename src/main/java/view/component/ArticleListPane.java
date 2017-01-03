package view.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import model.Article;

import java.util.ArrayList;


public class ArticleListPane extends GridPane {

    private ObservableList<Article> list;
    private TableView<Article> tableView;

    public ArticleListPane() {


        Callback<TableColumn<Article, String>, TableCell<Article, String>> stringCellFactory =
                p -> new MyStringTableCell();


        tableView = new TableView<>();
        tableView.setEditable(false);

        TableColumn<Article, String> titleCol = new TableColumn<>("Titre");

        TableColumn<Article, String> titleColumn = new TableColumn<>("Titre");
        titleColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("title"));


        tableView.getColumns().add(titleColumn);
        /*TableColumn<Article, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory((p -> new SimpleStringProperty(p.getValue().getDescription().substring(0, 40))));
        descriptionColumn.setCellFactory(stringCellFactory);

        TableColumn<Article, String> authorColumn = new TableColumn<>("Auteur");
        authorColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAuthor()));
        authorColumn.setCellFactory(stringCellFactory);

        TableColumn<Article, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDate().toString()));
        dateColumn.setCellFactory(stringCellFactory);


        TableColumn<Article, String> rssColumn = new TableColumn<>("RSS");
        rssColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getRss()));
        rssColumn.setCellFactory(stringCellFactory);


        TableColumn<Article, String> linkColumn = new TableColumn<>("Lien");
        linkColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getLink().toString()));
        linkColumn.setCellFactory(stringCellFactory);



        tableView.getColumns().addAll(
                titleColumn,
                descriptionColumn,
                authorColumn,
                dateColumn,
                rssColumn,
                linkColumn
        );*/

        list = FXCollections.observableList(new ArrayList<>());


        tableView.setItems(list);


        GridPane.setConstraints(tableView, 0, 1);
        GridPane.setHgrow(tableView, Priority.ALWAYS);
        GridPane.setVgrow(tableView, Priority.ALWAYS);
        this.getChildren().add(tableView);
    }


    public void setArticles(ObservableList<Article> articleList) {
        tableView.getItems().removeAll(tableView.getItems());

        this.getChildren().clear();
        tableView.setItems(articleList);
        //this.getChildren().add(tableView);



    }

    class MyStringTableCell extends TableCell<Article, String> {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
            setGraphic(null);
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }
}