package view.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Article;
import model.SortableAttributes;
import org.apache.lucene.queryparser.classic.ParseException;
import view.MainGUI;

import java.io.IOException;
import java.util.ArrayList;


public class ArticleListPane extends BorderPane {


    private final ObservableList<Article> list;
    private final TableView<Article> tableView;
    private final MainGUI mainGUI;
    private int nbArticle = 100;
    private String sortCriteria = SortableAttributes.RELEVANCE;
    private final Label orderLabel;
    private final RadioButton rbo1;
    private final RadioButton rbo2;
    private final ToggleGroup sortGroup = new ToggleGroup();
    private final ToggleGroup orderGroup = new ToggleGroup();

    public ArticleListPane(MainGUI mainGUI) {
        super();
        this.mainGUI = mainGUI;
        tableView = new TableView<>();
        tableView.setEditable(false);

        TableColumn<Article, String> titleColumn = new TableColumn<>("Titre");
        titleColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTitle()));
        titleColumn.setSortable(false);
        TableColumn<Article, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory((p -> new SimpleStringProperty(p.getValue().getDescription().substring(0, Math.min(p.getValue().getDescription().length(), 40)))));

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
        linkColumn.setCellValueFactory(p -> new SimpleStringProperty((p.getValue().getLink() == null ? "" : p.getValue().getLink()).toString()));
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
        GridPane filterPane = new GridPane();

        Label label = new Label("Tri des articles par :");


        RadioButton rb1 = new RadioButton("Tri par pertinence");
        RadioButton rb2 = new RadioButton("Titre");
        RadioButton rb3 = new RadioButton("Auteur");

        rb1.setUserData(SortableAttributes.RELEVANCE);
        rb2.setUserData(SortableAttributes.TITLE);
        rb3.setUserData(SortableAttributes.AUTHOR);

        rb1.setToggleGroup(sortGroup);
        rb2.setToggleGroup(sortGroup);
        rb3.setToggleGroup(sortGroup);

        sortGroup.selectToggle(rb1);


        orderLabel = new Label("Ordre:");
        rbo1 = new RadioButton("Croissant");
        rbo2 = new RadioButton("Décroissant");
        rbo1.setUserData(true);
        rbo2.setUserData(false);
        rbo1.setToggleGroup(orderGroup);
        rbo2.setToggleGroup(orderGroup);

        orderLabel.setVisible(false);
        rbo1.setVisible(false);
        rbo2.setVisible(false);

        orderGroup.selectToggle(rbo1);

        sortGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {

            if (sortGroup.getSelectedToggle() != null) {


                sortCriteria = (String) sortGroup.getSelectedToggle().getUserData();

                if (!sortCriteria.equals(SortableAttributes.RELEVANCE)) {
                    orderLabel.setVisible(true);
                    rbo1.setVisible(true);
                    rbo2.setVisible(true);
                } else {
                    orderLabel.setVisible(false);
                    rbo1.setVisible(false);
                    rbo2.setVisible(false);
                }
            }

        });
        Button buttonTrier = new Button("buttonTrier");
        buttonTrier.setText("Trier");


        //Positionnement au sein du filterPane

        GridPane.setConstraints(label, 0, 0);
        filterPane.getChildren().add(label);

        GridPane.setConstraints(rb1, 0, 1);
        filterPane.getChildren().add(rb1);

        GridPane.setConstraints(rb2, 0, 2);
        filterPane.getChildren().add(rb2);


        GridPane.setConstraints(rb3, 0, 3);
        filterPane.getChildren().add(rb3);

        GridPane.setConstraints(orderLabel, 0, 4);
        filterPane.getChildren().add(orderLabel);

        GridPane.setConstraints(rbo1, 0, 5);
        filterPane.getChildren().add(rbo1);

        GridPane.setConstraints(rbo2, 0, 6);
        filterPane.getChildren().add(rbo2);

        GridPane.setConstraints(buttonTrier, 0, 7);
        filterPane.getChildren().add(buttonTrier);
        buttonTrier.setOnAction((e) -> switchtrie());


        this.setCenter(tableView);
        this.setRight(filterPane);
    }


    public void switchtrie() {

        try {
            boolean ascending = Boolean.parseBoolean(orderGroup.getSelectedToggle().getUserData().toString());
            setArticles(mainGUI.index.search("*:*", nbArticle, sortGroup.getSelectedToggle().getUserData().toString(), ascending));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }


    public void setArticles(ArrayList<Article> articleList) {
        this.list.removeAll(this.list);
        articleList.forEach(this.list::add);
        System.out.println(this.list.size());
        System.out.println(this.tableView.getItems().size());

    }


    public void refresh() {
        setArticles(mainGUI.index.getFirstArticles(nbArticle));
    }
}