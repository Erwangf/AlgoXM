package view;

import com.sun.javafx.geom.Dimension2D;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Article;
import view.component.*;

import java.util.ArrayList;


public class MainGUI extends Application implements Runnable {
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


    private Button btnscene1, btnscene2;
    private Label lblscene1, lblscene2;
    private FlowPane pane1, pane2;
    private Scene scene1, scene2;
    private Stage stage;

    private SortArticlePane sortArticlePane;
    private FilterArticlePane filterArticlePane;
    private ImportPane importPane;
    private AdvancedFilterPane advancedFilterPane;
    private AboutPane aboutPane;
    private CloudWordPane cloudWordPane;
    private FrequencyPane frequencyPane;
    private ArticleListPane articleListPane;
    private ExportPane exportPane;


    private ObservableList<Article> articleList = FXCollections.observableArrayList();

    GridPane grid = new GridPane();

    public MainGUI() {
        new JFXPanel(); // Fix de l'erreur "Toolkit not initialized", quand utilisé sans le launch.
        root = new GridPane();

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

        sortArticlePane = new SortArticlePane();

        filterArticlePane = new FilterArticlePane();
        importPane = new ImportPane();
        advancedFilterPane = new AdvancedFilterPane();
        aboutPane = new AboutPane();
        cloudWordPane = new CloudWordPane();
        frequencyPane = new FrequencyPane();
        articleListPane = new ArticleListPane();
        exportPane = new ExportPane();


        menuBar = new MenuBar();

    }


    private void ButtonClicked(ActionEvent e) {


        GridPane newRoot = new GridPane();

        if (e.getSource() == menuTrisArt) grid = sortArticlePane;
        else if (e.getSource() == menuFiltArt) grid = filterArticlePane;
        else if (e.getSource() == menuImp) grid = importPane;
        else if (e.getSource() == menuFiltAv) grid = advancedFilterPane;
        else if (e.getSource() == menuItemAbout) grid = aboutPane;
        else if (e.getSource() == menuMots) grid = cloudWordPane;
        else if (e.getSource() == menuFreq) grid = frequencyPane;
        else if (e.getSource() == menuItemArt) grid = articleListPane;
        else if (e.getSource() == menuExp) grid = exportPane;


        GridPane.setConstraints(menuBar,0,0);
        GridPane.setConstraints(grid,0,1);

        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);
        newRoot.getChildren().addAll(grid,menuBar);
        newRoot.setPrefSize(defaultSize.width, defaultSize.height);

        stage.setScene(new Scene(newRoot, defaultSize.width, defaultSize.height));


    }


    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;
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
        //make 2 Panes


        //make 2 scenes from 2 panes


        primaryStage.setTitle("Application Java");
        grid = new ExportPane();

        GridPane.setConstraints(menuBar, 0, 0);
        GridPane.setConstraints(grid, 0, 1);
        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);


        root.getChildren().addAll(grid,menuBar);

        stage.setScene(new Scene(root, defaultSize.width, defaultSize.height));
        primaryStage.show();


    }

    @Override
    public void run() {
        launch();
    }

    public static void main(String[] args) throws Exception {

        MainGUI m = new MainGUI();
        new Thread(m).start();

//        launch(args);

    }


    public ObservableList<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<Article> al) {
        // Ici on ne redéfinie pas une nouvelle liste d'article à partir du paramètre, pour conserver le pointeur
        al.forEach(this.articleList::add);
        this.articleListPane.setArticles(this.articleList);

    }



}  