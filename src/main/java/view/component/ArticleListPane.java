package view.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Article;
import model.ArticleAttributes;
import model.SortableAttributes;
import org.apache.lucene.queryparser.classic.ParseException;
import view.MainGUI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    private final TextField titleField = new TextField();
    private final TextField descriptionField = new TextField();
    private final TextField authorField = new TextField();
    private final TextField sourceField = new TextField();
    private final TextField rssField = new TextField();
    private String searchMode = "criteria";
    private final DatePicker datePickerDebut = new DatePicker();
    private final DatePicker datePickerFin = new DatePicker();
    private final CheckBox useDateCB = new CheckBox("Filtrer sur la période");
    private final TextField limitField;

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

        Label searchByCriteriaLabel = new Label("Recherche par critère");


        Label titleLabel = new Label("Titre : ");

        Label descriptionLabel = new Label("Description : ");

        Label authorLabel = new Label("Auteur : ");

        Label sourceLabel = new Label("Source : ");

        Label rssLabel = new Label("RSS : ");

        Label fromLabel = new Label("De :");

        Label toLabel = new Label("A :");

        Label limitLabel = new Label("Nombre de résultats");
        limitField = new TextField("100");




        Button buttonSearch = new Button("Rechercher");

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


        GridPane.setConstraints(titleLabel, 0, 8);
        filterPane.getChildren().add(titleLabel);

        GridPane.setConstraints(titleField, 1, 8);
        filterPane.getChildren().add(titleField);

        GridPane.setConstraints(descriptionLabel, 0, 9);
        filterPane.getChildren().add(descriptionLabel);

        GridPane.setConstraints(descriptionField, 1, 9);
        filterPane.getChildren().add(descriptionField);

        GridPane.setConstraints(authorLabel, 0, 10);
        filterPane.getChildren().add(authorLabel);

        GridPane.setConstraints(authorField, 1, 10);
        filterPane.getChildren().add(authorField);

        GridPane.setConstraints(sourceLabel, 0, 11);
        filterPane.getChildren().add(sourceLabel);

        GridPane.setConstraints(sourceField, 1, 11);
        filterPane.getChildren().add(sourceField);

        GridPane.setConstraints(rssLabel, 0, 12);
        filterPane.getChildren().add(rssLabel);

        GridPane.setConstraints(rssField, 1, 12);
        filterPane.getChildren().add(rssField);

        GridPane.setConstraints(fromLabel, 0, 13);
        filterPane.getChildren().add(fromLabel);

        GridPane.setConstraints(toLabel, 1, 13);
        filterPane.getChildren().add(toLabel);

        GridPane.setConstraints(datePickerDebut, 0, 14);
        filterPane.getChildren().add(datePickerDebut);

        GridPane.setConstraints(datePickerFin, 1, 14);
        filterPane.getChildren().add(datePickerFin);

        GridPane.setConstraints(useDateCB, 0, 15);
        filterPane.getChildren().add(useDateCB);

        GridPane.setConstraints(limitLabel, 0, 16);
        filterPane.getChildren().add(limitLabel);

        GridPane.setConstraints(limitField, 1, 16);
        filterPane.getChildren().add(limitField);


        GridPane.setConstraints(buttonSearch, 0, 17);
        filterPane.getChildren().add(buttonSearch);
        buttonSearch.setOnAction((e) -> switchtrie());


        this.setCenter(tableView);
        this.setRight(filterPane);
    }



    public void switchtrie() {

        this.nbArticle = Integer.parseInt(this.limitField.getText());


        try {
            if (searchMode.equals("criteria")) {
                boolean ascending = Boolean.parseBoolean(orderGroup.getSelectedToggle().getUserData().toString());

                HashMap<String, String> map = new LinkedHashMap<>();
                if (titleField.getText().length() > 0) map.put(ArticleAttributes.TITLE, titleField.getText());
                if (descriptionField.getText().length() > 0)
                    map.put(ArticleAttributes.DESCRIPTION, descriptionField.getText());
                if (authorField.getText().length() > 0) map.put(ArticleAttributes.AUTHOR, authorField.getText());
                if (sourceField.getText().length() > 0) map.put(ArticleAttributes.LINK, sourceField.getText());
                if (rssField.getText().length() > 0) map.put(ArticleAttributes.RSS, rssField.getText());
                if (useDateCB.isSelected()) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy", Locale.ENGLISH);
//                    String query = "{"
//                            + dateFormat.parse(datePickerDebut.getEditor().getText()).getTime()/100
//                            + " TO "
//                            + dateFormat.parse(datePickerFin.getEditor().getText()).getTime()/100
//                            + "}";
                    String query = "(>"
                            + dateFormat.parse(datePickerDebut.getEditor().getText()).getTime()/100
                            +")";

                    map.put(ArticleAttributes.DATE,
                            query);
                    System.out.println(query);

                }
                //mod_date:[20020101 TO 20030101]

                // TODO date


                setArticles(mainGUI.index.searchByTerms(map, nbArticle, sortGroup.getSelectedToggle().getUserData().toString(), ascending));
            }


        } catch (IOException | ParseException | java.text.ParseException e) {
            e.printStackTrace();
            setArticles(new ArrayList<>());


        }

        this.list.forEach(this.mainGUI.currentArticles::add);

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