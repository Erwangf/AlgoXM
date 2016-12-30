package view;

import com.sun.javafx.geom.Dimension2D;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.component.*;


public class MainGUI extends Application {
    Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2;
    Stage stage;

    StackPane root = new StackPane();
    MenuBar menuBar = new MenuBar();
    Menu menuExpImp = new Menu("Import/Export");
    MenuItem menuImp = new MenuItem("Importer les articles");
    MenuItem menuExp = new MenuItem("Exporter les articles");
    MenuItem menuItemArt = new MenuItem("Articles");
    Menu menuTris = new Menu("Tris et filtres");
    MenuItem menuTrisArt = new MenuItem("Trier les articles");
    MenuItem menuFiltArt = new MenuItem("Filtrer les articles");
    MenuItem menuFiltAv = new MenuItem("Filtre avancé");
    Menu menuStat = new Menu("Statistiques");
    MenuItem menuMots = new MenuItem("Nuage de Mots");
    MenuItem menuFreq = new MenuItem("Fréquence");
    Menu menuAbout = new Menu("à Propos");
    MenuItem menuItemAbout = new MenuItem("A propos");

    GridPane grid = new GridPane();


    private Dimension2D defaultSize = new Dimension2D(1200,800);






    private void ButtonClicked(ActionEvent e) {

        grid.getChildren().clear();
        StackPane newRoot = new StackPane();
        if (e.getSource() == menuTrisArt) grid = new SortArticlePane();
        else if (e.getSource() == menuFiltArt) grid = new FilterArticlePane();
        else if (e.getSource() == menuImp) grid = new ImportPane();
        else if (e.getSource() == menuFiltAv) grid = new AdvancedFilterPane();
        else if (e.getSource() == menuItemAbout) grid = new AboutPane();
        else if (e.getSource() == menuMots) grid = new CloudWordPage();
        else if (e.getSource() == menuFreq) grid = new FrequencyPane();
        else if (e.getSource() == menuItemArt) grid = new ArticleListPane();
        else if (e.getSource() == menuExp) grid = new ExportPane();





        GridPane.setConstraints(menuBar, 0, 0);
        grid.getChildren().add(menuBar);
        grid.setMinSize(defaultSize.width, defaultSize.height);
        newRoot.getChildren().addAll(grid);
        newRoot.setPrefSize(defaultSize.width, defaultSize.height);

        stage.setScene(new Scene(newRoot, defaultSize.width, defaultSize.height));


    }

    public void start(Stage primaryStage) {
        stage = primaryStage;
        //can now use the stage in other methods

        menuBar.getMenus().addAll(menuExpImp, menuTris, menuStat, menuAbout);

        menuExpImp.getItems().addAll(menuImp, menuExp);
        menuTris.getItems().addAll(menuItemArt,menuTrisArt, menuFiltArt, menuFiltAv);
        menuStat.getItems().addAll(menuMots, menuFreq);
        menuAbout.getItems().addAll(menuItemAbout);

        //Menu Exporter Importer
        menuImp.setOnAction(this::ButtonClicked);
        menuExp.setOnAction(this::ButtonClicked);
        //Menu Articles
        menuItemArt.setOnAction(this::ButtonClicked);
        //Menu Tris
        menuTrisArt.setOnAction(this::ButtonClicked);
        menuFiltArt.setOnAction(this::ButtonClicked);
        menuFiltAv.setOnAction(this::ButtonClicked);
        //Menu Stat
        menuMots.setOnAction(this::ButtonClicked);
        menuFreq.setOnAction(this::ButtonClicked);
        //Menu A propos
        menuItemAbout.setOnAction(this::ButtonClicked);
        //make 2 Panes


        //make 2 scenes from 2 panes


        primaryStage.setTitle("Application Java");
        grid = new ExportPane();

        GridPane.setConstraints(menuBar, 0, 0);
        grid.getChildren().add(menuBar);
        root.getChildren().addAll(grid);

        stage.setScene(new Scene(root, defaultSize.width, defaultSize.height));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}  