package view.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Article;

import java.util.ArrayList;


public class ArticleListPane extends GridPane {

    private final ObservableList<Article> list;
    private final TableView<Article> tableView;

    public ArticleListPane() {






        tableView = new TableView<>();
        tableView.setEditable(false);

        TableColumn<Article, String> titleColumn = new TableColumn<>("Titre");
        titleColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getTitle()));
        titleColumn.setSortable(false);
        TableColumn<Article, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory((p -> new SimpleStringProperty(p.getValue().getDescription().substring(0, 40))));

        descriptionColumn.setSortable(false);
        TableColumn<Article, String> authorColumn = new TableColumn<>("Auteur");
        authorColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAuthor()));
        authorColumn.setSortable(false);

        TableColumn<Article, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getStrDate()));
        dateColumn.setSortable(false);

        TableColumn<Article, String> rssColumn = new TableColumn<>("RSS");
        rssColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getRss()));
        rssColumn.setSortable(false);


        TableColumn<Article, String> linkColumn = new TableColumn<>("Lien");
        linkColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getLink().toString()));
        linkColumn.setSortable(false);

        tableView.getColumns().addAll(
                titleColumn,
                descriptionColumn,
                authorColumn,
                dateColumn,
                rssColumn,
                linkColumn
        );

        this.list = FXCollections.observableArrayList();


        tableView.setItems(this.list);


        GridPane.setConstraints(tableView, 0, 1);
        GridPane.setHgrow(tableView, Priority.ALWAYS);
        GridPane.setVgrow(tableView, Priority.ALWAYS);
        this.getChildren().add(tableView);
    }


    public void setArticles(ArrayList<Article> articleList) {
        this.list.removeAll(this.list);
        articleList.forEach(this.list::add);
        System.out.println(this.list.size());
        System.out.println(this.tableView.getItems().size());

    }





}