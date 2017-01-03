package view.component;

import controller.IOController;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import view.MainGUI;

import java.io.File;


public class ImportPane extends GridPane {

    private final MainGUI mainGUI;
    private final TextField textField;
    private final CheckBox keepDataCB;

    public ImportPane(MainGUI mainGUI){
        super();

        Label label = new Label("Choissisez le fichier à importer : ");
        textField = new TextField();
        textField.setText("");
        textField.setEditable(false);
        Button buttonParc = new Button("buttonParc");
        buttonParc.setText("Parcourir ...");
        keepDataCB = new CheckBox("Je souhaite conserver les données déjà enregistrées");
        Button buttonImp = new Button("buttonImp");
        buttonImp.setText("Importer");


        buttonParc.setOnAction((e)-> showFileInputChooser());
        buttonImp.setOnAction((e)->loadArticleFromFile());


        GridPane.setConstraints(label, 0, 2);
        this.getChildren().add(label);

        GridPane.setConstraints(textField, 0, 3);
        this.getChildren().add(textField);

        GridPane.setConstraints(buttonParc, 1, 3);
        this.getChildren().add(buttonParc);

        GridPane.setConstraints(keepDataCB, 0, 4);
        this.getChildren().add(keepDataCB);

        GridPane.setConstraints(buttonImp, 0, 5);
        this.getChildren().add(buttonImp);

        this.mainGUI = mainGUI;
    }

    public void loadArticleFromFile(){
        String path = textField.getText();
        if(path.length()!=0){
            if(!keepDataCB.isSelected()){
                System.out.println("DROP ALL DOCUMENTS");
                mainGUI.index.dropAll();
            }
            mainGUI.index.addArticles(IOController.readFile(path));
            mainGUI.refreshData();
        }

    }

    public void showFileInputChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File f = fileChooser.showOpenDialog(mainGUI.stage);
        if(f!=null){
            textField.setText(f.getAbsolutePath());
        }
    }
}
