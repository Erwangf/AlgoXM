package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainGUI extends Application {
    Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2;
    Stage thestage;

    StackPane root = new StackPane();
    MenuBar menuBar = new MenuBar();
    Menu menuExpImp = new Menu("Import/Export");
    MenuItem menuImp = new MenuItem("Importer les articles");
    MenuItem menuExp = new MenuItem("Exporter les articles");
    Menu menuArt = new Menu("Articles");
    Menu menuTris = new Menu("Tris et filtres");
    MenuItem menuTrisArt = new MenuItem("Trier les articles");
    MenuItem menuFiltArt = new MenuItem("Filtrer les articles");
    MenuItem menuFiltAv = new MenuItem("Filtre avancé");
    Menu menuStat = new Menu("Statistiques");
    MenuItem menuMots = new MenuItem("Nuage de Mots");
    MenuItem menuFreq = new MenuItem("Fréquence");
    Menu menuProp = new Menu("à Propos");

    GridPane grid = new GridPane();


    public Scene importScene() //PAGE IMPORTER
    {
        grid.getChildren().clear();
        Label label = new Label("Choissisez le fichier à importer : ");
        TextField textField = new TextField();
        textField.setText("chemin du fichier ...");
        Button buttonParc = new Button("buttonParc");
        buttonParc.setText("Parcourir ...");
        RadioButton rb1 = new RadioButton("Je souhaite conserver les données déjà enregistrées");
        Button buttonImp = new Button("buttonImp");
        buttonImp.setText("Importer");
        //	    AJOUTER BARRE CHARGEMENT


        GridPane.setConstraints(menuBar, 0, 0);
        grid.getChildren().add(menuBar);

        GridPane.setConstraints(label, 0, 2);
        grid.getChildren().add(label);
        GridPane.setConstraints(textField, 0, 3);
        grid.getChildren().add(textField);
        GridPane.setConstraints(buttonParc, 1, 3);
        grid.getChildren().add(buttonParc);
        GridPane.setConstraints(rb1, 0, 4);
        grid.getChildren().add(rb1);
        GridPane.setConstraints(buttonImp, 0, 5);
        grid.getChildren().add(buttonImp);

        root.getChildren().addAll(grid);
        scene1 = new Scene(root, 800, 600);


        return scene1;
    }

    public Scene exportScene()//PAGE EXPORTER
    {
        grid.getChildren().clear();
        Label label = new Label("Choissisez le nom du fichier : ");
        TextField textField = new TextField();
        textField.setText("nom du fichier ...");
        Button buttonExp = new Button("buttonExp");
        buttonExp.setText("Exporter");
        //	    AJOUTER BARRE CHARGEMENT

        GridPane.setConstraints(menuBar, 0, 0);
        grid.getChildren().add(menuBar);

        GridPane.setConstraints(label, 0, 2);
        grid.getChildren().add(label);
        GridPane.setConstraints(textField, 0, 3);
        grid.getChildren().add(textField);
        GridPane.setConstraints(buttonExp, 0, 4);
        grid.getChildren().add(buttonExp);


        root.getChildren().addAll(grid);
        scene1 = new Scene(root, 800, 600);
        return scene1;
    }

    public Scene articleScene() // PAGE ARTICLES
    {
        grid.getChildren().clear();

        TableView table = new TableView();
        TableColumn TitreCol = new TableColumn("Titre");
        TableColumn DescCol = new TableColumn("Description");
        TableColumn DateCol = new TableColumn("Date");
        TableColumn AuteurCol = new TableColumn("Auteur");
        TableColumn URLCol = new TableColumn("Lien URL");
        TableColumn RSSCol = new TableColumn("Flux RSS");
        table.getColumns().addAll(TitreCol, DescCol, DateCol, AuteurCol, URLCol, RSSCol);


        grid.getChildren().add(menuBar);
        GridPane.setConstraints(table, 0, 2);
        grid.getChildren().add(table);

        root.getChildren().addAll(grid);
        scene1 = new Scene(root, 800, 600);

        return scene1;
    }

    public Scene trisArticleScene() // PAGE TRIS ARTICLES
    {
        GridPane grid = new GridPane();
        StackPane root = new StackPane();

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
        //	    AJOUTER BARRE CHARGEMENT


        GridPane.setConstraints(menuBar, 0, 0);
        grid.getChildren().add(menuBar);

        GridPane.setConstraints(label, 0, 2);
        grid.getChildren().add(label);
        GridPane.setConstraints(rb1, 0, 3);
        grid.getChildren().add(rb1);
        GridPane.setConstraints(rb2, 0, 4);
        grid.getChildren().add(rb2);
        GridPane.setConstraints(rb3, 0, 5);
        grid.getChildren().add(rb3);
        GridPane.setConstraints(rb4, 0, 6);
        grid.getChildren().add(rb4);
        GridPane.setConstraints(buttonTrier, 0, 7);
        grid.getChildren().add(buttonTrier);
        root.getChildren().clear();
        root.getChildren().addAll(grid);

        //scene1 = new Scene(root, 800, 600);

        return new Scene(root,800,600);
    }

    public Scene filtreArticleScene() // PAGE FILTRE ARTICLES
    {

        GridPane grid = new GridPane();
        StackPane root = new StackPane();
        grid.getChildren().clear();

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
        //	    AJOUTER BARRE CHARGEMENT


        GridPane.setConstraints(menuBar, 0, 0);
        grid.getChildren().add(menuBar);

        GridPane.setConstraints(label, 0, 2);
        grid.getChildren().add(label);
        GridPane.setConstraints(textField, 0, 3);
        grid.getChildren().add(textField);
        GridPane.setConstraints(rb1, 0, 4);
        grid.getChildren().add(rb1);
        GridPane.setConstraints(datePickerDebut, 0, 5);
        grid.getChildren().add(datePickerDebut);
        GridPane.setConstraints(label2, 1, 5);
        grid.getChildren().add(label2);
        GridPane.setConstraints(datePickerFin, 2, 5);
        grid.getChildren().add(datePickerFin);
        GridPane.setConstraints(rb3, 0, 6);
        grid.getChildren().add(rb3);
        GridPane.setConstraints(rb2, 0, 7);
        grid.getChildren().add(rb2);
        GridPane.setConstraints(rb4, 0, 8);
        grid.getChildren().add(rb4);
        GridPane.setConstraints(buttonTrier, 0, 9);
        grid.getChildren().add(buttonTrier);

        root.getChildren().addAll(grid);
        scene1 = new Scene(root, 800, 600);

        return scene1;
    }

    public Scene filtreAvanceScene() // PAGE FILTRE AVANCE
    {
        grid.getChildren().clear();

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
        //	    AJOUTER BARRE CHARGEMENT


        GridPane.setConstraints(menuBar, 0, 0);
        grid.getChildren().add(menuBar);

        GridPane.setConstraints(label, 0, 2);
        grid.getChildren().add(label);
        GridPane.setConstraints(rb1, 0, 3);
        grid.getChildren().add(rb1);
        GridPane.setConstraints(datePickerDebut, 0, 4);
        grid.getChildren().add(datePickerDebut);
        GridPane.setConstraints(label2, 1, 4);
        grid.getChildren().add(label2);
        GridPane.setConstraints(datePickerFin, 2, 4);
        grid.getChildren().add(datePickerFin);
        GridPane.setConstraints(rb2, 0, 5);
        grid.getChildren().add(rb2);
        GridPane.setConstraints(textFieldAuteur, 1, 5);
        grid.getChildren().add(textFieldAuteur);
        GridPane.setConstraints(rb3, 0, 6);
        grid.getChildren().add(rb3);
        GridPane.setConstraints(textFieldTitre, 1, 6);
        grid.getChildren().add(textFieldTitre);
        GridPane.setConstraints(rb4, 0, 7);
        grid.getChildren().add(rb4);
        GridPane.setConstraints(textFieldSource, 1, 7);
        grid.getChildren().add(textFieldSource);
        GridPane.setConstraints(buttonTrier, 0, 8);
        grid.getChildren().add(buttonTrier);

        root.getChildren().addAll(grid);
        scene1 = new Scene(root, 800, 600);

        return scene1;
    }

    public Scene nuageScene() // PAGE NUAGE DE MOT
    {
        grid.getChildren().clear();

        GridPane.setConstraints(menuBar, 0, 0);
        grid.getChildren().add(menuBar);


        root.getChildren().addAll(grid);
        scene1 = new Scene(root, 800, 600);

        return scene1;
    }

    public Scene frequenceScene() // PAGE Frequence
    { // VOIR POUR FAIRE UN TABLEAU A 2 COLONNES
        grid.getChildren().clear();

        TableView table = new TableView();
        TableColumn NomCol = new TableColumn("Nom");
        TableColumn RecCol = new TableColumn("Nombre de récurence");
        table.getColumns().addAll(RecCol, NomCol);


        grid.getChildren().add(menuBar);
        GridPane.setConstraints(table, 1, 2);
        grid.getChildren().add(table);

        root.getChildren().addAll(grid);
        scene1 = new Scene(root, 800, 600);

        return scene1;
    }


    public Scene proposScene() // PAGE Propos
    {// VOIR QUOI METTRE ICI
        grid.getChildren().clear();

        grid.getChildren().add(menuBar);

        root.getChildren().addAll(grid);
        scene1 = new Scene(root, 800, 600);

        return scene1;
    }

    public void start(Stage primaryStage) {
        thestage = primaryStage;
        //can now use the stage in other methods

        menuBar.getMenus().addAll(menuExpImp, menuArt, menuTris, menuStat, menuProp);
        menuExpImp.getItems().addAll(menuImp, menuExp);
        menuTris.getItems().addAll(menuTrisArt, menuFiltArt, menuFiltAv);
        menuStat.getItems().addAll(menuMots, menuFreq);

        //Menu Exporter Importer
        menuImp.setOnAction(this::ButtonClicked);
        menuExp.setOnAction(this::ButtonClicked);
        //Menu Articles
        menuArt.setOnAction(this::ButtonClicked);
        //Menu Tris
        menuTrisArt.setOnAction(this::ButtonClicked);
        menuFiltArt.setOnAction(this::ButtonClicked);
        menuFiltAv.setOnAction(this::ButtonClicked);
        //Menu Stat
        menuMots.setOnAction(this::ButtonClicked);
        menuFreq.setOnAction(this::ButtonClicked);
        //Menu A propos
        menuProp.setOnAction(this::ButtonClicked);
        //make 2 Panes


        //make 2 scenes from 2 panes


        primaryStage.setTitle("Application Java");
        primaryStage.setScene(exportScene());
        primaryStage.show();


    }

    private void ButtonClicked(ActionEvent e) {

        if (e.getSource() == menuExp) thestage.setScene(exportScene());
        else if (e.getSource() == menuImp) thestage.setScene(importScene());
        else if (e.getSource() == menuArt) thestage.setScene(articleScene());
        else if (e.getSource() == menuTrisArt) thestage.setScene(trisArticleScene());
        else if (e.getSource() == menuFiltArt) thestage.setScene(filtreArticleScene());
        else if (e.getSource() == menuFiltAv) thestage.setScene(filtreAvanceScene());
        else if (e.getSource() == menuMots) thestage.setScene(nuageScene());
        else if (e.getSource() == menuFreq) thestage.setScene(frequenceScene());
        else if (e.getSource() == menuProp) thestage.setScene(proposScene());
    }

    public static void main(String[] args) {
        launch(args);
    }

}  