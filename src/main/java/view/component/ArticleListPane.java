package view.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Article;


public class ArticleListPane extends GridPane {

    private ObservableList<Article> list;
    private TableView<Article> tableView;
    public ArticleListPane() {

        tableView = new TableView<>();
        TableView<Article> tableView = new TableView<>();
        TableColumn<Article,String> titleCol = new TableColumn<>("Titre");

        TableColumn<Article, String> titleColumn = new TableColumn<>("Titre");
        titleColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTitle()));

        TableColumn<Article, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory((p -> new SimpleStringProperty(p.getValue().getDescription().substring(0,40))));

        TableColumn<Article, String> authorColumn = new TableColumn<>("Auteur");
        authorColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAuthor()));

        TableColumn<Article, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDate().toString()));

        TableColumn<Article, String> rssColumn = new TableColumn<>("RSS");
        rssColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getRss()));

        TableColumn<Article, String> linkColumn = new TableColumn<>("Lien");
        linkColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getLink().toString()));


        tableView.getColumns().addAll(
                titleColumn,
                descriptionColumn,
                authorColumn,
                dateColumn,
                rssColumn,
                linkColumn
        );

        list = FXCollections.observableList(Article.getDefaultArticles());

        tableView.setItems(list);


        GridPane.setConstraints(tableView, 0, 1);
        GridPane.setHgrow(tableView, Priority.ALWAYS);
        GridPane.setVgrow(tableView, Priority.ALWAYS);
        this.getChildren().add(tableView);
    }
}
