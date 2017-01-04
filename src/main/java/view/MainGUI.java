package view;

import com.sun.javafx.geom.Dimension2D;
import controller.ArticleIndex;
import controller.IOController;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Article;
import view.component.*;

import java.io.File;
import java.util.ArrayList;


public class MainGUI extends Application {
    private final Dimension2D defaultSize = new Dimension2D(1200, 800);
    private final GridPane root;
    private final MenuBar menuBar;
    private final Menu fileMenu;
    private final MenuItem menuImp;
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
    private AdvancedFilterPane advancedFilterPane;
    private AboutPane aboutPane;
    private CloudWordPane cloudWordPane;
    private FrequencyPane frequencyPane;
    private ArticleListPane articleListPane;

    public ArrayList<Article> currentArticles = new ArrayList<>();
    private MenuItem menuExp;
    private MenuItem menuClean;


    public MainGUI() {
        new JFXPanel(); // Fix de l'erreur "Toolkit not initialized", quand utilisé sans Application.launch ( statique )
        root = new GridPane();

        // MENU
        menuBar = new MenuBar();
        //> FILE
        fileMenu = new Menu("Fichier");
        menuImp = new MenuItem("Ajouter un document");
        menuExp = new MenuItem("Exporter");
        menuClean = new MenuItem("Vider l'index");

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
        advancedFilterPane = new AdvancedFilterPane();
        aboutPane = new AboutPane();
        cloudWordPane = new CloudWordPane();
        frequencyPane = new FrequencyPane();
        articleListPane = new ArticleListPane();
    }




    public void loadPane(GridPane pane){
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

    public void switchPane(ActionEvent e) {
        GridPane pane = new GridPane();
        if (e.getSource() == menuTrisArt) pane = sortArticlePane;
        else if (e.getSource() == menuFiltArt) pane = filterArticlePane;
        else if (e.getSource() == menuFiltAv) pane = advancedFilterPane;
        else if (e.getSource() == menuItemAbout) pane = aboutPane;
        else if (e.getSource() == menuMots) pane = cloudWordPane;
        else if (e.getSource() == menuFreq) pane = frequencyPane;
        else if (e.getSource() == menuItemArt) pane = articleListPane;

        loadPane(pane);
    }


    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;
        //can now use the stage in other methods

        menuBar.getMenus().addAll(fileMenu, menuTris, menuStat, menuAbout);


        fileMenu.getItems().addAll(menuImp,menuExp, menuClean);
        menuTris.getItems().addAll(menuItemArt, menuTrisArt, menuFiltArt, menuFiltAv);
        menuStat.getItems().addAll(menuMots, menuFreq);
        menuAbout.getItems().addAll(menuItemAbout);

        //MenuItem : Import
        menuImp.setOnAction((e)->showFileInputChooser());
        //MenuItem : Export
        menuExp.setOnAction((e)->showExportFileChooser());
        //MenuItem : Export
        menuClean.setOnAction((e)->showCleanWindow());


        //Menu Articles
        menuItemArt.setOnAction(this::switchPane);
        //Menu Tris
        menuTrisArt.setOnAction(this::switchPane);
        menuFiltArt.setOnAction(this::switchPane);
        menuFiltAv.setOnAction(this::switchPane);
        //Menu Stat
        menuMots.setOnAction(this::switchPane);
        menuFreq.setOnAction(this::switchPane);
        //Menu A propos
        menuItemAbout.setOnAction(this::switchPane);


        primaryStage.setTitle("Application Java");

        scene = new Scene(root, defaultSize.width, defaultSize.height);
        loadPane(articleListPane);
        stage.setScene(scene);
        primaryStage.show();


    }




    public static void main(String[] args) throws Exception {

        launch(args);

    }


    private void initData() {
        System.out.println("Refreshing Data !");
        currentArticles = index.getDefaultResult();
        articleListPane.setArticles(currentArticles);
    }


    private void loadArticleFromFile(String path){

        if(path.length()!=0){
            index.addArticles(IOController.readFile(path));
            initData();
        }

    }

    private void showFileInputChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File f = fileChooser.showOpenDialog(stage);
        if(f!=null){
            loadArticleFromFile(f.getPath());
        }
    }

    private void showExportFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save...");
        File f = fileChooser.showSaveDialog(stage);

        if (f != null) {
            writeToFile(f.getPath());
        }
    }

    private void writeToFile(String path) {
        if (path.length() != 0) {
            IOController.writeToCSV(currentArticles, path);
        }
    }
    private void showCleanWindow() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Voulez-vous vraiment vider l'index de tous les documents enregistrés ?"));
        Button btnYes = new Button("Oui");
        Button btnNo = new Button("Non");
        btnYes.setOnAction((e)->{this.clean();dialog.hide();});
        btnNo.setOnAction((e)->dialog.hide());
        GridPane pane = new GridPane();
        GridPane.setConstraints(dialogVbox,0,0);
        GridPane.setConstraints(btnYes,0,1);
        GridPane.setConstraints(btnNo,0,2);

        pane.getChildren().addAll(dialogVbox,btnNo,btnYes);

        Scene dialogScene = new Scene(pane, 400, 300);
        dialog.setScene(dialogScene);
        dialog.show();

    }
    private void clean(){
        index.dropAll();
        initData();
    }
}