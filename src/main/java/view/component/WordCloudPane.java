package view.component;

import controller.WordCloudController;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import view.MainGUI;

import java.awt.image.BufferedImage;

/**
 * Created by Erwan on 29/12/2016.
 */
public class WordCloudPane extends GridPane {

    private final MainGUI mainGUI;
    private final WordCloudController controller;


    public WordCloudPane(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        this.controller = new WordCloudController(mainGUI.index);


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


}

