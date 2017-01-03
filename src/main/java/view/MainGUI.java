package view;

import com.sun.javafx.geom.Dimension2D;
import controller.ArticleIndex;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Article;
import view.component.*;

import java.util.ArrayList;


public class MainGUI extends Application {
    private final Dimension2D defaultSize = new Dimension2D(1200, 800);
    private final GridPane root;
    private final MenuBar menuBar;
    private final Menu menuExpImp;
    private final MenuItem menuImp;
    private final MenuItem menuExp;
    private final Menu menuTris;
    private final MenuItem menuItemArt;
    private final MenuItem menuTrisArt;
    private final MenuItem menuFiltArt;
    private final MenuItem menuFiltAv;
    private final Menu menuStat;
    private final MenuItem menuMots;
    private final MenuItem menuFreq;
    private final Menu menuAbout;
    private final MenuItem menuItemAbout;
    public final ArticleIndex index;

    private Scene scene;
    public Stage stage;

    private GridPane grid = new GridPane();
    private SortArticlePane sortArticlePane;
    private FilterArticlePane filterArticlePane;
    private ImportPane importPane;
    private AdvancedFilterPane advancedFilterPane;
    private AboutPane aboutPane;
    private CloudWordPane cloudWordPane;
    private FrequencyPane frequencyPane;
    private ArticleListPane articleListPane;
    private ExportPane exportPane;

    public ArrayList<Article> currentArticles = new ArrayList<>();


    public MainGUI() {
        new JFXPanel(); // Fix de l'erreur "Toolkit not initialized", quand utilisé sans Application.launch ( statique )
        root = new GridPane();

        // MENU
        menuBar = new MenuBar();
        menuExpImp = new Menu("Import/Export");
        menuImp = new MenuItem("Importer les articles");
        menuExp = new MenuItem("Exporter les articles");
        menuItemArt = new MenuItem("Articles");
        menuTris = new Menu("Tris et filtres");
        menuTrisArt = new MenuItem("Trier les articles");
        menuFiltArt = new MenuItem("Filtrer les articles");
        menuFiltAv = new MenuItem("Filtre avancé");
        menuStat = new Menu("Statistiques");
        menuMots = new MenuItem("Nuage de Mots");
        menuFreq = new MenuItem("Fréquence");
        menuAbout = new Menu("à Propos");
        menuItemAbout = new MenuItem("A propos");

        //INDEX
        index = new ArticleIndex();

        //PAGES
        sortArticlePane = new SortArticlePane();
        filterArticlePane = new FilterArticlePane();
        importPane = new ImportPane(this);
        advancedFilterPane = new AdvancedFilterPane();
        aboutPane = new AboutPane();
        cloudWordPane = new CloudWordPane();
        frequencyPane = new FrequencyPane();
        articleListPane = new ArticleListPane();
        exportPane = new ExportPane(this);

    }




    public void switchPane(GridPane pane){
        scene.setRoot(new GridPane());
        root.getChildren().clear();
        GridPane.setConstraints(menuBar, 0, 0);
        GridPane.setConstraints(pane, 0, 1);

        GridPane.setHgrow(pane, Priority.ALWAYS);
        GridPane.setVgrow(pane, Priority.ALWAYS);
        root.getChildren().addAll(pane, menuBar);
        root.setPrefSize(defaultSize.width, defaultSize.height);

        scene.setRoot(root);
        stage.setScene(scene);
    }

    private void ButtonClicked(ActionEvent e) {
        GridPane pane = new GridPane();
        if (e.getSource() == menuTrisArt) pane = sortArticlePane;
        else if (e.getSource() == menuFiltArt) pane = filterArticlePane;
        else if (e.getSource() == menuImp) pane = importPane;
        else if (e.getSource() == menuFiltAv) pane = advancedFilterPane;
        else if (e.getSource() == menuItemAbout) pane = aboutPane;
        else if (e.getSource() == menuMots) pane = cloudWordPane;
        else if (e.getSource() == menuFreq) pane = frequencyPane;
        else if (e.getSource() == menuItemArt) pane = articleListPane;
        else if (e.getSource() == menuExp) pane = exportPane;

        switchPane(pane);
    }


    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;
        //can now use the stage in other methods

        menuBar.getMenus().addAll(menuExpImp, menuTris, menuStat, menuAbout);

        menuExpImp.getItems().addAll(menuImp, menuExp);
        menuTris.getItems().addAll(menuItemArt, menuTrisArt, menuFiltArt, menuFiltAv);
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


        primaryStage.setTitle("Application Java");
        scene = new Scene(root, defaultSize.width, defaultSize.height);
        switchPane(exportPane);

        stage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) throws Exception {

        launch(args);

    }


    public void refreshData() {
        System.out.println("Refreshing Data !");
        currentArticles = index.getDefaultResult();
        articleListPane.setArticles(currentArticles);
    }
}