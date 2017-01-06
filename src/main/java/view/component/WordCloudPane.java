package view.component;

import controller.WordCloudController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import model.Frequency;
import view.MainGUI;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Erwan on 29/12/2016.
 */
public class WordCloudPane extends GridPane {

    private final MainGUI mainGUI;
    private final WordCloudController controller;
    private int nbKeywordsOnCloud = 100;
    private int nbKeywordsFreq = 20;
    private final TableView<Frequency> tableView;
    private ObservableList<Frequency> data = FXCollections.observableList(new ArrayList<>());


    public WordCloudPane(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        this.controller = new WordCloudController(mainGUI.index);
        tableView = new TableView<>();

        TableColumn<Frequency,String> wordCol = new TableColumn<>("Nom");
        wordCol.setCellValueFactory(f->new SimpleStringProperty(f.getValue().getWord()));
        wordCol.setSortable(false);

        TableColumn<Frequency,Integer> freqCol = new TableColumn<>("Nombre de récurence");
        freqCol.setCellValueFactory(f->new SimpleIntegerProperty(f.getValue().getFrequency()).asObject());
        freqCol.setSortable(false);

        tableView.getColumns().addAll(freqCol, wordCol);
        tableView.setItems(data);

        GridPane.setConstraints(tableView, 1, 2);
        this.getChildren().add(tableView);


    }

    public void refresh(){
        controller.refreshFreq(Math.max(nbKeywordsFreq,nbKeywordsOnCloud));
        showWordCloud();
        loadFreqs();

    }

    public void showWordCloud() {

        BufferedImage bufferedImage = controller.getWordCloudImage(100);

        WritableImage wr = null;
        if (bufferedImage != null) {
            wr = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            PixelWriter pixelWriter = wr.getPixelWriter();
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    pixelWriter.setArgb(x, y, bufferedImage.getRGB(x, y));
                }
            }
        }

        ImageView imView = new ImageView(wr);
        this.getChildren().add(imView);
    }

    public void loadFreqs(){
        data.clear();
        data.addAll(controller.getTopFreq(nbKeywordsFreq));
    }


}

